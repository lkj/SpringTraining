package xyz.lokaj.productPlacement.model;

import lombok.*;
import org.springframework.lang.NonNull;
import xyz.lokaj.productPlacement.model.Product;
import xyz.lokaj.productPlacement.model.Order;

import javax.persistence.*;

@Data
@Entity
@Table(name= "order_details")
public class OrderDetail {

    @Id @GeneratedValue
    private Long id;
    @NonNull
    @ManyToOne
    private Product product;
    @NonNull
    private Integer quantity;
    @NonNull
    @ManyToOne
    private Order order;

    public OrderDetail() { }
    public OrderDetail(Product product, Integer quantity, Order order) {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
    }

}
