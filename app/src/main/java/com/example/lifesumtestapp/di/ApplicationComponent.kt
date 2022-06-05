package com.example.lifesumtestapp.di

import android.content.Context
import com.example.lifesumtestapp.core.CoreProvider
import com.example.lifesumtestapp.core.NetworkProvider
import com.example.lifesumtestapp.core.ProvidersAccumulator
import com.example.lifesumtestapp.coreimpl.di.CoreComponent
import com.example.lifesumtestapp.coreimpl.di.NetworkComponent
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        CoreProvider::class,
        NetworkProvider::class
    ]
)
@Singleton
interface ApplicationComponent: ProvidersAccumulator {

    companion object {

        fun create(context: Context): ProvidersAccumulator {
            val coreProvider = CoreComponent.create(context)
            val networkProvider = NetworkComponent.create()
            return DaggerApplicationComponent.factory()
                .create(
                    coreProvider,
                    networkProvider
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            coreProvider: CoreProvider,
            networkProvider: NetworkProvider,
        ): ApplicationComponent
    }
}