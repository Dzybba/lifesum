package com.example.lifesumtestapp.fooditem.presentation.shake

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

private const val CRITICAL_ACCELERATION_VALUE = 12

class ShakeEventListener(
    private val onShakeEventAction: () -> Unit
) : SensorEventListener {

    private var acceleration = 10f
    private var currentAcceleration = SensorManager.GRAVITY_EARTH
    private var lastAcceleration = SensorManager.GRAVITY_EARTH

    override fun onSensorChanged(event: SensorEvent) {

        // Fetching x,y,z values
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        lastAcceleration = currentAcceleration

        // Getting current accelerations
        // with the help of fetched x,y,z values
        currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val delta: Float = currentAcceleration - lastAcceleration
        acceleration = acceleration * 0.9f + delta

        if (acceleration > CRITICAL_ACCELERATION_VALUE) {
            onShakeEventAction()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}
