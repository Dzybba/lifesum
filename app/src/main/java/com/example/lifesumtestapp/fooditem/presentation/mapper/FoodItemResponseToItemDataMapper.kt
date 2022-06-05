package com.example.lifesumtestapp.fooditem.presentation.mapper

import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import com.example.lifesumtestapp.fooditem.presentation.model.ViewModelState
import javax.inject.Inject

class FoodItemResponseToItemDataMapper
@Inject constructor() {

    fun map(response: FoodItemResponse): ViewModelState.LoadedState.FoodItemData {
        return ViewModelState.LoadedState.FoodItemData(
            title = response.response.title,
            calories = response.response.calories,
            carbs = response.response.carbs,
            protein = response.response.protein,
            fat = response.response.fat,
        )
    }
}