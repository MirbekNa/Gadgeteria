package peaksoft.service;

import peaksoft.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(Brand brand);
    Brand updateBrand(Long brandId, Brand brand);
    void deleteBrand(Long brandId);
    Brand getBrandById(Long brandId);
    List<Brand> getAllBrands();
}
