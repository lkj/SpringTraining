package xyz.lokaj.productPlacement.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.lokaj.productPlacement.model.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
}
