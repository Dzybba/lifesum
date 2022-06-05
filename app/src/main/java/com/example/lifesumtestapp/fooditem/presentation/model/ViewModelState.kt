package com.example.lifesumtestapp.fooditem.presentation.model

sealed class ViewModelState {

    object LoadingState : ViewModelState()

    object ErrorState : ViewModelState()

    data class LoadedState(
        val foodItem: FoodItemModel
    ) : ViewModelState()
}