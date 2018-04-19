package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    public UserService userService;

    @RequestMapping("/")
    public String getHome(){

        return "index";
    }

}
