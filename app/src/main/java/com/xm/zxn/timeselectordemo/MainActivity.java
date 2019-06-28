package com.xm.zxn.timeselectordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.xm.zxn.timeselector.R;
import com.xm.zxn.timeselector.TimeSelector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TimeSelector timeSelector;
    private TimeSelector ymdTimeSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.tv_ymd).setOnClickListener(this);


        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        }, "1989-01-30 00:00", "2018-12-31 00:00");
//        timeSelector.setIsLoop(false);

        ymdTimeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        }, "1989-01-30 00:00", "2018-12-31 00:00");
        ymdTimeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
        ymdTimeSelector.setIsLoop(false);
    }

    public void show(View v) {
        timeSelector.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ymd) {
            ymdTimeSelector.show();
        }
    }
}
