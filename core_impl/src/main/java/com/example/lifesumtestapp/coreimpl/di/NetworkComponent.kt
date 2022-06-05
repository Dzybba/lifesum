package com.example.lifesumtestapp.coreimpl.di

import com.example.lifesumtestapp.core.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent : NetworkProvider {

    companion object {

        fun create(): NetworkComponent {
            return DaggerNetworkComponent.create()
        }
    }
}
