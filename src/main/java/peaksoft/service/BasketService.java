package peaksoft.service;

import peaksoft.dto.request.BasketRequest;
import peaksoft.dto.response.BasketResponse;
import peaksoft.entity.Basket;

import java.util.List;

import java.util.List;

public interface BasketService {
    BasketResponse createBasket(BasketRequest basketRequest);
    BasketResponse updateBasket(Long basketId, BasketRequest basketRequest);
    void deleteBasket(Long basketId);
    BasketResponse getBasketById(Long basketId);
    List<BasketResponse> getAllBaskets();
}
