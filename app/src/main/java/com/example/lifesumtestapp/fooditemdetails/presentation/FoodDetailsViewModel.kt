package com.example.lifesumtestapp.fooditemdetails.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lifesumtestapp.fooditemdetails.presentation.mapper.FoodDetailsInitDataToDetailsListMapper
import com.example.lifesumtestapp.fooditemdetails.presentation.model.FoodDetailViewModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FoodDetailsViewModel(
    initialData: FoodDetailInitialData,
    foodDetailsInitDataToDetailsListMapper: FoodDetailsInitDataToDetailsListMapper
) : ViewModel() {

    private val _uiState: MutableStateFlow<FoodDetailViewModelState>

    init {
        val state = foodDetailsInitDataToDetailsListMapper.map(initialData)
        _uiState = MutableStateFlow(FoodDetailViewModelState(state))
    }

    fun getUiState(): StateFlow<FoodDetailViewModelState> {
        return _uiState
    }

    class Factory
    @Inject constructor(
        owner: SavedStateRegistryOwner,
        private val initialData: FoodDetailInitialData,
        private val foodDetailsInitDataToDetailsListMapper: FoodDetailsInitDataToDetailsListMapper
    ) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle,
        ): T {
            return FoodDetailsViewModel(
                initialData,
                foodDetailsInitDataToDetailsListMapper
            ) as T
        }
    }
}