package app.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = {"/admin", "/user", "/"})
    public String getUserPage() {
        if(Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), "anonymousUser")) {
            return "redirect:/login";
        }

        return "index";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }


}
