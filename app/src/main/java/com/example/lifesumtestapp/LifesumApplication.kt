package com.example.lifesumtestapp

import android.app.Application
import com.example.lifesumtestapp.core.ProvidersAccumulator
import com.example.lifesumtestapp.core.ProvidersHolder
import com.example.lifesumtestapp.di.ApplicationComponent

class LifesumApplication : Application(), ProvidersHolder {

    companion object {

        private var providersAccumulator: ProvidersAccumulator? = null
    }

    override fun getProvidersAccumulator(): ProvidersAccumulator {
        return providersAccumulator ?: ApplicationComponent.create(this).also {
            providersAccumulator = it
        }
    }
}
