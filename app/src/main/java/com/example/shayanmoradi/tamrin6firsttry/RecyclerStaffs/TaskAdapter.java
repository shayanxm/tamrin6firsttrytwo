package com.example.shayanmoradi.tamrin6firsttry.RecyclerStaffs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shayanmoradi.tamrin6firsttry.Model.Task;
import com.example.shayanmoradi.tamrin6firsttry.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
    private List<Task> mTask;
    private Context context;
    public TaskAdapter(List<Task> tasks) {
        mTask = tasks;
    }


    public void setCrimes(List<Task> tasks) {
        mTask = tasks;
    }


    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_task, parent, false);
        TaskHolder crimeHolder = new TaskHolder(view);
        context=view.getContext();
        return crimeHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        Task task = mTask.get(position);
      if ( mTask.size()==0)
          Toast.makeText(context,"nothing to show",Toast.LENGTH_SHORT);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }

}
