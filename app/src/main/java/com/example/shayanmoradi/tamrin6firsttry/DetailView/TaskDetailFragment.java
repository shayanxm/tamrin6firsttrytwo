package com.example.shayanmoradi.tamrin6firsttry.DetailView;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shayanmoradi.tamrin6firsttry.MainView.MainActivity;
import com.example.shayanmoradi.tamrin6firsttry.Model.Task;
import com.example.shayanmoradi.tamrin6firsttry.Model.TaskManager;
import com.example.shayanmoradi.tamrin6firsttry.R;
import com.example.shayanmoradi.tamrin6firsttry.getInfromation.GetInfoActivity;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends DialogFragment {
    private TextView title;
    public Task task;
    private TextView descTxt;
    private TextView timeTxt;
    private TextView dateTxt;
    private Button delteBtn;
    private Button editBtn;
    private Button doneBtn;
    private boolean deleteOrNo;
    private LinearLayout dilaogLinear;
    public static final String ARG_CRIME_ID = "crimeId";

    public static TaskDetailFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public TaskDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);


        final UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        title = view.findViewById(R.id.detail_title);
        delteBtn = view.findViewById(R.id.delete_task);
        editBtn = view.findViewById(R.id.edit_task);
        doneBtn = view.findViewById(R.id.done_task);
        descTxt = view.findViewById(R.id.detail_des);
        timeTxt = view.findViewById(R.id.detail_time);
        dateTxt = view.findViewById(R.id.detail_date);
        dilaogLinear = view.findViewById(R.id.dialog_date_date_picker);

        task = TaskManager.getInstance().getask(crimeId);

        setViewsDetialsUp();

        delteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AreYouSureToDeleteFragment datePickerFragment = new AreYouSureToDeleteFragment();
                datePickerFragment.setTargetFragment(TaskDetailFragment.this,
                        0);
                datePickerFragment.show(getFragmentManager(), "MyDialog");

            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setYesForEditNoForCreate(true);
                startActivity(GetInfoActivity.newIntent(getActivity(), crimeId));
            }
        });
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task.getZeroForAlloneForDoneTwoForUnDone() != 1) {
                    editTask();
                }
                getActivity().finish();
//                startActivity(MainActivityity.newIntent(getActivity()));
            }
        });

        new AlertDialog.Builder(getActivity())
                .setView(dilaogLinear)
                .setTitle("test")

                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Date date = new GregorianCalendar(year, month, day).getTime();
//                        sendResult(date);
                    }
                }).create();
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icons8_checkmark_filled_100)
                .setTitle("hg")
                .setPositiveButton("po",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
//                                (getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton("neg",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
//                                ((FragmentAlertDialog)getActivity()).doNegativeClick();
                            }
                        }
                )
                .create();
    }

    private void setViewsDetialsUp() {
        title.setText(task.getTitle() + "");
        if (task.getmDescription() == "null841") {
            descTxt.setText("no decription!");
        }
        descTxt.setText(task.getmDescription());

        timeTxt.setText(task.getSimpleTime() + "");

        dateTxt.setText(task.getSimpleDate());
    }

    private void editTask() {
        task.setZeroForAlloneForDoneTwoForUnDone(1);
//        TaskManager.getInstance().addTask(task, 1);
//        TaskManager.getInstance().deleteTaskUNDone(task);
//        TaskManager.getInstance().deleteTaskUNDone(task);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {

            deleteOrNo = (boolean) data.getSerializableExtra("test");
            if (deleteOrNo == true) {
                deleteTask();
            }

        }

    }

    private void deleteTask() {
        TaskManager.getInstance().deleteTask(task);

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
