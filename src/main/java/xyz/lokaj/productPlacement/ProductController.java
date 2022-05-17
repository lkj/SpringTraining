package xyz.lokaj.productPlacement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class ProductController {

    private final ProductService service;

    ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping("/form")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @PostMapping("/form")
    public String productSubmit(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        service.createProduct(product);
        return "resultPage";
    }
}
