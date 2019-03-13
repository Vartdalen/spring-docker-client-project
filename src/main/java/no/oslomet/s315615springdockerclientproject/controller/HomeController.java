package no.oslomet.s315615springdockerclientproject.controller;

import no.oslomet.s315615springdockerclientproject.model.User;
import no.oslomet.s315615springdockerclientproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model){
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userService);
        return "index";
    }

    private void setUserModel(Model model, Authentication auth, UserService userService) {
        User user = userService.getUserByEmail(auth.getName());
        model.addAttribute("user", user);
    }
}
