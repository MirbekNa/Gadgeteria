package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    }
