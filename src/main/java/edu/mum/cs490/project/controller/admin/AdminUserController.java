package edu.mum.cs490.project.controller.admin;

import edu.mum.cs490.project.service.AdminService;
import edu.mum.cs490.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Erdenebayar on 4/29/2018
 */
@Controller
@RequestMapping("admin/users")
public class AdminUserController {

    private final AdminService adminService;

    @Autowired
    public AdminUserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/")
    public String getIndex(Model model){
        return "admin/user/index";
    }


}
