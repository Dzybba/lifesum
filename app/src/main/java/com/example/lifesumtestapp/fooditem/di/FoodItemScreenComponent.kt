package com.example.lifesumtestapp.fooditem.di

import androidx.savedstate.SavedStateRegistryOwner
import com.example.lifesumtestapp.core.ProvidersAccumulator
import com.example.lifesumtestapp.fooditem.presentation.FoodItemFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ProvidersAccumulator::class],
    modules = [
        FoodipediaRepositoryModule::class,
        FoodItemViewModelModule::class
    ]
)
interface FoodItemScreenComponent {

    companion object {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            savedStateRegistryOwner: SavedStateRegistryOwner
        ): FoodItemScreenComponent {
            return DaggerFoodItemScreenComponent.factory()
                .create(
                    providersAccumulator,
                    savedStateRegistryOwner
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner
        ): FoodItemScreenComponent
    }

    fun inject(fragment: FoodItemFragment)
}