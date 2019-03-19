package no.oslomet.s315615springdockerclientproject.controller;

import no.oslomet.s315615springdockerclientproject.model.User;
import no.oslomet.s315615springdockerclientproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String newUser(Model model) {
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userService);
        return "user";
    }

    @PostMapping("/user")
    public String saveUser(@ModelAttribute("user") User newUser) throws HttpClientErrorException {
        try {
            userService.saveUser(newUser);
            return "redirect:/";
        } catch (HttpClientErrorException e) {
            return "redirect:/error";
        }
    }

//    @GetMapping("user/edit/{id}")
//    public String updateUser(@PathVariable long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("users", userService.getAllUsers());
//        return "index";
//    }
//
//    @GetMapping("user/delete/{id}")
//    public String deleteUser(@PathVariable long id) {
//        userService.deleteUserById(id);
//        return "redirect:/";
//    }

    private void setUserModel(Model model, Authentication auth, UserService userService) {
        Optional<User> user = userService.getUserByEmail(auth.getName());
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        }
    }
}
