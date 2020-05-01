package com.xm.zxn.timeselectordemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xm.zxn.timeselector.ClockSelector;
import com.xm.zxn.timeselector.ResultHandler;
import com.xm.zxn.timeselector.TimeSelector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TimeSelector timeSelector;
    private TimeSelector ymdTimeSelector;
    private TimeSelector hmTimeSelector;
    private ClockSelector mClockSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_ymd).setOnClickListener(this);
        findViewById(R.id.tv_hm).setOnClickListener(this);

        onInitSelector();
    }

    private void onInitSelector() {
        timeSelector = new TimeSelector(this, new ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        }, "1989-01-30 00:00", "2018-12-31 00:00");
//        timeSelector.setIsLoop(false);

        ymdTimeSelector = new TimeSelector(this, new ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        }, "1989-01-30 00:00", "2018-12-31 00:00");
        ymdTimeSelector.setMode(TimeSelector.YMD);//只显示 年月日
        ymdTimeSelector.setIsLoop(false);

        mClockSelector = new ClockSelector(this, new ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        });
        //hmTimeSelector.setMode(TimeSelector.HM);//只显示 时分
        hmTimeSelector.setIsLoop(false);
    }

    public void show(View v) {
        timeSelector.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ymd) {
            ymdTimeSelector.show();
        } else if (v.getId() == R.id.tv_hm) {
            mClockSelector.show();
        }
    }
}
