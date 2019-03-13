package no.oslomet.s315615springdockerclientproject.controller;

import no.oslomet.s315615springdockerclientproject.model.User;
import no.oslomet.s315615springdockerclientproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public String home(Model model) {
//        User user  = new User();
//        List<User> userList = userService.getAllUsers();
//        model.addAttribute("user", user);
//        model.addAttribute("users", userList);
//        return "index";
//    }

    @GetMapping("/user")
    public String registerUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        setUserModel(model, authentication, userService);
        return "user";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User newUser) {
        userService.saveUser(newUser);
        return "redirect:/";
    }

    @GetMapping("users/edit/{id}")
    public String updateUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    private void setUserModel(Model model, Authentication auth, UserService userService) {
        User user = userService.getUserByEmail(auth.getName());
        model.addAttribute("user", user);
    }
}
