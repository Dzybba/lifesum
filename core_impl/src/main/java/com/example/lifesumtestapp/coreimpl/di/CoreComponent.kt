package com.example.lifesumtestapp.coreimpl.di

import android.content.Context
import com.example.lifesumtestapp.core.ApplicationContext
import com.example.lifesumtestapp.core.CoreProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface CoreComponent : CoreProvider {

    companion object {

        fun create(context: Context): CoreComponent {
            return DaggerCoreComponent.factory()
                .create(context)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance @ApplicationContext context: Context
        ): CoreComponent
    }
}
