package xyz.lokaj.productPlacement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lokaj.productPlacement.exception.IllegalOrderStateException;
import xyz.lokaj.productPlacement.exception.RecordNotFoundException;
import xyz.lokaj.productPlacement.model.Order;
import xyz.lokaj.productPlacement.model.OrderState;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository repository;

    Order readById(Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    List<Order> readAll() {
        return repository.findAll();
    }

    Order create(Order order) {
        return repository.save(order);
    }

    //zwróć dane a jak nie można to rzuć wyjątek
    //w przykładach jest że jak nie ma id to instert zamias update ale tu tak sie nie bawimy
    Order update(Long id, Order updatedOrder) {
        Order order = readById(id);
        if (order.isLocked()){
            throw new IllegalOrderStateException(id);
        } else {
            order.setDescription(updatedOrder.getDescription());
            order.setState(updatedOrder.getState());
            return repository.save(order);
        }
    }

    //a co jak są pozycje?
    void delete(Long id) {
        repository.deleteById(id);
    }

    Order cancel(Long id) {
        Order order = readById(id);
        order.setState(OrderState.CANCELED);
        return update(id, order);
    }

    Order complete(Long id) {
        Order order = readById(id);
        order.setState(OrderState.COMPLETED);
        return update(id, order);
    }
}
