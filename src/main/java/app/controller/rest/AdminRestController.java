package app.controller.rest;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> list() {
        return userService.getUsersList();
    }

    @GetMapping("{id}")
    public User getUserInfo(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        userService.changeUser(user);
        return user;
    }

    @DeleteMapping
    public User delete(@RequestBody User user){
        userService.removeUser(user.getId());
        return user;
    }
}
