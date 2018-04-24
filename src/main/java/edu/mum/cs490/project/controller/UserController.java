package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.service.UserService;
import edu.mum.cs490.project.utils.SignedUser;

import java.util.List;

/**
 * Created by Erdenebayar on 4/21/2018
 */
//@RestController
/*public class UserController<T extends User> {

    protected final Class<T> domain;

    private final UserService<T> userService;

    public UserController(Class<T> domain, UserService<T> userService) {
        this.domain = domain;
        this.userService = userService;
    }

//    @RequestMapping("admin/all")
    public List<T> getUsers(){
        System.out.println(domain.getName());
        User user = SignedUser.getSignedUser();
        System.out.println(user.getUsername());
        return userService.getAll();
    }
}*/
