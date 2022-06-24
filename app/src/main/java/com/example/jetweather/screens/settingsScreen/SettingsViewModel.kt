package com.example.jetweather.screens.settingsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.model.Favorite
import com.example.jetweather.model.UserPreferences
import com.example.jetweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private  val weatherRepository: WeatherRepository) : ViewModel() {
    private val _userPrefList = MutableStateFlow<List<UserPreferences>>(emptyList())
    val userPrefList = _userPrefList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getUserPreferences().distinctUntilChanged().collect() {
                _userPrefList.value = it
            }
        }
    }

    fun updatePreferences(preferences: UserPreferences) {
        viewModelScope.launch {
            weatherRepository.updateUserPreference(
                userPreferences = preferences
            )
        }
    }

    fun insertUserPreference(userPreferences: UserPreferences) {
        viewModelScope.launch {weatherRepository.insertUserPreference(userPreferences = userPreferences)}

    }
    suspend fun updateUserPreference(userPreferences: UserPreferences) = viewModelScope.launch{weatherRepository.updateUserPreference(userPreferences = userPreferences)}


    fun deleteAllPreferences() = viewModelScope.launch { weatherRepository.deleteAllPreferences() }
}