package com.kyletung.kylesystemclock.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
 * @version 0.1
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    Fragment fragment;
    AlarmFragment alarmFragment = new AlarmFragment();
    TimerFragment timerFragment = new TimerFragment();
    StopwatchFragment stopwatchFragment = new StopwatchFragment();

    String[] titles = {"Alarm", "Timer", "StopWatch"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
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
        return titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
