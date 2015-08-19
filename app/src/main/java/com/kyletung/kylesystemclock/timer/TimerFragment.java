package com.kyletung.kylesystemclock.timer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kyletung.kylesystemclock.R;


/**
 * Description:
 * <br>Created on 15-8-14.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.4
 */
public class TimerFragment extends Fragment {

    private Button timerSet;
    private Button timerReset;
    private Button timerStart;

    private TextView timerHour;
    private TextView timerMinute;
    private TextView timerSecond;

    private int hour;
    private int minute;
    private int second;

    private static int START_STATE = 1;

    private TimerRun timerRun;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.timer_fragment, container, false);
        timerReset = (Button) view.findViewById(R.id.timer_reset);
        timerSet = (Button) view.findViewById(R.id.timer_set);
        timerStart = (Button) view.findViewById(R.id.timer_start);
        timerHour = (TextView) view.findViewById(R.id.timer_hour);
        timerMinute = (TextView) view.findViewById(R.id.timer_minute);
        timerSecond = (TextView) view.findViewById(R.id.timer_second);

        //
        timerStart.setEnabled(false);
        timerReset.setEnabled(false);

        //set timer
        timerSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TimerSetActivity.class);
                startActivityForResult(intent, 1);

                timerStart.setText("Start");
                START_STATE = 1;
            }
        });
        timerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (START_STATE == 1) {
                    timerRun.resume();
                    timerStart.setText("Stop");
                    START_STATE = 0;
                } else if (START_STATE == 0) {
                    timerRun.stop();
                    timerStart.setText("Resume");
                    START_STATE = 1;
                }
            }
        });
        timerReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerRun.clear();
                timerHour.setText(hour + "");
                timerMinute.setText(minute + "");
                timerSecond.setText(second + "");

                timerStart.setText("Start");
                START_STATE = 1;
            }
        });

        return view;
    }

    public long getAllTime(int hour, int minute, int second) {
        System.out.println("set time is " + hour + "-" + minute + "-" + second);
        return (long) (hour * 60 * 60 + minute * 60 + second);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 2015) {
                hour = data.getIntExtra("time_hour", 0);
                minute = data.getIntExtra("time_minute", 0);
                second = data.getIntExtra("time_second", 0);
                if (hour == 0) {
                    timerHour.setText("00");
                } else {
                    timerHour.setText(hour + "");
                }
                if (minute == 0) {
                    timerMinute.setText("00");
                } else {
                    timerMinute.setText(minute + "");
                }
                if (second == 0) {
                    timerSecond.setText("00");
                } else {
                    timerSecond.setText(second + "");
                }
                timerRun = new TimerRun(getActivity(), getAllTime(hour, minute, second), timerHour, timerMinute, timerSecond);
                timerReset.setEnabled(true);
                timerStart.setEnabled(true);
            }
        }
    }
}
