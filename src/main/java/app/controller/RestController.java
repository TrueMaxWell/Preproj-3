package app.controller;

import app.model.User;
import app.service.UserService;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserService userService;

    @GetMapping("admin/get")
    public List<User> list() {
        return userService.getUsersList();
    }

    @GetMapping({"admin/getVkInfo/{vkId}", "user/getVkInfo/{vkId}"})
    public String getVkInfo(@PathVariable String vkId) throws ClientException {
        return userService.getVkInfo(vkId);
    }

    @GetMapping({"admin/get/current", "user/get/current"})
    public User getUserInfo() {
        return (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("admin/{role}")
    public void create(@RequestBody User user, @PathVariable String role) {
        userService.setRole(user, role);
        userService.add(user);
    }

    @PutMapping("admin/{role}")
    public User update(@RequestBody User user, @PathVariable String role) {
        userService.setRole(user, role);
        userService.changeUser(user);
        return user;
    }

    @DeleteMapping("admin/{id}")
    public void delete(@PathVariable String id){
        userService.removeUser(Long.valueOf(id));
    }

}
