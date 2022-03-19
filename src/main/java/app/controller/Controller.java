package app.controller;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping({"/admin", "/user"})
    public String getUserPage() {
        return "index";
    }

    @GetMapping({"login", "/"})
    public String getLoginPage(HttpServletRequest request) {
        System.out.println(request.getHeader("access_token_211876865"));
        return "login";
    }

    @GetMapping("/getVkGroupToken")
    public String getVkGroupToken() {
        return "redirect:" + userService.getVkGroupToken();
    }

    @GetMapping("/getVkUserToken")
    public String getVkUserToken() {
        return "redirect:" + userService.getVkUserToken();
    }


}
