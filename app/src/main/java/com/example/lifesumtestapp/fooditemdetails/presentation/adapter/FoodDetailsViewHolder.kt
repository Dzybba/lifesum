package com.example.lifesumtestapp.fooditemdetails.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesumtestapp.fooditemdetails.presentation.view.FoodDetailCardModel
import com.example.lifesumtestapp.fooditemdetails.presentation.view.FoodDetailCardView

class FoodDetailsViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    fun onBindViewHolder(model: FoodDetailCardModel) {
        (itemView as? FoodDetailCardView)?.let {
            it.populate(model)
        }
    }
}