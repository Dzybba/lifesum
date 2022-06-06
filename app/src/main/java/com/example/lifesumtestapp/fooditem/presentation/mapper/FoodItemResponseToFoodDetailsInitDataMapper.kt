package com.example.lifesumtestapp.fooditem.presentation.mapper

import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailInitialData
import javax.inject.Inject

class FoodItemResponseToFoodDetailsInitDataMapper
@Inject constructor() {

    fun map(response: FoodItemResponse): FoodDetailInitialData {
        val dto = response.response
        return FoodDetailInitialData(
            title = dto.title,
            calories = dto.calories,
            carbs = dto.carbs,
            protein = dto.protein,
            fat = dto.fat,
            saturatedfat = dto.saturatedfat,
            unsaturatedfat = dto.unsaturatedfat,
            fiber = dto.fiber,
            cholesterol = dto.cholesterol,
            sugar = dto.sugar,
            sodium = dto.sodium,
            potassium = dto.potassium,
            gramsperserving = dto.gramsperserving,
            pcstext = dto.pcstext
        )
    }
}