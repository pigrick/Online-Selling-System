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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/vendor/product")
public class VendorProductController {
    private Path path;

    @Autowired
    private ProductService productService;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://temp//";

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

        model.addAttribute("title", "Product:");

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
                                      @AuthenticationPrincipal Vendor vendor,
                                      @RequestParam("file") MultipartFile file,
                                      RedirectAttributes model) {

        model.addFlashAttribute("title", "Product");
        model.addFlashAttribute("categories", categoryService.getAllMainCategory());

        if (file.isEmpty() || result.hasErrors()) {
            model.addFlashAttribute("message", "Please select a file to upload or check fields!!!");
            return "vendor/saveProduct";
        }

        try {
            String url=file.getOriginalFilename();
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
            product.setImage(UPLOADED_FOLDER +url);
            productService.saveOrUpdateProduct(product);


            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/vendor/product/all";
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(required = true) Integer id) {

        productService.delete(id);

        return "redirect:/vendor/product/all";
    }


}
