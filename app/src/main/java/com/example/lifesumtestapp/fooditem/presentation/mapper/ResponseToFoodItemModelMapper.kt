package com.example.lifesumtestapp.fooditem.presentation.mapper

import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import com.example.lifesumtestapp.fooditem.presentation.model.FoodItemModel
import com.example.lifesumtestapp.fooditem.presentation.view.FoodItemCircleModel
import javax.inject.Inject

class ResponseToFoodItemModelMapper
@Inject constructor() {

    fun map(response: FoodItemResponse): FoodItemModel {

        val circleModel = FoodItemCircleModel(
            response.response.title,
            response.response.calories
        )
        return FoodItemModel(circleModel)
    }
}