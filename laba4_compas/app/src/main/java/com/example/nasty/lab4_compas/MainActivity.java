package com.example.nasty.lab4_compas;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


   // private  float rotate;
    private SensorManager mSensor;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Устанавливаем слушателя ориентации сенсора
        mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Останавливаем при надобности слушателя ориентации
        //сенсора с целью сбережения заряда батареи:
        mSensor.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //Получаем градус поворота от оси, которая направлена на север, север = 0 градусов:
        float degree = Math.round(event.values[0]);
        textView.setText("Отклонение от севера: " + Float.toString(degree) + " градусов");


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
