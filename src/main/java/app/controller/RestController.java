package app.controller;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserService userService;

    @GetMapping("admin/get")
    public List<User> list() {
        return userService.getUsersList();
    }

    @GetMapping({"admin/get/current", "user/get/current"})
    public User getUserInfo() {
        return (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("admin/{role}")
    public void create(@RequestBody User user, @PathVariable String role) {
        setRole(user, role);
        userService.add(user);
    }

    @PutMapping("admin/{role}")
    public User update(@RequestBody User user, @PathVariable String role) {
        setRole(user, role);
        userService.changeUser(user);
        return user;
    }

    @DeleteMapping("admin/{id}")
    public void delete(@PathVariable String id){
        userService.removeUser(Long.valueOf(id));
    }

    private void setRole(User user,String role) {
        Collection<Role> roles = new ArrayList<>();
        if (Objects.equals(role, "ROLE_ADMIN")) {
            Role roleAdmin = new Role();
            roleAdmin.setRole("ROLE_ADMIN");
            roles.add(roleAdmin);
        }
        Role roleUser = new Role();
        roleUser.setRole("ROLE_USER");
        roles.add(roleUser);
        user.setRoles(roles);
    }
}
