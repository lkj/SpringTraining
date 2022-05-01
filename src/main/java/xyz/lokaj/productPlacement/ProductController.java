package xyz.lokaj.productPlacement;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/product")
    void addProduct(@RequestBody Product newProduct) {
        productService.addProduct(newProduct);
    }
}


