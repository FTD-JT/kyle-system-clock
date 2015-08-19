package com.kyletung.kylesystemclock.stopwatch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyletung.kylesystemclock.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <br>Created on 15-8-19.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.4
 */
public class StopRecordAdapter extends RecyclerView.Adapter<StopViewHolder> {

    private List<StopRecordTime> list;
    private Context context;

    public StopRecordAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public StopViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.stop_recycler_item, null);
        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StopViewHolder stopViewHolder, int i) {
        stopViewHolder.stopListHour.setText(list.get(i).getHour() + "");
        stopViewHolder.stopListMinute.setText(list.get(i).getMinute() + "");
        stopViewHolder.stopListSecond.setText(list.get(i).getSecond() + "");
        stopViewHolder.stopListMilles.setText(list.get(i).getMilles() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(StopRecordTime stopRecordTime) {
        list.add(list.size(), stopRecordTime);
        notifyItemInserted(list.size());
    }

    public void clear() {
        for (int i = list.size() - 1; i >= 0; i--) {
            list.remove(i);
            notifyItemRemoved(i);
        }
    }
}

class StopViewHolder extends RecyclerView.ViewHolder {

    TextView stopListHour;
    TextView stopListMinute;
    TextView stopListSecond;
    TextView stopListMilles;

    public StopViewHolder(View itemView) {
        super(itemView);
        stopListHour = (TextView) itemView.findViewById(R.id.stop_list_hour);
        stopListMinute = (TextView) itemView.findViewById(R.id.stop_list_minute);
        stopListSecond = (TextView) itemView.findViewById(R.id.stop_list_second);
        stopListMilles = (TextView) itemView.findViewById(R.id.stop_list_milles);
    }
}