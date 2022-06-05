package com.example.lifesumtestapp.fooditem.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject

class FoodItemViewModel: ViewModel() {


    class Factory
    @Inject constructor(
        owner: SavedStateRegistryOwner,
    ) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return FoodItemViewModel() as T
        }
    }
}