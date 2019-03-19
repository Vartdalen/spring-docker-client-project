package no.oslomet.s315615springdockerclientproject.controller;

import no.oslomet.s315615springdockerclientproject.model.Error;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyErrorController implements ErrorController {

    public MyErrorController() {}

    @GetMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Error error = new Error();
        appendErrorInformation(error, request, "");
        model.addAttribute("error", error);
        return "error";
    }

    @GetMapping("/error/login")
    public String handleLoginError(Model model, HttpServletRequest request) {
        Error error = new Error();
        appendErrorInformation(error, request, "Invalid username or password");
        model.addAttribute("error", error);
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
    
    private void appendErrorInformation(Error error, HttpServletRequest request, String message) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object statusMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if(statusCode != null) {
            error.setCode(statusCode.toString());
        } else {
            error.setCode("404");
        }

        if(statusMessage != null) {
            error.setMessage(statusMessage.toString());
        } else {
            error.setMessage(message);
        }
    }
}