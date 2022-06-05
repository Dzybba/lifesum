package com.example.lifesumtestapp.fooditem.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import com.example.lifesumtestapp.fooditem.presentation.FoodItemViewModel
import dagger.Binds
import dagger.Module

@Module
interface FoodItemViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: FoodItemViewModel.Factory): AbstractSavedStateViewModelFactory
}