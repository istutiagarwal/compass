package com.example.assingmenttraining

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        sensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ROTATION_VECTOR) {
            val rotationMatrix = FloatArray(9)
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            val orientationValues = FloatArray(3)
            SensorManager.getOrientation(rotationMatrix, orientationValues)
            val azimuth = Math.toDegrees(orientationValues[0].toDouble())
            Log.d("AH-LOG", "$azimuth")
            findViewById<CompassView>(R.id.compass)?.updatePosition((-azimuth).toFloat() )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Do nothing
    }
}





