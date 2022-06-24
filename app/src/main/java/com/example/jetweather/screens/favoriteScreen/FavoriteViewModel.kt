package com.example.jetweather.screens.favoriteScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.model.Favorite
import com.example.jetweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getFavorites().distinctUntilChanged().collect() {
                if (it.isNullOrEmpty()) {
                    Log.d("Empty Favorites", "Nothing Found")
                }
                _favList.value = it
            }
        }
    }

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            weatherRepository.insertFavCity(favorite = favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.deleteFavCity(
                city = favorite.city
            )
        }
    }
}