package xyz.lokaj.productPlacement;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product, linkTo(methodOn(ProductRESTController.class).getProduct(product.getId())).withSelfRel(), linkTo(methodOn(ProductRESTController.class).getAllProducts()).withRel("products"));
    }

}
