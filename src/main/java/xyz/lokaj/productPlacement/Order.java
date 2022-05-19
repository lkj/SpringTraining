package xyz.lokaj.productPlacement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue
    private Long Id;

    @GeneratedValue
    private Long billTo; //narazie zlać pole i nie komplikować sobie sytuacji

    @OneToMany(mappedBy = "orderId")
    private ArrayList<Order> orderDetails;
    private Date placementDate;

    private OrderState state;

    public Order() {
    }

    public Order(Long billTo, ArrayList<Order> orderDetails, Date placementDate, OrderState orderState) {
        this.billTo = billTo;
        this.orderDetails = orderDetails;
        this.placementDate = placementDate;
        this.state = orderState;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getBillTo() {
        return billTo;
    }

    public void setBillTo(Long billTo) {
        this.billTo = billTo;
    }

    public ArrayList<Order> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<Order> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

}
