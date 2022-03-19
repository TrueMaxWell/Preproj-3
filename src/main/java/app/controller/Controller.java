package app.controller;

import app.service.UserService;
import app.vk.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    VkService vkService;

    @GetMapping({"/admin", "/user"})
    public String getUserPage() {
        return "index";
    }

    @GetMapping({"login", "/"})
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/requests")
    public String getRequestsPage() {
        return "requests";
    }

    @GetMapping("/getVkGroupToken")
    public String getVkGroupToken() {
        return "redirect:" + vkService.getVkGroupToken();
    }

    @GetMapping("/getVkUserToken")
    public String getVkUserToken() {
        return "redirect:" + vkService.getVkUserToken();
    }


}
