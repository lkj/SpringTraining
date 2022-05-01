package xyz.lokaj.productPlacement;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class ProductEntity {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;

}