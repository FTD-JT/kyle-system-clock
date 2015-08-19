package com.kyletung.kylesystemclock.stopwatch;

/**
 * Description:
 * <br>Created on 15-8-19.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.4
 */
public class StopRecordTime {

    private int hour;
    private int minute;
    private int second;
    private int milles;

    public StopRecordTime(String hour, String minute, String second, String milles) {
        this.hour = Integer.parseInt(hour);
        this.minute = Integer.parseInt(minute);
        this.second = Integer.parseInt(second);
        this.milles = Integer.parseInt(milles);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMilles() {
        return milles;
    }

    public void setMilles(int milles) {
        this.milles = milles;
    }
}
