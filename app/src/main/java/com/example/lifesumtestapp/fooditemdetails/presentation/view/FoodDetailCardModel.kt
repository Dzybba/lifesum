package com.example.lifesumtestapp.fooditemdetails.presentation.view

import androidx.annotation.ColorInt

class FoodDetailCardModel(
    val title: String,
    val subtitle: String,
    val progress: Int,
    val progressText: String,
    @ColorInt val startColor: Int,
    @ColorInt val endColor: Int
)