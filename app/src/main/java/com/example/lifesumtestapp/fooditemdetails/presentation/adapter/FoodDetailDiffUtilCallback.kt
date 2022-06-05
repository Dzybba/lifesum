package com.example.lifesumtestapp.fooditemdetails.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.lifesumtestapp.fooditemdetails.presentation.view.FoodDetailCardModel

class FoodDetailDiffUtilCallback : DiffUtil.ItemCallback<FoodDetailCardModel>() {
    override fun areItemsTheSame(
        oldItem: FoodDetailCardModel,
        newItem: FoodDetailCardModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: FoodDetailCardModel,
        newItem: FoodDetailCardModel
    ): Boolean {
        return oldItem == newItem
    }
}