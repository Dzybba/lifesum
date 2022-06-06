package com.example.lifesumtestapp.fooditemdetails.presentation.mapper

import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailInitialData
import com.example.lifesumtestapp.fooditemdetails.presentation.model.FoodDetailViewModelState
import javax.inject.Inject

class FoodDetailsInitDataToDetailsListMapper
@Inject constructor() {

    fun map(foodDetailInitialData: FoodDetailInitialData): List<FoodDetailViewModelState.Detail> {
        return listOf(
            getFatSaturationDetail(foodDetailInitialData),
            getFiber(foodDetailInitialData),
            getCholesterol(foodDetailInitialData),
            getSugar(foodDetailInitialData),
            getPotassium(foodDetailInitialData)
        )
    }

    private fun getFatSaturationDetail(foodDetailInitialData: FoodDetailInitialData): FoodDetailViewModelState.Detail {
        val unsaturatedFatPercent = foodDetailInitialData.unsaturatedfat * 100 /
                (foodDetailInitialData.unsaturatedfat + foodDetailInitialData.saturatedfat)
        return FoodDetailViewModelState.Detail(
            name = foodDetailInitialData.title,
            calories = foodDetailInitialData.calories,
            type = FoodDetailViewModelState.DetailType.UNSATURATED_FAT,
            percent = unsaturatedFatPercent.toInt()
        )
    }

    private fun getFiber(foodDetailInitialData: FoodDetailInitialData): FoodDetailViewModelState.Detail {
        return FoodDetailViewModelState.Detail(
            name = foodDetailInitialData.title,
            calories = foodDetailInitialData.calories,
            type = FoodDetailViewModelState.DetailType.FIBER,
            value = foodDetailInitialData.fiber
        )
    }

    private fun getCholesterol(foodDetailInitialData: FoodDetailInitialData): FoodDetailViewModelState.Detail {
        return FoodDetailViewModelState.Detail(
            name = foodDetailInitialData.title,
            calories = foodDetailInitialData.calories,
            type = FoodDetailViewModelState.DetailType.CHOLESTEROL,
            value = foodDetailInitialData.cholesterol
        )
    }

    private fun getSugar(foodDetailInitialData: FoodDetailInitialData): FoodDetailViewModelState.Detail {
        return FoodDetailViewModelState.Detail(
            name = foodDetailInitialData.title,
            calories = foodDetailInitialData.calories,
            type = FoodDetailViewModelState.DetailType.SUGAR,
            value = foodDetailInitialData.sugar
        )
    }

    private fun getPotassium(foodDetailInitialData: FoodDetailInitialData): FoodDetailViewModelState.Detail {
        return FoodDetailViewModelState.Detail(
            name = foodDetailInitialData.title,
            calories = foodDetailInitialData.calories,
            type = FoodDetailViewModelState.DetailType.POTASSIUM,
            value = foodDetailInitialData.potassium
        )
    }
}