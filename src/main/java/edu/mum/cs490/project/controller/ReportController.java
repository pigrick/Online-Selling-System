package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/adminReport", method = RequestMethod.GET)
    public String adminReportLoading(Model model) {
        List<Vendor> vendorList = vendorService.getAll();
        List<String> vendorListNames = new ArrayList<>();
        for (Vendor v : vendorList) {
            String item = v.getCompanyName();
            vendorListNames.add(item);
        }
        List<Category> categoryList = categoryService.getAllMainCategory();
        List<String> categoryListNames = new ArrayList<>();
        for (Category c : categoryList) {
            String item = c.getName();
            categoryListNames.add(item);
        }
        model.addAttribute("vendors", vendorListNames);
        model.addAttribute("categories", categoryListNames);
        return "report/adminReport";
    }

    @RequestMapping(value = "/adminExport", method = RequestMethod.POST)
    public String adminReportExport(Model model, @RequestParam String from, @RequestParam String to, @RequestParam String vendor, @RequestParam String category) {
        
        return "report/adminExport";
    }

    @RequestMapping(value = "/vendorReport", method = RequestMethod.GET)
    public String vendorReportLoading(Model model) {
        List<Category> categoryList = categoryService.getAllMainCategory();
        List<String> categoryListNames = new ArrayList<>();
        for (Category c : categoryList) {
            String item = c.getName();
            categoryListNames.add(item);
        }
        model.addAttribute("categories", categoryListNames);
        return "report/vendorReport";
    }

    @RequestMapping(value = "/vendorExport", method = RequestMethod.POST)
    public String vendorReportExport(Model model, @RequestParam String from, @RequestParam String to, @RequestParam String category) {
        return "report/vendorExport";
    }


}
