package app.controller;


import app.model.Role;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String userListPage(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "adminPage";
    }

    @GetMapping("/newUser")
    public String showCreateUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("create", true);
        return "editOrCreateUser";
    }

    @PostMapping("/newUser/create")
    public String createUserPage(@ModelAttribute("user") User user) {
        Collection<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{userId}/edit")
    public String showEditUserPage(Model model, @PathVariable long userId) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("create", false);
        return "editOrCreateUser";
    }

    @PostMapping("/{userId}/edit/submit")
    public String editUserPage(@ModelAttribute("user") User user, @PathVariable long userId) {
        user.setId(userId);
        Collection<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        userService.changeUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUserPage(@PathVariable long userId) {
        userService.removeUser(userId);
        return "redirect:/admin/";
    }
}
