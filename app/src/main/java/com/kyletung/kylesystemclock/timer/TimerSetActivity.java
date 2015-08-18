package com.kyletung.kylesystemclock.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyletung.kylesystemclock.R;


/**
 * Description:
 * <br>Created on 15-8-18.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.1.3
 */
public class TimerSetActivity extends AppCompatActivity {

    private final static int RESULT_OK = 2015;

    private EditText timerSetHour;
    private EditText timerSetMinute;
    private EditText timerSetSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_set_activity);
        //
        timerSetHour = (EditText) findViewById(R.id.timer_set_hour);
        timerSetMinute = (EditText) findViewById(R.id.timer_set_minute);
        timerSetSecond = (EditText) findViewById(R.id.timer_set_second);
        Button set = (Button) findViewById(R.id.timer_set_set);
        //
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour;
                int minute;
                int second;

                if (timerSetHour.getText().toString().equals("")) {
                    hour = 0;
                } else {
                    hour = Integer.parseInt(timerSetHour.getText().toString());
                }

                if (timerSetMinute.getText().toString().equals("")) {
                    minute = 0;
                } else {
                    minute = Integer.parseInt(timerSetMinute.getText().toString());
                }

                if (timerSetSecond.getText().toString().equals("")) {
                    second = 0;
                } else {
                    second = Integer.parseInt(timerSetSecond.getText().toString());
                }

                if (minute < 60) {
                    if (second < 60) {
                        Intent intent = new Intent();
                        intent.putExtra("time_hour", hour);
                        intent.putExtra("time_minute", minute);
                        intent.putExtra("time_second", second);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(TimerSetActivity.this, "少年，数字不对哦", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TimerSetActivity.this, "少年，数字不对哦", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_set, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
