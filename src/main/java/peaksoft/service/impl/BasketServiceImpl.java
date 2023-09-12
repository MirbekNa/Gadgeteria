package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Basket;
import peaksoft.repository.BasketRepository;
import peaksoft.service.BasketService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;

    @Override
    public Basket createBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public Basket updateBasket(Long basketId, Basket basket) {
        Optional<Basket> existingBasket = basketRepository.findById(basketId);
        if (existingBasket.isPresent()) {
            Basket updatedBasket = existingBasket.get();
            return basketRepository.save(updatedBasket);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBasket(Long basketId) {
        basketRepository.deleteById(basketId);
    }

    @Override
    public Basket getBasketById(Long basketId) {
        return basketRepository.findById(basketId).orElse(null);
    }

    @Override
    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }
}
