package com.kyletung.kylesystemclock.alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.kyletung.kylesystemclock.R;

import java.util.Calendar;
import java.util.List;

/**
 * Description:
 * <br>Created on 15-8-14.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.2
 */
public class AlarmAdapter extends BaseAdapter {

    private List<AlarmData> list;
    private AlarmMyManager alarmMyManager;
    private Context context;
    private AlarmSQLiteSave alarmSQLiteSave;

    public AlarmAdapter(Context context) {
        this.context = context;
        alarmSQLiteSave = new AlarmSQLiteSave(context);
        list = alarmSQLiteSave.initAlarm();
        alarmMyManager = new AlarmMyManager(context, list);
    }

    public void addAlarm(AlarmData alarmData) {
        Calendar calendarGet = Calendar.getInstance();
        Calendar calendarSet = Calendar.getInstance();
        calendarSet.set(alarmData.getYear(), alarmData.getMonth(), alarmData.getDay(), alarmData.getHour(), alarmData.getMinute(), 0);
        if (calendarGet.getTimeInMillis() < calendarSet.getTimeInMillis() & alarmData.getYear() < 2100) {
            alarmSQLiteSave.saveAlarm(alarmData);
            alarmMyManager.addAlarmManager(alarmData);
            list.add(alarmData);
            notifyDataSetChanged();
        } else {
            Toast.makeText(context, "少年，无法时空穿越", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeAlarm(int position) {
        alarmSQLiteSave.deleteAlarm(list.get(position));
        alarmMyManager.cancelAlarmManager(list.get(position));
        list.remove(position);
        notifyDataSetChanged();
    }

    public void changeAlarm(int position, int alarmSwitch) {
        if (alarmSwitch == 1) {
            Calendar calendarGet = Calendar.getInstance();
            Calendar calendarSet = Calendar.getInstance();
            calendarSet.set(list.get(position).getYear(), list.get(position).getMonth(), list.get(position).getDay(), list.get(position).getHour(), list.get(position).getMinute(), 0);
            if (calendarGet.getTimeInMillis() < calendarSet.getTimeInMillis()) {
                alarmSQLiteSave.updateAlarm(list.get(position), alarmSwitch);
                alarmMyManager.addAlarmManager(list.get(position));
                list.get(position).setAlarmSwitch(alarmSwitch);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "少年，这是过去的时间", Toast.LENGTH_SHORT).show();
                list.get(position).setAlarmSwitch(0);
                notifyDataSetChanged();
            }
        } else if (alarmSwitch == 0) {
            alarmSQLiteSave.updateAlarm(list.get(position), alarmSwitch);
            alarmMyManager.cancelAlarmManager(list.get(position));
            list.get(position).setAlarmSwitch(alarmSwitch);
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.alarm_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.time.setText(list.get(position).getHour() + " 时 / " + list.get(position).getMinute() + " 分");
        holder.date.setText(list.get(position).getYear() + " 年 / " + (list.get(position).getMonth() + 1) + " 月 / " + list.get(position).getDay() + " 日");
        if (list.get(position).getAlarmSwitch() == 1) {
            holder.alarmSwitch.setChecked(true);
            holder.image.setImageResource(R.drawable.ic_alarm_on_white);
        } else if (list.get(position).getAlarmSwitch() == 0) {
            holder.alarmSwitch.setChecked(false);
            holder.image.setImageResource(R.drawable.ic_alarm_off_white);
        }
        holder.alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    changeAlarm(position, 1);
                } else {
                    changeAlarm(position, 0);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder {

        TextView time;
        TextView date;
        ImageView image;
        Switch alarmSwitch;

        public ViewHolder(View view) {
            time = (TextView) view.findViewById(R.id.alarm_time);
            date = (TextView) view.findViewById(R.id.alarm_date);
            image = (ImageView) view.findViewById(R.id.alarm_image);
            alarmSwitch = (Switch) view.findViewById(R.id.alarm_switch);
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
