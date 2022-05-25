package xyz.lokaj.productPlacement.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import xyz.lokaj.productPlacement.model.Order;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<Order> toModel(Order order) {
        EntityModel<Order> orderModel =  EntityModel.of(order,
                linkTo(methodOn(OrderRESTController.class).getOrder(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderRESTController.class).getAllOrders()).withRel("orders"));

        if (!order.isLocked()) {
            orderModel.add(linkTo(methodOn(OrderRESTController.class).cancelOrder(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderRESTController.class).completeOrder(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}
