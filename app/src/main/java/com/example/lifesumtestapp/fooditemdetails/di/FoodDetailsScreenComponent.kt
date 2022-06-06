package com.example.lifesumtestapp.fooditemdetails.di

import androidx.savedstate.SavedStateRegistryOwner
import com.example.lifesumtestapp.core.ProvidersAccumulator
import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailInitialData
import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ProvidersAccumulator::class],
    modules = [FoodDetailsViewModelModule::class]
)
interface FoodDetailsScreenComponent {

    companion object {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            savedStateRegistryOwner: SavedStateRegistryOwner,
            foodDetailInitialData: FoodDetailInitialData
        ): FoodDetailsScreenComponent {
            return DaggerFoodDetailsScreenComponent.factory()
                .create(
                    providersAccumulator,
                    savedStateRegistryOwner,
                    foodDetailInitialData
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance foodDetailInitialData: FoodDetailInitialData
        ): FoodDetailsScreenComponent
    }

    fun inject(fragment: FoodDetailsFragment)
}