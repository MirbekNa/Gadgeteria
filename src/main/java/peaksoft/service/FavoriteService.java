package peaksoft.service;

import peaksoft.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite createFavorite(Favorite favorite);
    void deleteFavorite(Long favoriteId);
    Favorite updateFavorite(Long id, Favorite favorite);
    Favorite getFavoriteById(Long favoriteId);
    List<Favorite> getAllFavorites();
}
