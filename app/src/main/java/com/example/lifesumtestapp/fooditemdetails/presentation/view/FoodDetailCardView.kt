package com.example.lifesumtestapp.fooditemdetails.presentation.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.databinding.FoodDetailsCardContentLayoutBinding

class FoodDetailCardView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FoodDetailsCardContentLayoutBinding.bind(
            this
        )
    }

    init {
        inflate(context, R.layout.food_details_card_content_layout, this)
    }

    fun populate(model: FoodDetailCardModel) {
        binding.foodDetailsTitle.text = model.title
        binding.foodDetailsSubtitle.text = model.subtitle
        binding.foodDetailsProgress.isVisible = model.progress != null
        if (model.progress != null) {
            binding.foodDetailsProgress.progress = model.progress
        }
        binding.foodDetailsProgressText.isVisible = model.progress != null
        binding.foodDetailsProgressText.text = model.progressText
        val gradientDrawable = GradientDrawable()
        gradientDrawable.colors = intArrayOf(model.startColor, model.endColor)
        binding.foodDetailsContainer.background = gradientDrawable
    }
}