package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.response.FavoriteResponse;
import peaksoft.entity.Favorite;
import peaksoft.repository.FavoriteRepository;
import peaksoft.service.FavoriteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public FavoriteResponse createFavorite(FavoriteRequest favoriteRequest) {
        Favorite favorite = new Favorite();
        BeanUtils.copyProperties(favoriteRequest, favorite);
        Favorite savedFavorite = favoriteRepository.save(favorite);
        FavoriteResponse favoriteResponse = new FavoriteResponse();
        BeanUtils.copyProperties(savedFavorite, favoriteResponse);
        return favoriteResponse;
    }

    @Override
    public FavoriteResponse updateFavorite(Long favoriteId, FavoriteRequest favoriteRequest) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(favoriteId);
        if (optionalFavorite.isPresent()) {
            Favorite favorite = optionalFavorite.get();
            BeanUtils.copyProperties(favoriteRequest, favorite);
            Favorite updatedFavorite = favoriteRepository.save(favorite);
            FavoriteResponse favoriteResponse = new FavoriteResponse();
            BeanUtils.copyProperties(updatedFavorite, favoriteResponse);
            return favoriteResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    @Override
    public FavoriteResponse getFavoriteById(Long favoriteId) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(favoriteId);
        if (optionalFavorite.isPresent()) {
            Favorite favorite = optionalFavorite.get();
            FavoriteResponse favoriteResponse = new FavoriteResponse();
            BeanUtils.copyProperties(favorite, favoriteResponse);
            return favoriteResponse;
        } else {
            return null;
        }
    }

    @Override
    public List<FavoriteResponse> getAllFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();
        return favorites.stream()
                .map(favorite -> {
                    FavoriteResponse favoriteResponse = new FavoriteResponse();
                    BeanUtils.copyProperties(favorite, favoriteResponse);
                    return favoriteResponse;
                })
                .collect(Collectors.toList());
    }
}
