package com.example.lifesumtestapp.fooditem.presentation.model

import com.example.lifesumtestapp.fooditem.presentation.view.FoodCompositionItemModel
import com.example.lifesumtestapp.fooditem.presentation.view.FoodItemCircleModel

data class FoodItemRender(
    val foodItemCircleModel: FoodItemCircleModel,
    val carbs: FoodCompositionItemModel,
    val protein: FoodCompositionItemModel,
    val fat: FoodCompositionItemModel,
)