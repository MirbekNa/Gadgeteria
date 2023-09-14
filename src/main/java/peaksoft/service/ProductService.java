package peaksoft.service;

import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.ProductResponse;
import peaksoft.entity.Product;

import java.util.List;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(Long productId, ProductRequest productRequest);
    void deleteProduct(Long productId);
    ProductResponse getProductById(Long productId);
    List<ProductResponse> getAllProducts();
}

