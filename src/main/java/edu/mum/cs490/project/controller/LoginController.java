package edu.mum.cs490.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping
    public String index(){
        return "login";
    }
}
