package cn.edu.qfnu.demo.controller;

import cn.edu.qfnu.demo.entity.User;
import cn.edu.qfnu.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("findById/{id}")
    public User findById(@PathVariable Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.orElse(null);
    }

    @PostMapping("save")
    public String save(@RequestBody User user){
        userRepository.save(user);
        return "success";
    }

    @PostMapping("removeById/{id}")
    public  String removeById(@PathVariable Integer id){
        userRepository.deleteById(id);
        return "success";
    }
}
