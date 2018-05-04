package edu.mum.cs490.project.controller.vendor;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.ProductForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
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
    @Value("${product.upload.path}")
    private String FILE_UPLOAD_PATH;

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

        String url = file.getOriginalFilename();
        Product product;
        if (form.getId() == null) {
            product = new Product();
        } else {
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

        if (file != null) {
            String fileFullName = createFile(file, product.getId());

            if (fileFullName != null) {
                product.setImage(fileFullName);
                productService.saveOrUpdateProduct(product);
            }
        }
        return "redirect:/vendor/product/all";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(required = true) Integer id) {

        productService.delete(id);

        return "redirect:/vendor/product/all";
    }

    private String createFile(MultipartFile file, Integer productId) {
        try {
            String directoryPath = "";
            if (FILE_UPLOAD_PATH != null) {
                directoryPath = FILE_UPLOAD_PATH + File.separator + productId;
                Path rootPath = Paths.get(directoryPath);
                if (!Files.exists(rootPath)) {
                    Files.createDirectories(rootPath);
                }
            }
            String fileFullName = directoryPath + File.separator + (productId + "." + file.getOriginalFilename().split("\\.")[1]);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileFullName);
            Files.write(path, bytes);
            System.out.println("Successfully created file - " + fileFullName);
            return fileFullName;
        } catch (IOException ex) {
            ex.getMessage();
        }
        return null;
    }
}
