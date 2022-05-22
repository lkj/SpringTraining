package xyz.lokaj.productPlacement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import xyz.lokaj.productPlacement.model.Product;

@Component
class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product, WebMvcLinkBuilder.linkTo(methodOn(ProductRESTController.class).getProduct(product.getId())).withSelfRel(), linkTo(methodOn(ProductRESTController.class).getAllProducts()).withRel("products"));
    }

}
