package com.example.lifesumtestapp.fooditem.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.databinding.FoodCompositionItemLayoutBinding

class FoodCompositionItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { FoodCompositionItemLayoutBinding.bind(this) }

    init {
        orientation = VERTICAL
        inflate(context, R.layout.food_composition_item_layout, this)
    }

    fun populate(model: FoodCompositionItemModel) {
        binding.foodCompositionTitle.text = model.title
        binding.foodCompositionPercent.text = model.percent
    }
}