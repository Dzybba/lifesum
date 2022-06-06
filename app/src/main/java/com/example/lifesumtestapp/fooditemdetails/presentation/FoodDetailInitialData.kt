package com.example.lifesumtestapp.fooditemdetails.presentation

import java.io.Serializable

data class FoodDetailInitialData(
    val title: String,
    val calories: Int,
    val carbs: Float,
    val protein: Float,
    val fat: Float,
    val saturatedfat: Float,
    val unsaturatedfat: Float,
    val fiber: Float,
    val cholesterol: Float,
    val sugar: Float,
    val sodium: Float,
    val potassium: Float,
    val gramsperserving: Float,
    val pcstext: String
) : Serializable {

    companion object {
        const val ARGUMENT_KEY = "food_detail_initial_data"
    }
}