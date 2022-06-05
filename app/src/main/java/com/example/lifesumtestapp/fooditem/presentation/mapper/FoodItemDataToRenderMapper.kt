package com.example.lifesumtestapp.fooditem.presentation.mapper

import android.content.res.Resources
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.fooditem.domain.GetFoodComponentPercentUseCase
import com.example.lifesumtestapp.fooditem.presentation.model.FoodItemRender
import com.example.lifesumtestapp.fooditem.presentation.model.ViewModelState
import com.example.lifesumtestapp.fooditem.presentation.view.FoodCompositionItemModel
import com.example.lifesumtestapp.fooditem.presentation.view.FoodItemCircleModel
import javax.inject.Inject

class FoodItemDataToRenderMapper
@Inject constructor(
    private val getFoodComponentPercentUseCase: GetFoodComponentPercentUseCase
) {

    fun map(
        resources: Resources,
        foodItemData: ViewModelState.LoadedState.FoodItemData
    ): FoodItemRender {
        val circleModel = FoodItemCircleModel(
            title = foodItemData.title,
            calories = foodItemData.calories
        )
        val carbsModel = getCompositionItemModel(
            title = resources.getString(R.string.food_composition_carbs).toUpperCase(),
            targetComponent = foodItemData.carbs,
            secondComponent = foodItemData.protein,
            thirdComponent = foodItemData.fat
        )
        val proteinModel = getCompositionItemModel(
            title = resources.getString(R.string.food_composition_protein).toUpperCase(),
            targetComponent = foodItemData.protein,
            secondComponent = foodItemData.carbs,
            thirdComponent = foodItemData.fat
        )
        val fatModel =  getCompositionItemModel(
            title = resources.getString(R.string.food_composition_fat).toUpperCase(),
            targetComponent = foodItemData.fat,
            secondComponent = foodItemData.carbs,
            thirdComponent = foodItemData.protein
        )
        return FoodItemRender(
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