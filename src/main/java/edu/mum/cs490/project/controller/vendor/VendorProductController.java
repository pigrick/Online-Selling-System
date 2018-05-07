package edu.mum.cs490.project.controller.vendor;

import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.ProductForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import edu.mum.cs490.project.service.impl.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private final int PAGE_SIZE = 10;

    @GetMapping
    public String productManagement(@AuthenticationPrincipal Vendor vendor, Model model) {
        List<Product> productsList = productService.find(null, null, vendor.getId(), Status.ENABLED, null);
        model.addAttribute("productList", productsList);
        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));

        return "/vendor/index";
    }

    @RequestMapping(value = "/list")
    public String getProduct(@AuthenticationPrincipal Vendor vendor,
                             @RequestParam(required = false) String name,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(required = false) Integer categoryId, Model model) {
        Page<Product> productsList = productService.findPage(name.equals("") ? null : name, categoryId, vendor.getId(), Status.ENABLED, PageRequest.of(page-1, PAGE_SIZE));
        //List<Product> productsList = productService.find(name.equals("") ? null : name, categoryId, vendor.getId(), Status.ENABLED, null);
        model.addAttribute("result", productsList);
        model.addAttribute("productList", productsList.getContent());

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
                                      //@RequestParam("file") MultipartFile file,
                                      Model model) {

        model.addAttribute("title", "Product");
        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));

        if (result.hasErrors()) {
            model.addAttribute("message",  Message.errorOccurred);
            return "vendor/saveProduct";
        } else if (form.getFile() != null &&!form.getFile().isEmpty() && !fileManagementService.checkImageExtension(form.getFile().getOriginalFilename())) {
            result.rejectValue("image", null, "File extension must be .jpg or .png!");
            return "vendor/saveProduct";
        }

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

        if (form.getFile() != null) {
            String fileFullName = fileManagementService.createFile(form.getFile(), "product", product.getId());

            if (fileFullName != null) {
                product.setImage(fileFullName);
                productService.saveOrUpdate(product);
            }
        }
        model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.uploaded"));
        return "vendor/saveProduct";
    }

    @GetMapping("/delete")
    public Message deleteProduct(@RequestParam(required = true) Integer id, Model model) {

        productService.delete(id);

        return new Message(Message.Type.SUCCESS, "successfully.deleted");
    }
}
