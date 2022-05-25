package xyz.lokaj.productPlacement.controller;

import org.aspectj.weaver.ast.Or;
import org.hibernate.EntityMode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.lokaj.productPlacement.model.Order;
import org.springframework.hateoas.mediatype.problem.Problem;

import java.nio.file.Watchable;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class OrderRESTController {

    private final OrderService service;
    private final OrderModelAssembler assembler;

    public OrderRESTController(OrderService service, OrderModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/orders")
    CollectionModel<EntityModel<Order>> getAllOrders() {
        List<EntityModel<Order>> orders = service.readAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(orders, linkTo(methodOn(OrderRESTController.class).getAllOrders()).withSelfRel());
    }

    @GetMapping("/order/{id}")
    EntityModel<Order> getOrder(@PathVariable Long id) {
        Order order = service.readById(id);
        return assembler.toModel(order);
    }

    //przetestować zachowanie w przypadku nielegalnych danych
    @PostMapping("/order")
    ResponseEntity<?> addOrder(@RequestBody Order newOrder) {
        EntityModel<Order> order = assembler.toModel(service.create(newOrder));
        return ResponseEntity
                .created(order.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(order);
    }

    @PutMapping("/order/{id}")
    ResponseEntity<?> replaceOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        EntityModel<Order> order = assembler.toModel(service.update(id, updatedOrder));
        return ResponseEntity
                .created(order.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(order);
    }

    @DeleteMapping("/order/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/order/{id}/cancel")
    ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        try {
            EntityModel<Order> order = assembler.toModel(service.cancel(id));
            return ResponseEntity.ok(order);
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Niedozwolona metoda")
                            .withDetail("Nie można anulować zamówienia które jest zablokowane"));
        }
    }

    @PutMapping("/order/{id}/complete")
    ResponseEntity<?> completeOrder(@PathVariable Long id) {
        try {
            EntityModel<Order> order = assembler.toModel(service.complete(id));
            return ResponseEntity.ok(order);
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Niedozwolona metoda")
                            .withDetail("Nie można zamknąć zamówienia które jest zablokowane"));
        }
    }

}
