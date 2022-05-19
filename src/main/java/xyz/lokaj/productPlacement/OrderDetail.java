package xyz.lokaj.productPlacement;

import javax.persistence.*;

@Entity
@Table(name= "orderDetail")
public class OrderDetail {

    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private Product product;
    private Integer quantity;

    @ManyToOne
    private Order order;

    public OrderDetail() { }
    public OrderDetail(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productId) {
        this.product = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
