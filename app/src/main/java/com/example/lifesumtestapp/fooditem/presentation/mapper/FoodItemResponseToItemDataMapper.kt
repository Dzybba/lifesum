package com.example.lifesumtestapp.fooditem.presentation.mapper

import com.example.lifesumtestapp.fooditem.domain.model.FoodItemModel
import com.example.lifesumtestapp.fooditem.presentation.model.ViewModelState
import javax.inject.Inject

class FoodItemResponseToItemDataMapper
@Inject constructor() {

    fun map(model: FoodItemModel): ViewModelState.LoadedState.FoodItemData {
        return ViewModelState.LoadedState.FoodItemData(
            title = model.title,
            calories = model.calories,
            carbs = model.carbs,
            protein = model.protein,
            fat = model.fat,
        )
    }
}