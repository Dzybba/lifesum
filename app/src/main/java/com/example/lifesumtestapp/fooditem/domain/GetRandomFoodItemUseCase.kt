package com.example.lifesumtestapp.fooditem.domain

import com.example.lifesumtestapp.fooditem.data.dto.FoodItemResponse
import com.example.lifesumtestapp.fooditem.data.repository.FoodipediaRepository
import javax.inject.Inject

class GetRandomFoodItemUseCase
@Inject constructor(
    private val repository: FoodipediaRepository,
    private val getRandomNumberUseCase: GetRandomNumberUseCase
){

    suspend fun getRandomFoodItem(): Result<FoodItemResponse> {
        return repository.getFoodItem(getRandomNumberUseCase.getRandomNumber())
    }
}