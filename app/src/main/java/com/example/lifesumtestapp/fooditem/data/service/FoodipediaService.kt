package com.example.lifesumtestapp.fooditem.data.service

import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodipediaService {

    @GET("v2/foodipedia/codetest")
    suspend fun getFoodItem(
        @Query("foodid") foodId: String,
    ): FoodItemResponse
}