package com.kyletung.kylesystemclock.alarm;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import com.kyletung.kylesystemclock.R;

import java.util.Calendar;

/**
 * Description:
 * <br>Created on 15-8-14.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.2
 */
public class AlarmFragment extends Fragment {

    private AlarmAdapter alarmAdapter;
    private AlarmData alarmData;
    private Calendar calendar;

    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.alarm_fragment, container, false);
        ListView listView = (ListView) view.findViewById(R.id.alarm_list);
        Button button = (Button) view.findViewById(R.id.add_alarm);
        alarmAdapter = new AlarmAdapter(getActivity());
        listView.setDividerHeight(0);
        listView.setAdapter(alarmAdapter);
        //set listener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("确认删除");
                dialog.setMessage("是否要删除此项闹钟？");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alarmAdapter.removeAlarm(position);
                    }
                });
                dialog.show();
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {

            TimePickerDialog timePickerDialog;
            DatePickerDialog datePickerDialog;

            @Override
            public void onClick(View v) {
                alarmData = new AlarmData();
                calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        alarmData.setYear(year);
                        alarmData.setMonth(monthOfYear);
                        alarmData.setDay(dayOfMonth);
                        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                alarmData.setHour(hourOfDay);
                                alarmData.setMinute(minute);
                                alarmAdapter.addAlarm(alarmData);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
                        timePickerDialog.show();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });

        return view;
    }


}
