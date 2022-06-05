package com.example.lifesumtestapp.fooditem.presentation.mapper

import android.content.Context
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.core.ApplicationContext
import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import com.example.lifesumtestapp.fooditem.domain.GetFoodComponentPercentUseCase
import com.example.lifesumtestapp.fooditem.presentation.model.FoodItemModel
import com.example.lifesumtestapp.fooditem.presentation.view.FoodCompositionItemModel
import com.example.lifesumtestapp.fooditem.presentation.view.FoodItemCircleModel
import javax.inject.Inject

class ResponseToFoodItemModelMapper
@Inject constructor(
    @ApplicationContext private val context: Context,
    private val getFoodComponentPercentUseCase: GetFoodComponentPercentUseCase
) {

    fun map(response: FoodItemResponse): FoodItemModel {
        val circleModel = FoodItemCircleModel(
            title = response.response.title,
            calories = response.response.calories
        )
        val carbsModel = getCompositionItemModel(
            title = context.getString(R.string.food_composition_carbs).toUpperCase(),
            targetComponent = response.response.carbs,
            secondComponent = response.response.protein,
            thirdComponent = response.response.fat
        )
        val proteinModel = getCompositionItemModel(
            title = context.getString(R.string.food_composition_protein).toUpperCase(),
            targetComponent = response.response.protein,
            secondComponent = response.response.carbs,
            thirdComponent = response.response.fat
        )
        val fatModel =  getCompositionItemModel(
            title = context.getString(R.string.food_composition_fat).toUpperCase(),
            targetComponent = response.response.fat,
            secondComponent = response.response.carbs,
            thirdComponent = response.response.protein
        )
        return FoodItemModel(
            foodItemCircleModel = circleModel,
            carbs = carbsModel,
            protein = proteinModel,
            fat = fatModel
        )
    }

    private fun getCompositionItemModel(
        title: String,
        targetComponent: Float,
        secondComponent: Float,
        thirdComponent: Float
    ): FoodCompositionItemModel {
        val percent = getFoodComponentPercentUseCase.getPercent(
            targetComponent = targetComponent,
            secondComponent = secondComponent,
            thirdComponent = thirdComponent
        )
        return FoodCompositionItemModel(
            title = title,
            percent = "$percent%"
        )
    }
}