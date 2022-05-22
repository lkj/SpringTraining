package xyz.lokaj.productPlacement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;
    public Product() { }
    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

}
