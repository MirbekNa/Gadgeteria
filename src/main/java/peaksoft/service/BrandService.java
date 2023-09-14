package peaksoft.service;

import peaksoft.dto.request.BrandRequest;
import peaksoft.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandResponse createBrand(BrandRequest brandRequest);
    BrandResponse updateBrand(Long brandId, BrandRequest brandRequest);
    void deleteBrand(Long brandId);
    BrandResponse getBrandById(Long brandId);
    List<BrandResponse> getAllBrands();
}
