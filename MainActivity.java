package com.example.android_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private SensorManager sensorManager;
   private Sensor gyroscopeSensor;
   private SensorEventListener gyroscopeEventListener;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
      // R.id.hello;
       sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
       gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

       if(gyroscopeSensor==null){
           Toast.makeText(this,"The device has no Gyroscope !",Toast.LENGTH_SHORT).show();
           finish();
       }
       gyroscopeEventListener = new SensorEventListener() {
           @Override
           public void onSensorChanged(SensorEvent sensorEvent) {
               if(sensorEvent.values[2]>0.5f){
                   getWindow().getDecorView().setBackgroundColor(Color.BLUE);

               }else if(sensorEvent.values[2]< 0.5f){
                   getWindow().getDecorView().setBackgroundColor(Color.GREEN);
               }
           }

           @Override
           public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

           }
       };
   }
   @Override
   protected void onResume(){
       super.onResume();
       sensorManager.registerListener(gyroscopeEventListener,gyroscopeSensor,SensorManager.SENSOR_DELAY_FASTEST);
   }
   @Override
   protected  void  onPause() {
       super.onPause();

       sensorManager.unregisterListener(gyroscopeEventListener);
   }
}

