package edu.mum.cs490.project.controller.vendor;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.ProductForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/vendor/product")
public class VendorProductController {
    private Path path;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String productManagement(@AuthenticationPrincipal Vendor vendor, Model model) {
        List<Product> productsList = productService.findByVendorAndStatus(vendor, Status.ENABLED);
        model.addAttribute("productList", productsList);

        return "vendor/productManagement";
    }

    @GetMapping("/save")
    public String saveOrUpdateProduct(@RequestParam(required = false) Integer id, Model model) {

        model.addAttribute("title", "Add Product");

        if (id != null && id != 0) {
            model.addAttribute("productForm", new ProductForm(productService.getOne(id)));
        } else {
            model.addAttribute("productForm", new ProductForm());
        }
        model.addAttribute("categories", categoryService.getAllMainCategory());

        return "vendor/saveProduct";
    }

    @PostMapping("/save")
    public String saveOrUpdateProduct(@Valid @ModelAttribute("productForm") ProductForm form, BindingResult result,
                                      @AuthenticationPrincipal Vendor vendor, Model model) {

        model.addAttribute("categories", categoryService.getAllMainCategory());

        if (result.hasErrors()) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "Check your forms!!!"));
            return "vendor/saveProduct";
        }

        Product product;
        if (form.getId() == null) {
            product = new Product();
        }
        else{
            product = productService.getOne(form.getId());
        }
        product.setStatus(Status.ENABLED);
        product.setCategory(categoryService.getCategoryById(form.getCategoryId()));
        product.setDescription(form.getDescription());
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setQuantity(form.getQuantity());
        product.setVendor(vendor);
        productService.saveOrUpdateProduct(product);
        model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.saved"));

        return "redirect:/vendor/product/all";
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(required = true) Integer id) {

        productService.delete(id);

        return "redirect:/vendor/product/all";
    }

}
