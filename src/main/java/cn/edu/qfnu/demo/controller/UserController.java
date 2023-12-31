package cn.edu.qfnu.demo.controller;

import cn.edu.qfnu.demo.entity.User;
import cn.edu.qfnu.demo.model.RequestPageResult;
import cn.edu.qfnu.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("findById/{id}")
    public User findById(@PathVariable Integer id) {

        return userService.findById(id);
    }

    @PostMapping("save")
    public String save(@RequestBody User user){
        userService.saveOrUpdate(user);
        return "success";
    }

    @PostMapping("removeById/{id}")
    public  String removeById(@PathVariable Integer id){
        userService.removeById(id);
        return "success";
    }

    @PostMapping("findList/{pageNum}/{pageSize}")
    public RequestPageResult<User> findList(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody User user){
        if (null == user){
            return userService.findPage(pageNum - 1, pageSize);
        }else {
            return  userService.findPage(pageNum - 1, pageSize, user);
        }
    }
}
