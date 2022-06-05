package com.example.lifesumtestapp.core

import android.content.Context

interface CoreProvider {

    @ApplicationContext
    fun applicationContext(): Context
}