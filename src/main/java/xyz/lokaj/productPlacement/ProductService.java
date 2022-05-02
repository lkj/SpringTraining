package xyz.lokaj.productPlacement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
class ProductService {

    @Autowired
    private ProductRepository productRepository;

    Product getById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return product;
    }
    
    List<Product> getAll() {
        return productRepository.findAll();
    }

    void addProduct(Product product) {
        productRepository.save(product);
    }
}
