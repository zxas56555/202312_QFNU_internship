package cn.edu.qfnu.demo.controller;

import cn.edu.qfnu.demo.entity.User;
import cn.edu.qfnu.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
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

    @PostMapping("findList/{pageNum}/{pageSize}")
    public List<User> findList(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<User> userPage = userRepository.findAll(pageRequest);

        return userPage.stream().toList();
    }
}
