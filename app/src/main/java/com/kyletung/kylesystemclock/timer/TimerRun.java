package com.kyletung.kylesystemclock.timer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:
 * <br>Created on 15-8-18.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.3
 */
public class TimerRun {

    private TextView textHour;
    private TextView textMinute;
    private TextView textSecond;

    private long realTime;
    private long time;
    private Timer timer;

    private Context context;
    private MyTimerTask myTimerTask;

    public TimerRun(Context context, long time, TextView textHour, TextView textMinute, TextView textSecond) {
        this.context = context;
        this.realTime = time;
        this.time = time;
        this.textHour = textHour;
        this.textMinute = textMinute;
        this.textSecond = textSecond;
        timer = new Timer();
        myTimerTask = new MyTimerTask();
    }

    public void resume() {
        timer.schedule(myTimerTask, 1000, 1000);
    }

    public void stop() {
        myTimerTask.cancel();
        myTimerTask = new MyTimerTask();
    }

    public void clear() {
        myTimerTask.cancel();
        time = realTime;
        myTimerTask = new MyTimerTask();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (time > 0) {
                        time = time - 1;
                        System.out.println("time in loop is " + time);
                        textHour.setText(getHour(time) + "");
                        textMinute.setText(getMinute(time) + "");
                        textSecond.setText(getSecond(time) + "");
                    } else {
                        myTimerTask.cancel();
                        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                        final MediaPlayer mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(context, alarmUri);
                            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                            if (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) != 0) {
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                                mediaPlayer.setLooping(true);
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            }
                            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                            dialog.setTitle("计时");
                            dialog.setMessage("您所设置的计时器已到时");
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mediaPlayer.stop();
                                    System.out.println(">>> alert dialog show <<<");
                                }
                            });
                            AlertDialog alertDialog = dialog.create();
                            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            alertDialog.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    //set function when time is running up
                    break;
                default:
                    break;
            }
        }
    };

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }

    public int getHour(long allTime) {
        int hour = (int) (allTime / 60 / 60);
        return hour;
    }

    public int getMinute(long allTime) {
        long cutHour = allTime - getHour(allTime) * 60 * 60;
        return (int) (cutHour / 60);
    }

    public int getSecond(long allTime) {
        long cutMinute = allTime - getHour(allTime) * 60 * 60 - getMinute(allTime) * 60;
        return (int) cutMinute;
    }
}