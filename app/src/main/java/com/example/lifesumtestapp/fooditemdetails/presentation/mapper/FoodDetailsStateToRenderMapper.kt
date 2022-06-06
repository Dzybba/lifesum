package com.example.lifesumtestapp.fooditemdetails.presentation.mapper

import android.content.res.Resources
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.fooditemdetails.presentation.model.FoodDetailViewModelState
import com.example.lifesumtestapp.fooditemdetails.presentation.model.FoodDetailsRender
import com.example.lifesumtestapp.fooditemdetails.presentation.view.FoodDetailCardModel
import javax.inject.Inject

class FoodDetailsStateToRenderMapper
@Inject constructor() {

    fun map(resources: Resources, state: FoodDetailViewModelState): FoodDetailsRender {
        val list = state.detailsList.map {
            mapDetail(resources, it)
        }
        return FoodDetailsRender(list)
    }

    private fun mapDetail(
        resources: Resources,
        detail: FoodDetailViewModelState.Detail
    ): FoodDetailCardModel {
        val (startColor, endColor) = getColors(resources, detail.type)
        return if (detail.type == FoodDetailViewModelState.DetailType.UNSATURATED_FAT) {
            FoodDetailCardModel(
                title = detail.name,
                subtitle = resources.getString(R.string.food_detail_cals, detail.calories),
                startColor = startColor,
                endColor = endColor,
                progress = detail.percent,
                progressText = resources.getString(R.string.food_detail_unsaturated_fat, detail.percent)
            )
        } else {
            FoodDetailCardModel(
                title = getNameForType(resources, detail.type),
                subtitle = resources.getString(R.string.food_detail_mg_serv, detail.value),
                startColor = startColor,
                endColor = endColor
            )
        }
    }

    private fun getNameForType(
        resources: Resources,
        type: FoodDetailViewModelState.DetailType
    ): String {
        return when (type) {
            FoodDetailViewModelState.DetailType.UNSATURATED_FAT -> ""
            FoodDetailViewModelState.DetailType.FIBER -> resources.getString(R.string.food_detail_fiber)
            FoodDetailViewModelState.DetailType.CHOLESTEROL -> resources.getString(R.string.food_detail_cholesterol)
            FoodDetailViewModelState.DetailType.SUGAR -> resources.getString(R.string.food_detail_sugar)
            FoodDetailViewModelState.DetailType.POTASSIUM -> resources.getString(R.string.food_detail_potassium)
        }
    }

    private fun getColors(
        resources: Resources,
        type: FoodDetailViewModelState.DetailType
    ): Pair<Int, Int> {
        return when(type) {
            FoodDetailViewModelState.DetailType.UNSATURATED_FAT -> {
                resources.getColor(R.color.teal_200) to resources.getColor(R.color.teal_700)
            }
            FoodDetailViewModelState.DetailType.FIBER -> {
                resources.getColor(R.color.teal_200) to resources.getColor(R.color.teal_700)
            }
            FoodDetailViewModelState.DetailType.CHOLESTEROL -> {
                resources.getColor(R.color.teal_200) to resources.getColor(R.color.teal_700)
            }
            FoodDetailViewModelState.DetailType.SUGAR -> {
                resources.getColor(R.color.teal_200) to resources.getColor(R.color.teal_700)
            }
            FoodDetailViewModelState.DetailType.POTASSIUM -> {
                resources.getColor(R.color.teal_200) to resources.getColor(R.color.teal_700)
            }
        }
    }
}