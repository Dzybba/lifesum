package com.example.lifesumtestapp.fooditem.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lifesumtestapp.fooditem.data.repository.FoodipediaRepository
import com.example.lifesumtestapp.fooditem.domain.GetRandomFoodItemUseCase
import com.example.lifesumtestapp.fooditem.presentation.mapper.ResponseToFoodItemModelMapper
import com.example.lifesumtestapp.fooditem.presentation.model.ViewModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodItemViewModel(
    private val getRandomFoodItemUseCase: GetRandomFoodItemUseCase,
    private val responseToFoodItemModelMapper: ResponseToFoodItemModelMapper
): ViewModel() {

    private val _uiState = MutableStateFlow<ViewModelState>(ViewModelState.LoadingState)
    val uiState: StateFlow<ViewModelState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = ViewModelState.LoadingState
            val result = getRandomFoodItemUseCase.getRandomFoodItem()
            _uiState.value = if (result.isSuccess) {
                val model = responseToFoodItemModelMapper.map(result.getOrThrow())
                ViewModelState.LoadedState(model)
            } else {
                ViewModelState.ErrorState
            }
        }
    }

    fun reload() {
        loadData()
    }

    class Factory
    @Inject constructor(
        owner: SavedStateRegistryOwner,
        private val getRandomFoodItemUseCase: GetRandomFoodItemUseCase,
        private val responseToFoodItemModelMapper: ResponseToFoodItemModelMapper
    ) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return FoodItemViewModel(getRandomFoodItemUseCase, responseToFoodItemModelMapper) as T
        }
    }
}