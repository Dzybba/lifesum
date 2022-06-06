package com.example.lifesumtestapp.fooditemdetails.presentation.view

import androidx.annotation.ColorInt

data class FoodDetailCardModel(
    val title: String,
    val subtitle: String,
    @ColorInt val startColor: Int,
    @ColorInt val endColor: Int,
    val progress: Int? = null,
    val progressText: String? = null
)