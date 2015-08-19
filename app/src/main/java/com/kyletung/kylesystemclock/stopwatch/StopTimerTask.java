package com.kyletung.kylesystemclock.stopwatch;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:
 * <br>Created on 15-8-19.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.4
 */
public class StopTimerTask {

    private long time;

    private MyStopTimerTask myStopTimerTask;
    private Timer timer;

    private TextView stopHour;
    private TextView stopMinute;
    private TextView stopSecond;
    private TextView stopMilles;


    public StopTimerTask(TextView stopHour, TextView stopMinute, TextView stopSecond, TextView stopMilles) {
        this.stopHour = stopHour;
        this.stopMinute = stopMinute;
        this.stopSecond = stopSecond;
        this.stopMilles = stopMilles;
        time = 0;
        timer = new Timer();
        myStopTimerTask = new MyStopTimerTask();
    }

    public void start() {
        timer.schedule(myStopTimerTask, 100, 100);
    }

    public StopRecordTime getTime() {
        String hour = getHour(time) + "";
        String minute = getHour(time) + "";
        String second = getSecond(time) + "";
        String milles = getMilles(time) + "";
        return new StopRecordTime(hour, minute, second, milles);
    }

    public void clear() {
        myStopTimerTask.cancel();
        time = 0;
        stopHour.setText(getHour(time) + "");
        stopMinute.setText(getMinute(time) + "");
        stopSecond.setText(getSecond(time) + "");
        stopMilles.setText(getMilles(time) + "");
        myStopTimerTask = new MyStopTimerTask();
    }

    public void stop() {
        myStopTimerTask.cancel();
        myStopTimerTask = new MyStopTimerTask();
    }

    public class MyStopTimerTask extends TimerTask {

        @Override
        public void run() {
            Message message = new Message();
            message.what = 13;
            handler.sendMessage(message);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 13:
                    time = time + 1;
                    stopHour.setText(getHour(time) + "");
                    stopMinute.setText(getMinute(time) + "");
                    stopSecond.setText(getSecond(time) + "");
                    stopMilles.setText(getMilles(time) + "");
                    break;
                default:
                    break;
            }
        }
    };

    public int getHour(long time) {
        return (int) (time / 10 / 60 / 60);
    }

    public int getMinute(long time) {
        return (int) ((time - getHour(time) * 60 * 60 * 10) / 10 / 60);
    }

    public int getSecond(long time) {
        return (int) ((time - getHour(time) * 60 * 60 * 10 - getMinute(time) * 60 * 10) / 10);
    }

    public int getMilles(long time) {
        return (int) (time - getHour(time) * 60 * 60 * 10 - getMinute(time) * 60 * 10 - getSecond(time) * 10);
    }

}
