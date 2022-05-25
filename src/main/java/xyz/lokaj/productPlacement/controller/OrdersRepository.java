package xyz.lokaj.productPlacement.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.lokaj.productPlacement.model.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
}
