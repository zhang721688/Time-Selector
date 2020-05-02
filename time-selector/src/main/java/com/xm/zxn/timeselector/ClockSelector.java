package com.xm.zxn.timeselector;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xm.zxn.timeselector.utils.DateUtil;
import com.xm.zxn.timeselector.utils.ScreenUtil;
import com.xm.zxn.timeselector.view.PickerView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by zxn on 2020/5/1.
 */
public class ClockSelector {

    private final int MAXMINUTE = 59;
    private final String FORMAT_STR = "HH:mm";

    private Context mContext;
    private ResultHandler mResultHandler;
    private int MAXHOUR = 23;
    private Dialog mClockDialog;
    private ArrayList<String> hourList;
    private ArrayList<String> minuteList;
    private PickerView hour_pv;
    private PickerView minute_pv;
    private int startHour, startMininute;
    private Calendar selectedCalender = Calendar.getInstance();

    public ClockSelector(Context context) {
        this(context, null);
    }

    public ClockSelector(Context context, ResultHandler resultHandler) {
        this.mContext = context;
        this.mResultHandler = resultHandler;
        initDialog();
        initView();
    }

    private void initView() {
        hour_pv = (PickerView) mClockDialog.findViewById(R.id.hour_pv);
        minute_pv = (PickerView) mClockDialog.findViewById(R.id.minute_pv);
        TextView tv_select = (TextView) mClockDialog.findViewById(R.id.tv_select);
        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mResultHandler.handle(DateUtil.format(selectedCalender.getTime(), FORMAT_STR));
                mClockDialog.dismiss();
            }
        });
    }

    private void initDialog() {
        if (mClockDialog == null) {
            mClockDialog = new Dialog(mContext, R.style.time_dialog);
            mClockDialog.setCancelable(true);
            mClockDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mClockDialog.setContentView(R.layout.dialog_clock_selector);
            Window window = mClockDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            int width = ScreenUtil.getInstance(mContext).getScreenWidth();
            lp.width = width;
            window.setAttributes(lp);
        }
    }

    public void show() {
        initTimer();
        addListener();
        mClockDialog.show();
    }


    private void initTimer() {
        initArrayList();

        for (int i = startHour; i <= MAXHOUR; i++) {
            hourList.add(fomatTimeUnit(i));
        }

        for (int i = startMininute; i <= MAXMINUTE; i++) {
            minuteList.add(fomatTimeUnit(i));
        }
        loadComponent();
    }

    private void addListener() {
        hour_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.HOUR, Integer.parseInt(text));
            }
        });
        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.MINUTE, Integer.parseInt(text));
            }
        });

    }

    private void initArrayList() {
        if (hourList == null) hourList = new ArrayList<>();
        if (minuteList == null) minuteList = new ArrayList<>();
        hourList.clear();
        minuteList.clear();
    }

    private String fomatTimeUnit(int unit) {
        return unit < 10 ? "0" + String.valueOf(unit) : String.valueOf(unit);
    }

    private void loadComponent() {
        hour_pv.setData(hourList);
        minute_pv.setData(minuteList);
        hour_pv.setSelected(0);
        minute_pv.setSelected(0);
        excuteScroll();
    }

    private void excuteScroll() {
        hour_pv.setCanScroll(true);
        minute_pv.setCanScroll(true);
    }
}
