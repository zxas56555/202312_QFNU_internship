package cn.edu.qfnu.demo.service.impl;

import cn.edu.qfnu.demo.entity.QUser;
import cn.edu.qfnu.demo.entity.User;
import cn.edu.qfnu.demo.model.RequestPageResult;
import cn.edu.qfnu.demo.repository.UserRepository;
import cn.edu.qfnu.demo.service.UserService;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User的业务逻辑实现类
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        userRepository.deleteById(id);

        return userOptional.orElse(null);
    }

    @Override
    public RequestPageResult<User> findPage(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);

        Page<User> users = userRepository.findAll(pageRequest);
        RequestPageResult<User> result = new RequestPageResult<>();
        result.setData(users.stream().toList());
        result.setTotalPage(users.getTotalPages());

        return result;
    }

    @Override
    public RequestPageResult<User> findPage(Integer pageNum, Integer pageSize, User user) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);

        // 使用Spring data jpa 的QBE 查询
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING));
//        Example<User> example = Example.of(user, matcher);
//
//        Page<User> users = userRepository.findAll(example, pageRequest);

        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotBlank(user.getName())){
            builder.and(QUser.user.name.contains(user.getName()));
        }
        if(StringUtils.isNotBlank(user.getUsername())) {
            builder.and(QUser.user.username.contains(user.getUsername()));
        }
        if(StringUtils.isNotBlank(user.getPhone())){
            builder.and(QUser.user.phone.contains(user.getPhone()));
        }
        Page<User> users = userRepository.findAll(builder, pageRequest);

        RequestPageResult<User> result = new RequestPageResult<>();
        result.setData(users.stream().toList());
        result.setTotalPage(users.getTotalPages());

        return result;
    }


    @Override
    public void saveOrUpdate(User user) {

        userRepository.save(user);
    }

    //    @Transactional(rollbackOn = Exception.class)
    @Override
    public void removeById(Integer id) {
        userRepository.deleteById(id);

//        int a = 10;
//        int b = a/0;
//        System.out.println(b);
    }
}
