package app.controller;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String userPage(Model model) {
        model.addAttribute("user",
                userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "userPage";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "logout";
    }


}
