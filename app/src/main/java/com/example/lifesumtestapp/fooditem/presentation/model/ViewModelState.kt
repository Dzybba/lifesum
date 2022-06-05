package com.example.lifesumtestapp.fooditem.presentation.model

sealed class ViewModelState {

    object LoadingState : ViewModelState()

    object ErrorState : ViewModelState()

    class LoadedState(
        val foodItemData: FoodItemData
    ) : ViewModelState() {

        class FoodItemData(
            val title: String,
            val calories: Int,
            val carbs: Float,
            val protein: Float,
            val fat: Float
        )
    }
}