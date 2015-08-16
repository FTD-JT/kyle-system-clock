package com.kyletung.kylesystemclock.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.List;

/**
 * Description:
 * <br>Created on 15-8-16.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.2
 */
public class AlarmMyManager {

    private List<AlarmData> list;
    private Context context;
    private Intent intent;
    private AlarmManager alarmManager;

    public AlarmMyManager(Context context, List<AlarmData> list) {
        this.context = context;
        intent = new Intent(context, MyAlarmService.class);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.list = list;
        initAlarmMyManager();
    }

    public void initAlarmMyManager() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAlarmSwitch() == 1) {
                addAlarmManager(list.get(i));
                System.out.println(">>> init alarm success <<<");
            }
        }
    }

    public void addAlarmManager(AlarmData alarmData) {
        int requestCode = getRequestCode(alarmData);
        Calendar calendar = Calendar.getInstance();
        calendar.set(alarmData.getYear(), alarmData.getMonth(), alarmData.getDay(), alarmData.getHour(), alarmData.getMinute(), 0);
        PendingIntent pendingIntent = PendingIntent.getService(context, requestCode, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        System.out.println(">>> add alarm success <<<");
    }

    public void cancelAlarmManager(AlarmData alarmData) {
        int requestCode = getRequestCode(alarmData);
        PendingIntent pendingIntent = PendingIntent.getService(context, requestCode, intent, 0);
        alarmManager.cancel(pendingIntent);
        System.out.println(">>> cancel alarm success <<<");
    }

    public int getRequestCode(AlarmData alarmData) {
        return Integer.parseInt((alarmData.getYear() - 2000) + "" + alarmData.getMonth() + "" + alarmData.getDay() + "" + alarmData.getHour() + "" + alarmData.getMinute());
    }

}
