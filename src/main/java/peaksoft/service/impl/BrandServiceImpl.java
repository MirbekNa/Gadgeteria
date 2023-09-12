package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Brand;
import peaksoft.repository.BrandRepository;
import peaksoft.service.BrandService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;


    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long brandId, Brand brand) {
        Optional<Brand> existingBrand = brandRepository.findById(brandId);
        if (existingBrand.isPresent()) {
            Brand updatedBrand = existingBrand.get();
            updatedBrand.setBrandName(brand.getBrandName());
            updatedBrand.setImage(brand.getImage());
            return brandRepository.save(updatedBrand);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandRepository.findById(brandId).orElse(null);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
