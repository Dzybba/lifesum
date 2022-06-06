package com.example.lifesumtestapp.fooditem.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import com.example.lifesumtestapp.fooditem.domain.GetRandomFoodItemUseCase
import com.example.lifesumtestapp.fooditem.presentation.mapper.FoodItemResponseToFoodDetailsInitDataMapper
import com.example.lifesumtestapp.fooditem.presentation.mapper.FoodItemResponseToItemDataMapper
import com.example.lifesumtestapp.fooditem.presentation.model.ViewModelState
import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailInitialData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodItemViewModel(
    private val getRandomFoodItemUseCase: GetRandomFoodItemUseCase,
    private val responseToFoodItemDataMapper: FoodItemResponseToItemDataMapper,
    private val foodItemResponseToFoodDetailsMapper: FoodItemResponseToFoodDetailsInitDataMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<ViewModelState>(ViewModelState.LoadingState)
    val uiState: StateFlow<ViewModelState> = _uiState

    private val _openDetailsFlow = MutableSharedFlow<FoodDetailInitialData>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val openDetailsFlow = _openDetailsFlow.asSharedFlow()

    private var lastResponse: FoodItemResponse? = null

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = ViewModelState.LoadingState
            val result = getRandomFoodItemUseCase.getRandomFoodItem()
            _uiState.value = if (result.isSuccess) {
                lastResponse = result.getOrThrow()
                val model = responseToFoodItemDataMapper.map(lastResponse!!)
                ViewModelState.LoadedState(model)
            } else {
                ViewModelState.ErrorState
            }
        }
    }

    fun reload() {
        loadData()
    }

    fun onDetailsButtonClicked() {
        val response = lastResponse ?: return
        val initData = foodItemResponseToFoodDetailsMapper.map(response)
        _openDetailsFlow.tryEmit(initData)
    }

    class Factory
    @Inject constructor(
        owner: SavedStateRegistryOwner,
        private val getRandomFoodItemUseCase: GetRandomFoodItemUseCase,
        private val responseToFoodItemDataMapper: FoodItemResponseToItemDataMapper,
        private val foodItemResponseToFoodDetailsMapper: FoodItemResponseToFoodDetailsInitDataMapper
    ) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return FoodItemViewModel(
                getRandomFoodItemUseCase,
                responseToFoodItemDataMapper,
                foodItemResponseToFoodDetailsMapper
            ) as T
        }
    }
}