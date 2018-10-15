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
    TextView textView, textView1;
    private ImageView image;
    private  float rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        image = (ImageView) findViewById(R.id.imageView);
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
        textView1.setText("Сторона: C");

        textView.setText("Отклонение от севера: " + Float.toString(degree) + " градусов");
        if (degree == 0) textView1.setText("Сторона: C");
        if (degree> 0 & degree < 90) textView1.setText("Сторона: CВ");
        if (degree == 90) textView1.setText("Сторона: В");
        if (degree> 90 & degree < 180) textView1.setText("Сторона: ЮВ");
        if (degree == 180) textView1.setText("Сторона: Ю");
        if (degree> 180 & degree < 270) textView1.setText("Сторона: ЮЗ");
        if (degree == 270) textView1.setText("Сторона: З");
        if (degree> 270) textView1.setText("Сторона: CЗ");
        //Создаем анимацию вращения:
        RotateAnimation rotateAnimation = new RotateAnimation(
                rotate,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        //Продолжительность анимации в миллисекундах:
        rotateAnimation.setDuration(200);

        //Настраиваем анимацию после завершения подсчетных действий датчика:
        rotateAnimation.setFillAfter(true);

        //Запускаем анимацию:
        image.startAnimation(rotateAnimation);
        rotate = -degree;


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
