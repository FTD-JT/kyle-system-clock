package com.kyletung.kylesystemclock.stopwatch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
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
public class StopwatchFragment extends Fragment {

    private static int STOP_STATE = 1;

//    private RecyclerView recyclerView;
    private ListView listview;

    private Button stopStart;
    private Button stopRecord;
    private Button stopClear;

    private TextView stopHour;
    private TextView stopMinute;
    private TextView stopSecond;
    private TextView stopMilles;

    private StopTimerTask stopTimerTask;
    private StopRecordAdapter stopRecordAdapter;

    public StopwatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stopwatch_fragment, container, false);
        stopStart = (Button) view.findViewById(R.id.stop_start);
        stopRecord = (Button) view.findViewById(R.id.stop_record);
        stopClear = (Button) view.findViewById(R.id.stop_clear);
        stopHour = (TextView) view.findViewById(R.id.stop_hour);
        stopMinute = (TextView) view.findViewById(R.id.stop_minute);
        stopSecond = (TextView) view.findViewById(R.id.stop_second);
        stopMilles = (TextView) view.findViewById(R.id.stop_milles);
        stopTimerTask = new StopTimerTask(stopHour, stopMinute, stopSecond, stopMilles);
        //
        stopRecordAdapter = new StopRecordAdapter(getActivity());
        listview = (ListView) view.findViewById(R.id.stop_recycler_view);
        listview.setDividerHeight(0);
        listview.setAdapter(stopRecordAdapter);

        stopStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (STOP_STATE == 1) {
                    stopTimerTask.start();
                    stopStart.setText("暂停");
                    STOP_STATE = 0;
                } else if (STOP_STATE == 0) {
                    stopTimerTask.stop();
                    stopStart.setText("继续");
                    STOP_STATE = 1;
                }
            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecordAdapter.add(stopTimerTask.getTime());
                listview.setSelection(stopRecordAdapter.getCount() - 1);
            }
        });
        stopClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimerTask.clear();
                stopRecordAdapter.clear();
                stopStart.setText("开始");
                STOP_STATE = 1;
            }
        });

        return view;
    }


}
