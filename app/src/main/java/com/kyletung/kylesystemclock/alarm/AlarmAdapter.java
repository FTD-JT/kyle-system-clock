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


import com.kyletung.kylesystemclock.R;

import java.util.List;

/**
 * Description:
 * <br>Created on 15-8-14.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1
 */
public class AlarmAdapter extends BaseAdapter {

    private List<AlarmData> list;

    private Context context;
    private AlarmSQLiteSave alarmSQLiteSave;

    public AlarmAdapter(Context context) {
        this.context = context;
        alarmSQLiteSave = new AlarmSQLiteSave(context);
        list = alarmSQLiteSave.initAlarm();
    }

    public void addAlarm(AlarmData alarmData) {
        alarmSQLiteSave.saveAlarm(alarmData);
        list.add(alarmData);
        notifyDataSetChanged();
    }

    public void removeAlarm(int position) {
        alarmSQLiteSave.deleteAlarm(list.get(position));
        list.remove(position);
        notifyDataSetChanged();
    }

    public void changeAlarm(int position, int alarmSwitch) {
        alarmSQLiteSave.updateAlarm(list.get(position), alarmSwitch);
        list.get(position).setAlarmSwitch(alarmSwitch);
        notifyDataSetChanged();
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
        holder.time.setText(list.get(position).getHour() + " H / " + list.get(position).getMinute() + " M");
        holder.date.setText(list.get(position).getYear() + " Y / " + (list.get(position).getMonth() + 1) + " M / " + list.get(position).getDay() + " D");
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
