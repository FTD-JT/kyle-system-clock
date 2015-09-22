package com.kyletung.kylesystemclock.stopwatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class StopRecordAdapter extends BaseAdapter {

    private List<StopRecordTime> list;
    private LayoutInflater inflater;

    public StopRecordAdapter(Context context) {
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void add(StopRecordTime stopRecordTime) {
        list.add(stopRecordTime);
        notifyDataSetChanged();
    }

    public void clear() {
        for (int i = list.size() - 1; i >= 0; i--) {
            list.remove(i);
            notifyDataSetChanged();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StopViewHolder stopViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.stop_recycler_item, parent, false);
            stopViewHolder = new StopViewHolder();
            stopViewHolder.stopListHour = (TextView) convertView.findViewById(R.id.stop_list_hour);
            stopViewHolder.stopListMinute = (TextView) convertView.findViewById(R.id.stop_list_minute);
            stopViewHolder.stopListSecond = (TextView) convertView.findViewById(R.id.stop_list_second);
            stopViewHolder.stopListMilles = (TextView) convertView.findViewById(R.id.stop_list_milles);
            convertView.setTag(stopViewHolder);
        } else {
            stopViewHolder = (StopViewHolder) convertView.getTag();
        }
        stopViewHolder.stopListHour.setText(list.get(position).getHour() + "");
        stopViewHolder.stopListMinute.setText(list.get(position).getMinute() + "");
        stopViewHolder.stopListSecond.setText(list.get(position).getSecond() + "");
        stopViewHolder.stopListMilles.setText(list.get(position).getMilles() + "");
        return convertView;
    }

    class StopViewHolder {

        TextView stopListHour;
        TextView stopListMinute;
        TextView stopListSecond;
        TextView stopListMilles;

    }
}