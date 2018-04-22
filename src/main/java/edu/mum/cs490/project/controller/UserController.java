package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Admin;
import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.service.AdminService;
import edu.mum.cs490.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Erdenebayar on 4/21/2018
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public UserController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @RequestMapping("all")
    public List<User> getUsers(){
        return userService.getAll();
    }

    @RequestMapping("admin")
    public List<Admin> getAdmins(){
        return adminService.getAll();
    }
}
