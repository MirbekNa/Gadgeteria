package peaksoft.service;

import peaksoft.entity.Basket;

import java.util.List;

public interface BasketService {
    Basket createBasket(Basket basket);
    Basket updateBasket(Long basketId, Basket basket);
    void deleteBasket(Long basketId);
    Basket getBasketById(Long basketId);
    List<Basket> getAllBaskets();
}
