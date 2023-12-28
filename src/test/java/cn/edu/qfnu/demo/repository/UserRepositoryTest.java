package cn.edu.qfnu.demo.repository;


import cn.edu.qfnu.demo.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void saveUser()
    {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setName("lgr");
        user.setPassword("123456");
        user.setAge(20);
        user.setPhone("12345678901");

        userRepository.save(user);
    }

    @Test
    public void findUser()
    {
        Optional<User> userOptional =  userRepository.findById(1);


        if(userOptional.isPresent()){
            System.out.println(userOptional.get().getName());
        }else{
            System.err.println("未查询到相关对象！");
        }
    }
}
