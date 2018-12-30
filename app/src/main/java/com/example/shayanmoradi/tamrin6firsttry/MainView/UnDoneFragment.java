package com.example.shayanmoradi.tamrin6firsttry.MainView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shayanmoradi.tamrin6firsttry.Model.TaskManager;
import com.example.shayanmoradi.tamrin6firsttry.R;
import com.example.shayanmoradi.tamrin6firsttry.RecyclerStaffs.TaskAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnDoneFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;

    public UnDoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_un_done, container, false);

        mRecyclerView = view.findViewById(R.id.un_done_tasks_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        TaskAdapter tasksAdapter = new TaskAdapter(TaskManager.getInstance().getUndoneTasks());
//        mRecyclerView.setAdapter(tasksAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        TaskAdapter tasksAdapter = new TaskAdapter(TaskManager.getInstance().getUndoneTasks());
        mRecyclerView.setAdapter(tasksAdapter);
        tasksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        //updateUI();
    }

//    private void updateUI() {
//        TaskManager taskManager = TaskManager.getInstance();
//        List<Task> tasks = taskManager.getTasks();
//        if (mTaskAdapter == null) {
//            mTaskAdapter = new TaskAdapter(tasks);
//            mRecyclerView.setAdapter(mTaskAdapter);
//        } else {
////            mCrimeAdapter.setCrimes(crimes);
//            mTaskAdapter.notifyDataSetChanged();
//        }
   // }
}
