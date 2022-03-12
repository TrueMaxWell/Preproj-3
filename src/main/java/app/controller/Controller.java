//package app.controller;
//
//
//import app.model.Role;
//import app.model.User;
//import app.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import java.util.ArrayList;
//import java.util.Collection;
//
//@org.springframework.stereotype.Controller
////@RequestMapping(value = "/")
//public class Controller {
//
////    @Autowired
////    UserService userService;
//
////    @GetMapping(value = "/")
////    public String getLoginPage() {
////        return "redirect:/user";
////    }
//
//    @GetMapping(value = "/user")
//    public String getUserPage() {
//        return "user";
//    }
//
//    @GetMapping(value = "/admin")
//    public String getAdminPage() {
//        return "admin";
//    }
//
////    @GetMapping(value = "/login")
////    public String getLoginPage() {
////        return "login";
////    }
////
////    @GetMapping("/registration")
////    public String showCreateUserPage(Model model) {
////        User user = new User();
////        model.addAttribute("user", user);
////        model.addAttribute("create", true);
////        return "registration";
////    }
////
////    @PostMapping("/registration/create")
////    public String createUserPage(@ModelAttribute("user") User user) {
////        Collection<Role> roles = new ArrayList<>();
////        Role role = new Role();
////        role.setRole("ROLE_USER");
////        roles.add(role);
////        user.setRoles(roles);
////        userService.add(user);
////        return "redirect:/login";
////    }
//}
