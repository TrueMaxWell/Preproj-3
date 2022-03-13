package app.controller;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
//@RequestMapping(value = "/admin")
public class RestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<User> list() {
        return userService.getUsersList();
    }

//    @GetMapping("{id}")
//    public User getUserInfo(@PathVariable Long id) {
//        return userService.getUser(id);
//    }

    @RequestMapping(value = "create/admin", method = RequestMethod.POST)
    public void createAdmin(@RequestBody User user) {
        Collection<Role> roles = new ArrayList<>();
        Role roleAdmin = new Role();
        roleAdmin.setRole("ROLE_ADMIN");
        roles.add(roleAdmin);
        Role roleUser = new Role();
        roleUser.setRole("ROLE_USER");
        roles.add(roleUser);
        user.setRoles(roles);
        System.out.println(user.getAuthorities());
        userService.add(user);
    }

    @RequestMapping(value = "create/user", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        Collection<Role> roles = new ArrayList<>();
        Role roleUser = new Role();
        roleUser.setRole("ROLE_USER");
        roles.add(roleUser);
        user.setRoles(roles);
        System.out.println(user.getAuthorities());
        userService.add(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        userService.changeUser(user);
        return user;
    }

    @DeleteMapping("{id}")
    public void delete(@RequestBody String id){
        userService.removeUser(Long.valueOf(id));
    }
}
