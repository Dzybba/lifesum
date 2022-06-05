package com.example.lifesumtestapp.fooditem.domain

import javax.inject.Inject

class GetFoodComponentPercentUseCase
@Inject constructor() {

    fun getPercent(
        targetComponent: Float,
        secondComponent: Float,
        thirdComponent: Float
    ): Int {
        val sum = targetComponent + secondComponent + thirdComponent
        return (targetComponent * 100 / sum).toInt()
    }
}