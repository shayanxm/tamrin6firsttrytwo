package com.example.shayanmoradi.tamrin6firsttry.RecyclerStaffs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.shayanmoradi.tamrin6firsttry.DetailView.DetailtaskActivity;
import com.example.shayanmoradi.tamrin6firsttry.MainView.MainActivity;
import com.example.shayanmoradi.tamrin6firsttry.Model.Task;
import com.example.shayanmoradi.tamrin6firsttry.R;

public class TaskHolder extends RecyclerView.ViewHolder {

    private TextView mTitleTextView;
    private TextView firstLetter;

    private Task mTask;
    Context context;
    public TaskHolder(@NonNull final View itemView) {
        super(itemView);

        mTitleTextView = itemView.findViewById(R.id.task_title);
        firstLetter = itemView.findViewById(R.id.first_letter_of_task);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //      for task click

                mTask.setYesForEditNoForCreate(false);
               itemView.getContext();
//             DialogFragment newFragment = TaskDetailFragment.newInstance(
//                        mTask.getmTaskId());

               // FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();

                MainActivity f = new MainActivity();
           //   f.showDialog(mTask.getmTaskId());
                // Return the fragment manager
//
//
//                public  void showDialog(UUID id) {
//                    DialogFragment newFragment = TaskDetailFragment.newInstance(
//                            id);
//
//                    newFragment.show(getSupportFragmentManager(), "dialog");
//                }
                Intent intent = DetailtaskActivity.newIntent(itemView.getContext(), mTask.getmTaskId());
                itemView.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Task crime) {
        mTask = crime;

        if (crime.getTitle() != null) {
            mTitleTextView.setText(crime.getTitle());
            String firstLetterString = crime.getTitle().substring(0, 1);
            firstLetter.setText(firstLetterString);
        } else {
            mTitleTextView.setText("uncomplited task");
        }

//        mDateTextView.setText(crime.getDate().toString());
//        mSolvedImageView.setVisibility(crime.isSolved() == true ? View.VISIBLE : View.GONE);
    }
    void showDialog() {

    }
}

