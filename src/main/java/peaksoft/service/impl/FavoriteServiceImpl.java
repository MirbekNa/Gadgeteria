package peaksoft.service.impl;
import org.springframework.stereotype.Service;
import peaksoft.entity.Favorite;
import peaksoft.repository.FavoriteRepository;
import peaksoft.service.FavoriteService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;


    @Override
    public Favorite createFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    @Override
    public Favorite updateFavorite(Long id, Favorite favorite) {
        Optional<Favorite> existingFavorite = favoriteRepository.findById(id);
        if (existingFavorite.isPresent()) {
            Favorite updatedFavorite = existingFavorite.get();
            updatedFavorite.setUser(favorite.getUser());
            updatedFavorite.setProduct(favorite.getProduct());
            return favoriteRepository.save(updatedFavorite);
        } else {
            return null;
        }
    }

    @Override
    public Favorite getFavoriteById(Long favoriteId) {
        return favoriteRepository.findById(favoriteId).orElse(null);
    }

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }
}
