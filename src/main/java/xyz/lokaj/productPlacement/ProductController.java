package xyz.lokaj.productPlacement;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    CollectionModel<EntityModel<Product>> getAllProducts() {
        List<EntityModel<Product>> products = productService.readAll().stream().map(product -> EntityModel.of(product,
            linkTo(methodOn(ProductController.class).getProduct(product.getId())).withSelfRel(),
            linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"))).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @GetMapping("/product/{id}")
    EntityModel<Product> getProduct(@PathVariable Long id) {
        Product product = productService.readById(id);
        return EntityModel.of(product, linkTo(methodOn(ProductController.class).getProduct(id)).withSelfRel(), linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
    }

    @PostMapping("/product")
    Product addProduct(@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @PutMapping("/product/{id}")
    Product replaceProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        return productService.updateProduct(id, newProduct);
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}


