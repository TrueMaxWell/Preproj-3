package app.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping({"/admin", "/user"})
    public String getUserPage() {
        return "index";
    }

    @GetMapping({"login","/"})
    public String getLoginPage() {
        return "login";
    }


}
