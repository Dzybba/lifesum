package com.example.lifesumtestapp.fooditem.data.repository

import com.example.lifesumtestapp.fooditem.data.service.FoodipediaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodipediaRepository
@Inject constructor(
    private val service: FoodipediaService
) {

    suspend fun getFoodItem(foodId: Int) = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = service.getFoodItem(foodId)
            Result.success(result)
        } catch (th: Throwable) {
            Result.failure(th)
        }
    }
}