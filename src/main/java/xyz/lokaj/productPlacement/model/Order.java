package xyz.lokaj.productPlacement.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;
    private String description;
    //@GeneratedValue
    //private Long billTo; //narazie zlać pole i nie komplikować sobie sytuacji
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    private Date placementDate;
    private OrderState state;

    public Order() { }
}
