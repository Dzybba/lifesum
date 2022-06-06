package com.example.lifesumtestapp.fooditem.presentation.mapper

import com.example.lifesumtestapp.fooditem.domain.model.FoodItemModel
import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailInitialData
import javax.inject.Inject

class FoodItemResponseToFoodDetailsInitDataMapper
@Inject constructor() {

    fun map(model: FoodItemModel): FoodDetailInitialData {
        return FoodDetailInitialData(
            title = model.title,
            calories = model.calories,
            carbs = model.carbs,
            protein = model.protein,
            fat = model.fat,
            saturatedfat = model.saturatedfat,
            unsaturatedfat = model.unsaturatedfat,
            fiber = model.fiber,
            cholesterol = model.cholesterol,
            sugar = model.sugar,
            sodium = model.sodium,
            potassium = model.potassium,
            gramsperserving = model.gramsperserving,
            pcstext = model.pcstext
        )
    }
}