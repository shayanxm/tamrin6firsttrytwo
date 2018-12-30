package com.example.shayanmoradi.tamrin6firsttry.TimeAndDateStuffs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.shayanmoradi.tamrin6firsttry.R;

import java.util.Date;

public class DateTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_continer);
Date date = (Date) getIntent().getSerializableExtra("test");

        Toast.makeText(this,date+"testing",Toast.LENGTH_LONG).show();
//        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(date);
      //  DatePickerFragment fragment =  DatePickerFragment.newInstance(date);
        DatePickerFragment fragment1= DatePickerFragment.newInstance(date);
//   fragment1.newInstance(date);

        //  TaskDetailFragment.newInstance(taskId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.times_continer, fragment1)

                .commit();
    }
}
