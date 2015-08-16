package com.kyletung.kylesystemclock.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.kyletung.kylesystemclock.R;
import com.kyletung.kylesystemclock.alarm.AlarmFragment;
import com.kyletung.kylesystemclock.stopwatch.StopwatchFragment;
import com.kyletung.kylesystemclock.timer.TimerFragment;

/**
 * Description:
 * <br>Created on 15-8-14.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.1
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    Context context;
    Fragment fragment;
    AlarmFragment alarmFragment = new AlarmFragment();
    TimerFragment timerFragment = new TimerFragment();
    StopwatchFragment stopwatchFragment = new StopwatchFragment();

    String[] titles = {"Alarm", "Timer", "StopWatch"};
    int[] imageTitles = {R.drawable.ic_access_alarm_white, R.drawable.ic_history_white, R.drawable.ic_timer_white};

    public MyPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                fragment = alarmFragment;
                break;
            case 1:
                fragment = timerFragment;
                break;
            case 2:
                fragment = stopwatchFragment;
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return titles[position];
        Drawable image = context.getResources().getDrawable(imageTitles[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString spannableString = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
