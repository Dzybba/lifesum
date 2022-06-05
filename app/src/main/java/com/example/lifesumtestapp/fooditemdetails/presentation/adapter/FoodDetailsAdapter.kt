package com.example.lifesumtestapp.fooditemdetails.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.fooditemdetails.presentation.view.FoodDetailCardModel

class FoodDetailsAdapter() :
    ListAdapter<FoodDetailCardModel, FoodDetailsViewHolder>(FoodDetailDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.food_details_card_layout,
            parent,
            false
        )
        return FoodDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodDetailsViewHolder, position: Int) {
        holder.onBindViewHolder(getItem(position))
    }
}