package com.example.lifesumtestapp.fooditem.domain

import javax.inject.Inject

class GetRandomNumberUseCase
@Inject constructor(){

    fun getRandomNumber(): Int {
        return (1..200).random()
    }
}