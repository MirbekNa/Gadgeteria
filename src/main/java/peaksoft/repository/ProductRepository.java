package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoProduct.AllProductRequest;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;
import peaksoft.enums.Category;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new peaksoft.dto.dtoProduct.AllProductRequest(p.id,p.name,p.price,p.images,p.characteristic,p.isFavorite,p.madeIn,p.category) from Product p " +
            "where p.category=:category and p.price<=:price order by p.price asc")
    List<AllProductRequest> getAllProduct(Category category, int price);

    @Query ("select c.comment from Product p join p.comment c where p.id=:id")
    List<String> getAllProductComments(Long id);

    @Query ("select f from Product p join p.favorite f where p.id=:id")
    List<Favorite> getAllProductFavorites(Long id);
}
