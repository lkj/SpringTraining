package xyz.lokaj.productPlacement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lokaj.productPlacement.exception.RecordNotFoundException;
import xyz.lokaj.productPlacement.model.Product;

import java.util.List;

@Service //dlaczego Service? co mi ta adnotacja daje
class ProductService {

    @Autowired
    private ProductsRepository productRepository;

    Product readById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }
    
    List<Product> readAll() {
        return productRepository.findAll();
    }

    Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> { //w miejsce zmiennej/parametru product wpada to co wyciągnie fukcja findById
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        })
        .orElseGet(() -> { //tu dbsługujemy sytuację jeśli findById nie wyciągnie nic
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        });
    }

    void deleteProduct(Long id) {
        productRepository.deleteById(id);//. orElseThrow(() -> new RecordNotFoundException(id));

    }
}
