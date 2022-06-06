package com.example.lifesumtestapp.fooditem.domain

import com.example.lifesumtestapp.fooditem.data.repository.FoodipediaRepository
import com.example.lifesumtestapp.fooditem.domain.mapper.FoodItemModelMapper
import com.example.lifesumtestapp.fooditem.domain.model.FoodItemModel
import javax.inject.Inject

class GetRandomFoodItemUseCase
@Inject constructor(
    private val repository: FoodipediaRepository,
    private val foodItemModelMapper: FoodItemModelMapper,
    private val getRandomNumberUseCase: GetRandomNumberUseCase
) {

    suspend fun getRandomFoodItem(): Result<FoodItemModel> {
        return repository.getFoodItem(getRandomNumberUseCase.getRandomNumber())
            .map {
                foodItemModelMapper.map(it)
            }
    }
}