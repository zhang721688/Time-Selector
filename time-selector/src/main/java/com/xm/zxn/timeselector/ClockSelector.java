package com.xm.zxn.timeselector;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zxn on 2020/5/1.
 */
public class ClockSelector {
    private Context mContext;
    private ResultHandler mResultHandler;

    public ClockSelector(Context context) {
        this.mContext = context;
    }

    public ClockSelector(Context context,ResultHandler resultHandler) {
        this.mContext = context;
        this.mResultHandler = resultHandler;
    }


    public void show() {
        /*if (startCalendar.getTime().getTime() >= endCalendar.getTime().getTime()) {
            Toast.makeText(context, "start>end", Toast.LENGTH_LONG).show();
            return;
        }

        if (!excuteWorkTime()) return;
        initParameter();
        initTimer();
        addListener();
        seletorDialog.show();*/
    }
}
