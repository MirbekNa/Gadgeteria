package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoBrand.BrandRequest;
import peaksoft.dto.dtoBrand.BrandResponse;
import peaksoft.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
@Tag(name = "BrandApi")
public class BrandAPI {
    private final BrandService service;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<BrandResponse> getAllBrands(){
        return service.getAllBrands();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveBrand(@RequestBody BrandRequest brandRequest){
        return service.saveBrand(brandRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public SimpleResponse assignProductToBrand(@RequestParam Long productId,@PathVariable Long id){
        return service.assignProductToBrand(productId,id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteBrand(@PathVariable Long id){
        return service.deleteBrandById(id);
    }

}
