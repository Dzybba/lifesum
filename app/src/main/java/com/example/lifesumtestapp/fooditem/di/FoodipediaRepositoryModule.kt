package com.example.lifesumtestapp.fooditem.di

import com.example.lifesumtestapp.fooditem.data.service.FoodipediaService
import dagger.Module
import retrofit2.Retrofit

@Module
object FoodipediaRepositoryModule {

    fun provideService(retrofit: Retrofit): FoodipediaService {
        return retrofit.create(FoodipediaService::class.java)
    }
}