package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.form.ReportForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.OrderDetailService;
import edu.mum.cs490.project.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping(value = "/reportFilter")
    public String adminReportLoading(Model model) {
        List<Vendor> vendorList = vendorService.getAll();
        List<String> vendorListNames = new ArrayList<>();
        for (Vendor v : vendorList) {
            String item = v.getCompanyName();
            vendorListNames.add(item);
        }
        List<Category> categoryList = categoryService.getAllCategory();
        List<String> categoryListNames = new ArrayList<>();
        for (Category c : categoryList) {
            String item = c.getName();
            System.out.println("Test category " + item);
            categoryListNames.add(item);
        }
        model.addAttribute("vendors", vendorListNames);
        model.addAttribute("categories", categoryListNames);
        return "report/reportFilter";
    }

    @PostMapping(value = "/reportFilter")
    public String adminReportExport(Model model, @ModelAttribute("reportForm") ReportForm reportForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "report/errorPage";
        }

        return "report/reportFilter";
    }

//    @GetMapping(value = "/vendorReport")
//    public String vendorReportLoading(Model model) {
//        List<Category> categoryList = categoryService.getAllCategory();
//        List<String> categoryListNames = new ArrayList<>();
//        for (Category c : categoryList) {
//            String item = c.getName();
//            System.out.print("Test category" + item);
//            categoryListNames.add(item);
//        }
//        model.addAttribute("categories", categoryListNames);
//        return "report/vendorReport";
//    }

//    @PostMapping(value = "/vendorReport")
//    public String vendorReportExport(Model model, @RequestParam String from, @RequestParam String to, @RequestParam String category) {
//        return "report/vendorExport";
//    }


}
