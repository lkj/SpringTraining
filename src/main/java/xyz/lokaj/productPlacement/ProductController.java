package xyz.lokaj.productPlacement;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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

    private final ProductService productService;

    private final ProductModelAssembler productModelAssembler;

    ProductController(ProductService service, ProductModelAssembler assembler) {
        this.productService = service;
        this.productModelAssembler = assembler;
    }

    @GetMapping("/products")
    CollectionModel<EntityModel<Product>> getAllProducts() {
        List<EntityModel<Product>> products = productService.readAll().stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @GetMapping("/product/{id}")
    EntityModel<Product> getProduct(@PathVariable Long id) {
        Product product = productService.readById(id);
        return productModelAssembler.toModel(product);
    }

    @PostMapping("/product")
    ResponseEntity<?> addProduct(@RequestBody Product newProduct) {
        EntityModel<Product> entityModel = productModelAssembler.toModel(productService.createProduct(newProduct));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping("/product/{id}")
    ResponseEntity<?> replaceProduct(@PathVariable Long id, @RequestBody Product newProduct) {

        EntityModel<Product> entityModel = productModelAssembler.toModel(productService.updateProduct(id, newProduct));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}


