package com.example.lifesumtestapp.core

import retrofit2.Retrofit

interface NetworkProvider {

    fun provideRetrofit(): Retrofit
}