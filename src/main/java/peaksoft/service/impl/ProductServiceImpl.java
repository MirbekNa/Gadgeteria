package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.ProductResponse;
import peaksoft.entity.Product;
import peaksoft.repository.ProductRepository;
import peaksoft.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        Product savedProduct = productRepository.save(product);
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(savedProduct, productResponse);
        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            BeanUtils.copyProperties(productRequest, product);
            Product updatedProduct = productRepository.save(product);
            ProductResponse productResponse = new ProductResponse();
            BeanUtils.copyProperties(updatedProduct, productResponse);
            return productResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductResponse productResponse = new ProductResponse();
            BeanUtils.copyProperties(product, productResponse);
            return productResponse;
        } else {
            return null;
        }
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    BeanUtils.copyProperties(product, productResponse);
                    return productResponse;
                })
                .collect(Collectors.toList());
    }
}
