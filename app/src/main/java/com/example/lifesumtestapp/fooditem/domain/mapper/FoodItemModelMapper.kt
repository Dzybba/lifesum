package com.example.lifesumtestapp.fooditem.domain.mapper

import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import com.example.lifesumtestapp.fooditem.domain.model.FoodItemModel
import javax.inject.Inject

class FoodItemModelMapper
@Inject constructor() {

    fun map(response: FoodItemResponse): FoodItemModel {
        val dto = response.response
        return FoodItemModel(
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