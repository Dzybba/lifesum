package com.example.lifesumtestapp.fooditemdetails.presentation.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.cardview.widget.CardView
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.databinding.FoodDetailsCardLayoutBinding

class FoodDetailCardView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { FoodDetailsCardLayoutBinding.bind(this) }

    init {
        inflate(context, R.layout.food_details_card_layout, this)
    }

    fun populate(model: FoodDetailCardModel) {
        binding.foodDetailsTitle.text = model.title
        binding.foodDetailsSubtitle.text = model.subtitle
        binding.foodDetailsProgress.progress = model.progress
        binding.foodDetailsProgressText.text = model.progressText
        val gradientDrawable = GradientDrawable()
        gradientDrawable.colors = intArrayOf(model.startColor, model.endColor)
        binding.foodDetailsContainer.background = gradientDrawable
    }
}