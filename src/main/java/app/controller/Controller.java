package app.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
//@RequestMapping(value = "/")
public class Controller {

    @GetMapping(value = "/")
    public String getLoginPage() {
        return "index";
    }


}
