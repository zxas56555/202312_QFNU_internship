package cn.edu.qfnu.demo.service.impl;

import cn.edu.qfnu.demo.entity.User;
import cn.edu.qfnu.demo.model.RequestPageResult;
import cn.edu.qfnu.demo.repository.UserRepository;
import cn.edu.qfnu.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
