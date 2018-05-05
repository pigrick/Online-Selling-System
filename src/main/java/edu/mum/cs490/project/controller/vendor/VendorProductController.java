package edu.mum.cs490.project.controller.vendor;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.ProductForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import edu.mum.cs490.project.service.impl.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/vendor/product")
public class VendorProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileManagementService fileManagementService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String productManagement(@AuthenticationPrincipal Vendor vendor, Model model) {
        List<Product> productsList = productService.find(null, null, vendor.getId(), Status.ENABLED, null);
        model.addAttribute("productList", productsList);
        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));

        return "/vendor/index";
    }

    @RequestMapping(value = "/list")
    public String getProduct(@AuthenticationPrincipal Vendor vendor,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer categoryId, Model model) {

        List<Product> productsList = productService.find(name, categoryId, vendor.getId(), Status.ENABLED, null);
        model.addAttribute("productList", productsList);

        return "/vendor/list";
    }

    @GetMapping("/save")
    public String saveOrUpdateProduct(@RequestParam(required = false) Integer id, Model model) {

        model.addAttribute("title", "Product:");

        if (id != null && id != 0) {
            model.addAttribute("productForm", new ProductForm(productService.getOne(id)));
        } else {
            model.addAttribute("productForm", new ProductForm());
        }
        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));

        return "vendor/saveProduct";
    }

    @PostMapping("/save")
    public String saveOrUpdateProduct(@Valid @ModelAttribute("productForm") ProductForm form, BindingResult result,
                                      @AuthenticationPrincipal Vendor vendor,
                                      @RequestParam("file") MultipartFile file,
                                      Model model) {

        model.addAttribute("title", "Product");
        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));

        if (file.isEmpty() || result.hasErrors()) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "Please fill out the form!"));
            return "vendor/saveProduct";
        } else if (!file.isEmpty() && !fileManagementService.checkImageExtension(file.getOriginalFilename())) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "File extension must be .jpg or .png!"));
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
        productService.saveOrUpdate(product);

        if (file != null) {
            String fileFullName = fileManagementService.createFile(file, "product", product.getId());

            if (fileFullName != null) {
                product.setImage(fileFullName);
                productService.saveOrUpdate(product);
                model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.uploaded"));
            }
        }
        return "redirect:/vendor/product/all";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(required = true) Integer id) {

        productService.delete(id);

        return "redirect:/vendor/product/all";
    }
}
