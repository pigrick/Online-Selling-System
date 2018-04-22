package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Controller
public class SignController {

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "signup")
    public String signUp(@AuthenticationPrincipal User user) {
        if (user != null)
            return "signUp";
        return "/";
    }
}
