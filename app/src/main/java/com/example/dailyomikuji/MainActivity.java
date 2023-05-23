package com.example.dailyomikuji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgOmikuji;
    TextView txtMain;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgOmikuji = findViewById(R.id.imgOmokuji);
        txtMain = findViewById(R.id.txtMain);
        Button btnHiku = findViewById(R.id.btnHiku);
        Button btnClear = findViewById(R.id.btnClear);

        btnHiku.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnHiku){
            if (isExecToday()){
                long time = preferences.getLong("time",0);
                int omikuji = preferences.getInt("omikuji",-1);
                Calendar beforeCl = Calendar.getInstance();
                beforeCl.setTimeInMillis(time);

                SimpleDateFormat sdf = new SimpleDateFormat("今日はすでに引いてます\n" + "前回の結果は" + Omikuji.getText(omikuji) + "\n前回引いた時刻はHH時mm分ss秒");
                String text = sdf.format(beforeCl.getTime());

                txtMain.setText(text);
                return;
            }


            int random = Omikuji.omikuji();
            Omikuji.setImage(imgOmikuji,random);

            editor = preferences.edit();

            editor.putInt("omikuji",random);

            Calendar cl = Calendar.getInstance();
            editor.putLong("time",cl.getTimeInMillis());
            editor.apply();


        }else if(view.getId() == R.id.btnClear){
            imgOmikuji.setImageResource(R.drawable.omikuji);

            editor = preferences.edit();

            editor.clear();
            editor.apply();
            txtMain.setText("");

        }





    }
    private boolean isExecToday(){
        long time = preferences.getLong("time",0);
        if(time == 0) return false;

        Calendar before = Calendar.getInstance();
        before.setTimeInMillis(time);
        // 現在の時刻を取得
        Calendar now = Calendar.getInstance();

        int yearDifference = before.get(Calendar.YEAR) - now.get(Calendar.YEAR);
        int monthDifference = before.get(Calendar.MONTH) - now.get(Calendar.MONTH);
        int dayDifference = before.get(Calendar.DATE) - now.get(Calendar.DATE);

        return yearDifference == 0 && monthDifference == 0 && dayDifference == 0;
    }


    }
