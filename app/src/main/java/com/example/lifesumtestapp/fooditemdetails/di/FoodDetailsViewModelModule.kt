package com.example.lifesumtestapp.fooditemdetails.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailsViewModel
import dagger.Binds
import dagger.Module

@Module
interface FoodDetailsViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: FoodDetailsViewModel.Factory): AbstractSavedStateViewModelFactory
}