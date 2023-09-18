package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoProduct.AllProductRequest;
import peaksoft.dto.dtoProduct.ProductRequest;
import peaksoft.dto.dtoProduct.ProductResponse;
import peaksoft.enums.Category;
import peaksoft.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "ProductApi")
public class ProductAPI {
    private final ProductService service;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getAllProducts")
    public List<AllProductRequest> getAllProducts(@RequestParam String category, @RequestParam int price) {
        Category disiredCategory = Category.valueOf(category.toUpperCase());
        return service.getAllProducts(disiredCategory, price);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveProduct")
    public SimpleResponse saveProduct(@RequestBody ProductRequest productRequest,@RequestParam Long brandId) {
        return service.saveProduct(productRequest, brandId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public SimpleResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return service.updateProduct(id, productRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        return service.getProductById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteProduct(@PathVariable Long id){
        return service.deleteProductById(id);
    }
}
