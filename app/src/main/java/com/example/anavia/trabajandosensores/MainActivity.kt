package com.example.anavia.trabajandosensores

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout
import java.nio.file.Files.find


class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensorManager: SensorManager?=null
    var sensorAcelerometer:Sensor?=null

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
       }

    override fun onSensorChanged(event: SensorEvent?) {

        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            lblPosition.text = "${event.values[0].toString()} - ${event.values[1].toString()} - ${event.values[1].toString()}"
        }
     }


    override fun onResume() {

        sensorManager?.registerListener(this, sensorAcelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensorManager?.unregisterListener(this)
        super.onPause()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        /* val sensores = sensorManager?.getSensorList(Sensor.TYPE_ALL)
         for (i in 0 ..sensores!!.size-1){
             lblPosition.append(sensores!!.get(i).name + "\n")
         }*/

        //verificamos si existe el sensor en el dispositivo
        if(sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!= null)
        {
            sensorAcelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        }else{
            Toast.makeText(this,"Sensor acelerometro no disponible", Toast.LENGTH_SHORT).show()
        }

        btnBegin.setOnClickListener {
            val androidColors = R.array.androidcolors
            println( "androidColors $androidColors" )
           /* int[] androidColors = getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            view.setBackgroundColor(randomAndroidColor);*/

            val linearlayout = findViewById(R.id.lyPosition) as LinearLayout
            linearlayout.setBackgroundColor(Color.RED);

        }

        btnStop.setOnClickListener {
            onPause()
        }
    }
}
