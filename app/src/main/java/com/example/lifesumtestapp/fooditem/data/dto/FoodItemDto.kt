package com.example.lifesumtestapp.fooditem.data.dto

data class FoodItemDto(
    val title: String,
    val calories: Int,
    val carbs: Float,
    val protein: Float,
    val fat: Float,
    val saturatedfat: Float,
    val unsaturatedfat: Float,
    val fiber: Float, // клетчатка
    val cholesterol: Float, // холестерин
    val sugar: Float, // сахар
    val sodium: Float, // натрий
    val potassium: Float, // калий
    val gramsperserving: Float,
    val pcstext: String
)