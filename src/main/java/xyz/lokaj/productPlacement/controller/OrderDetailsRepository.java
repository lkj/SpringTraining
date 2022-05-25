package xyz.lokaj.productPlacement.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.lokaj.productPlacement.model.OrderDetail;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long> {
}
