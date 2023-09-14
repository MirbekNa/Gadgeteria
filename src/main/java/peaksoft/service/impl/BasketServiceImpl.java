package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.BasketRequest;
import peaksoft.dto.response.BasketResponse;
import peaksoft.entity.Basket;
import peaksoft.repository.BasketRepository;
import peaksoft.service.BasketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    @Override
    public BasketResponse createBasket(BasketRequest basketRequest) {
        Basket basket = new Basket();
        BeanUtils.copyProperties(basketRequest, basket);
        Basket savedBasket = basketRepository.save(basket);
        BasketResponse basketResponse = new BasketResponse();
        BeanUtils.copyProperties(savedBasket, basketResponse);
        return basketResponse;
    }

    @Override
    public BasketResponse updateBasket(Long basketId, BasketRequest basketRequest) {
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            BeanUtils.copyProperties(basketRequest, basket);
            Basket updatedBasket = basketRepository.save(basket);
            BasketResponse basketResponse = new BasketResponse();
            BeanUtils.copyProperties(updatedBasket, basketResponse);
            return basketResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteBasket(Long basketId) {
        basketRepository.deleteById(basketId);
    }

    @Override
    public BasketResponse getBasketById(Long basketId) {
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            BasketResponse basketResponse = new BasketResponse();
            BeanUtils.copyProperties(basket, basketResponse);
            return basketResponse;
        } else {
            return null;
        }
    }

    @Override
    public List<BasketResponse> getAllBaskets() {
        List<Basket> baskets = basketRepository.findAll();
        return baskets.stream()
                .map(basket -> {
                    BasketResponse basketResponse = new BasketResponse();
                    BeanUtils.copyProperties(basket, basketResponse);
                    return basketResponse;
                })
                .collect(Collectors.toList());
    }
}
