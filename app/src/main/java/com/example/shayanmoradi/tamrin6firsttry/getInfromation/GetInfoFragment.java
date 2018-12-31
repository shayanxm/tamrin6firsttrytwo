package com.example.shayanmoradi.tamrin6firsttry.getInfromation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shayanmoradi.tamrin6firsttry.MainView.MainActivity;
import com.example.shayanmoradi.tamrin6firsttry.Model.Task;
import com.example.shayanmoradi.tamrin6firsttry.Model.TaskManager;
import com.example.shayanmoradi.tamrin6firsttry.R;
import com.example.shayanmoradi.tamrin6firsttry.TimeAndDateStuffs.DatePickerFragment;
import com.example.shayanmoradi.tamrin6firsttry.TimeAndDateStuffs.TimePickerFragment;
import com.example.shayanmoradi.tamrin6firsttry.utils.RandomData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetInfoFragment extends Fragment {
    private EditText editText;
    private EditText editDes;
    private Button addToList;
    private String titleeText;
    private String desText;
    private Button datePicker;
    private Button timePicker;

    private static final int REQ_DATE_PICKER = 0;
    private static final int REQ_TIME_PICKER = 1;

    private static final String ARG_CRIME_ID = "crimeId";
    private static final String DIALOG_TAG = "DialogDate";
    private Task mCrime;


    public GetInfoFragment() {
        // Required empty public constructor
    }

    public static GetInfoFragment newInstance(UUID taskId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, taskId);

        GetInfoFragment fragment = new GetInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_info, container, false);
        editText = view.findViewById(R.id.titile_geter_edit_text);
        addToList = view.findViewById(R.id.add_task_btn);
        datePicker = view.findViewById(R.id.pick_date_btn);
        timePicker = view.findViewById(R.id.pick_time_btn);
        editDes = view.findViewById(R.id.des_geter_edit_text);


        final UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        editText.setText(mCrime.getTitle());
        if (mCrime.getmDescription() != "no description enterd")
            editDes.setText(mCrime.getmDescription());
        if (mCrime.getSimpleDate() != null)
            datePicker.setText(mCrime.getSimpleDate());
        if (mCrime.getSimpleTime() != null)
            timePicker.setText(mCrime.getSimpleTime());
        mCrime = TaskManager.getInstance().getask(crimeId);

//        if (mCrime.getYesForEditNoForCreate() == false) {
//            editOrCrearte.setVisibility(View.INVISIBLE);
//        } else {
//            mCrime.setmDoneOrUnDone(editOrCrearte.isChecked());
//        }

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = RandomData.randomDate();
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCrime.getmDate());
                datePickerFragment.setTargetFragment(GetInfoFragment.this,
                        REQ_DATE_PICKER);
                datePickerFragment.show(getFragmentManager(), DIALOG_TAG);

            }
        });
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = RandomData.randomDate();
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(date);
                timePickerFragment.setTargetFragment(GetInfoFragment.this,
                        REQ_TIME_PICKER);
                timePickerFragment.show(getFragmentManager(), DIALOG_TAG);
            }
        });

        editDes.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                desText = editDes.getText().toString();


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                titleeText = editText.getText().toString();


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (titleeText != null) {
                    if (titleeText != null)
                        mCrime.setTitle(titleeText + "");
//                if (desText != null)
                    if (desText == null) {
                        mCrime.setmDescription("no description enterd");
                    } else {
                        mCrime.setmDescription(desText);
                    }
                    //TaskManager.getInstance().addTask(mCrime);
                    TaskManager.getInstance().deleteTaskDone(mCrime);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"you should enter title",Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        mCrime = TaskManager.getInstance().getask(crimeId);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mCrime.setmDate(date);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

            String time = fmt.format(date);
            if (time == null) {
                mCrime.setSimpleDate("no date");
            } else {
                mCrime.setSimpleDate(time);
            }

            datePicker.setText(time);
        }
        if (requestCode == REQ_TIME_PICKER) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            SimpleDateFormat fmt = new SimpleDateFormat("hh:mm");


            String time1 = fmt.format(date);
            if (time1 == null) {
                mCrime.setSimpleTime("no time");
            } else {
                mCrime.setSimpleTime(time1);
            }

            timePicker.setText(time1);
        }
    }

}
