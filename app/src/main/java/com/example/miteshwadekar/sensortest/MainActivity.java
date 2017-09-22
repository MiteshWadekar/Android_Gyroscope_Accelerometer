package com.example.miteshwadekar.sensortest;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    SensorManager sm ;
    Sensor accmeter , gyroscope;
    TextView tv1 ,tv2, tv3, tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accmeter = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accmeter,SensorManager.SENSOR_DELAY_NORMAL);
        gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sm.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        tv1 = (TextView) findViewById(R.id.textviewone);
        tv2 = (TextView) findViewById(R.id.textviewtwo);
        tv3 = (TextView) findViewById(R.id.textviewthree);
        tv4 = (TextView) findViewById(R.id.textviewfour);
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
    public void onSensorChanged(SensorEvent event)
    {
        Sensor sensor;
        sensor = event.sensor;
       if(sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
           // Toast toast = Toast.makeText(getApplicationContext(),"ITS WORKING",Toast.LENGTH_SHORT);
         //   toast.show();
            float data[] = event.values;
            tv1.setText("X:" +data[0]+ "\nY:" +data[1]+ "\nZ:"+data[2]);
            tv3.setText("X:" +(int)data[0]+ "\nY:" +(int)data[1]+ "\nZ:" +(int)data[2]);
        }
        if(sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
           // Toast toast = Toast.makeText(getApplicationContext(),"ITS NOT WORKING",Toast.LENGTH_SHORT);
            //toast.show();
            float data[] = event.values;
            tv2.setText("X:" +data[0]+ "\nY:" +data[1]+ "\nZ:" +data[2]);
            tv4.setText("X:" +(int)data[0]+ "\nY:"+(int)data[1]+"\nZ.:"+(int)data[2]);
        }
    }
    protected void onStop()
    {
        sm.unregisterListener(this);
        gyroscope = null;
        sm = null;
        accmeter = null;
        Toast toast = Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_SHORT);
        toast.show();
        super.onStop();

    }
}


