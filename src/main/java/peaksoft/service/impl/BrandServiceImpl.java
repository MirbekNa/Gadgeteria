package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.BrandRequest;
import peaksoft.dto.request.BasketRequest;
import peaksoft.dto.response.BrandResponse;
import peaksoft.dto.response.BasketResponse;
import peaksoft.entity.Brand;
import peaksoft.entity.Basket;
import peaksoft.repository.BrandRepository;
import peaksoft.repository.BasketRepository;
import peaksoft.service.BrandService;
import peaksoft.service.BasketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandResponse createBrand(BrandRequest brandRequest) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandRequest, brand);
        Brand savedBrand = brandRepository.save(brand);
        BrandResponse brandResponse = new BrandResponse();
        BeanUtils.copyProperties(savedBrand, brandResponse);
        return brandResponse;
    }

    @Override
    public BrandResponse updateBrand(Long brandId, BrandRequest brandRequest) {
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            BeanUtils.copyProperties(brandRequest, brand);
            Brand updatedBrand = brandRepository.save(brand);
            BrandResponse brandResponse = new BrandResponse();
            BeanUtils.copyProperties(updatedBrand, brandResponse);
            return brandResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public BrandResponse getBrandById(Long brandId) {
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            BrandResponse brandResponse = new BrandResponse();
            BeanUtils.copyProperties(brand, brandResponse);
            return brandResponse;
        } else {
            return null;
        }
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(brand -> {
                    BrandResponse brandResponse = new BrandResponse();
                    BeanUtils.copyProperties(brand, brandResponse);
                    return brandResponse;
                })
                .collect(Collectors.toList());
    }
}

