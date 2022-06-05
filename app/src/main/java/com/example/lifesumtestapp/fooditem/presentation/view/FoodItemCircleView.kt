package com.example.lifesumtestapp.fooditem.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.databinding.FoodItemCircleLayoutBinding

class FoodItemCircleView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { FoodItemCircleLayoutBinding.bind(this) }

    init {
        inflate(context, R.layout.food_item_circle_layout, this)
    }

    fun populate(model: FoodItemCircleModel) {
        binding.foodItemTitle.text = model.title
        binding.foodItemCaloriesNum.text = model.calories.toString()
    }
}