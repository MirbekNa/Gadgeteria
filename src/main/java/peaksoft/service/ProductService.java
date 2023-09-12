package peaksoft.service;

import peaksoft.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
}
