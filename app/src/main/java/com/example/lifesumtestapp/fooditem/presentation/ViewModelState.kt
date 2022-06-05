package com.example.lifesumtestapp.fooditem.presentation

import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse

sealed class ViewModelState {

    object LoadingState : ViewModelState()

    object ErrorState : ViewModelState()

    data class LoadedState(
        val foodItem: FoodItemResponse
    ) : ViewModelState()
}