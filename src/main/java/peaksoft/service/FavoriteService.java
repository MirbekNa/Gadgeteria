package peaksoft.service;

import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.response.FavoriteResponse;
import peaksoft.entity.Favorite;

import java.util.List;

import java.util.List;

public interface FavoriteService {
    FavoriteResponse createFavorite(FavoriteRequest favoriteRequest);
    FavoriteResponse updateFavorite(Long favoriteId, FavoriteRequest favoriteRequest);
    void deleteFavorite(Long favoriteId);
    FavoriteResponse getFavoriteById(Long favoriteId);
    List<FavoriteResponse> getAllFavorites();
}
