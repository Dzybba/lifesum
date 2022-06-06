package com.example.lifesumtestapp.fooditemdetails.presentation.model

class FoodDetailViewModelState(
    val detailsList: List<Detail>
) {

    class Detail(
        val name: String,
        val calories: Int,
        val type: DetailType,
        val percent: Int? = null,
        val value: Float? = null
    )

    enum class DetailType {
        UNSATURATED_FAT,
        FIBER,
        CHOLESTEROL,
        SUGAR,
        POTASSIUM
    }
}