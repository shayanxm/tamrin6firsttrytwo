package com.example.shayanmoradi.tamrin6firsttry.DetailView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.shayanmoradi.tamrin6firsttry.Model.Task;
import com.example.shayanmoradi.tamrin6firsttry.R;

import java.util.UUID;

public class DetailtaskActivity extends AppCompatActivity {
    public static UUID baseId;
    private Task taski = new Task();
    private static final String EXRTA_TASK_ID = "com.example.shayanmoradi.tamrin6firsttry.DetailView.task_id";
    public static final String ARG_CRIME_ID = "crimeId";
    private static final String DIALOG_TAG = "DialogDate";

    public static Intent newIntent(Context context, UUID taskId) {
        Intent intent = new Intent(context, DetailtaskActivity.class);
        intent.putExtra(EXRTA_TASK_ID, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtask);
        baseId = (UUID) getIntent().getSerializableExtra(EXRTA_TASK_ID);
//showDialog();
        TaskDetailFragment.newInstance(baseId);
        Fragment fragment = TaskDetailFragment.newInstance(baseId);
        getSupportFragmentManager()
                .beginTransaction()

                .replace(R.id.detail_container, fragment)
                .commit();


    }
//    void showDialog() {
////        TaskDetailFragment newFragment = TaskDetailFragment();
////        newFragment.show(getFragmentManager(), "dialog");
//        TaskDetailFragment timePickerFragment = TaskDetailFragment.newInstance(baseId);
//        timePickerFragment.setTargetFragment(DetailtaskActivity.this,
//                0);
//        timePickerFragment.show(getFragmentManager(),DIALOG_TAG);
//    }


//void showDialog() {
//    DialogFragment newFragment = TaskDetailFragment.newInstance(
//    baseId);
//    newFragment.show(getSupportFragmentManager(), "dialog");
//}
}
