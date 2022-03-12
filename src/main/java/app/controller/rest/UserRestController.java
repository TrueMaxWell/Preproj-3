package app.controller.rest;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public List<User> getUserInfo(@PathVariable String id) {
        return userService.getUsersList();
    }
}
