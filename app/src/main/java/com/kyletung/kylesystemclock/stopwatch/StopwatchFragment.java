package com.kyletung.kylesystemclock.stopwatch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class StopwatchFragment extends Fragment {

    private static int STOP_STATE = 1;

    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.stop_recycler_view);
        stopRecordAdapter = new StopRecordAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(stopRecordAdapter);
        //
        stopStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (STOP_STATE == 1) {
                    stopTimerTask.start();
                    stopStart.setText("Pause");
                    STOP_STATE = 0;
                } else if (STOP_STATE == 0) {
                    stopTimerTask.stop();
                    stopStart.setText("Resume");
                    STOP_STATE = 1;
                }
            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecordAdapter.add(stopTimerTask.getTime());
            }
        });
        stopClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimerTask.clear();
                stopRecordAdapter.clear();
                stopStart.setText("Start");
                STOP_STATE = 1;
            }
        });

        return view;
    }


}
