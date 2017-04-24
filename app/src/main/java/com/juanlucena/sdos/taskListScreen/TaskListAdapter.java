package com.juanlucena.sdos.taskListScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.juanlucena.sdos.R;
import com.juanlucena.sdos.model.Task;
import com.orm.SugarContext;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListAdapterViewHolder>{

    private List<Task> taskList;

    public TaskListAdapter(List<Task> taskList){
        this.taskList = taskList;
    }

    public static class TaskListAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewTaskName)TextView textViewTaskName;
        @BindView(R.id.textViewTaskTime)TextView textViewTaskTime;
        @BindView(R.id.textViewTaskDescription)TextView textViewTaskDescription;
        @BindView(R.id.checkBoxTaskFinished) CheckBox checkBoxTaskFinished;


        public TaskListAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public TaskListAdapter.TaskListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_task_list, parent, false);
        return new TaskListAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.TaskListAdapterViewHolder holder, final int position) {

        final Context context = holder.itemView.getContext();

        holder.textViewTaskName.setText(taskList.get(position).getTaskName());
        holder.textViewTaskTime.setText(Integer.toString(taskList.get(position).getTaskTime()));
        holder.textViewTaskDescription.setText(taskList.get(position).getTaskDescription());

        if(taskList.get(position).isFinished()){
            holder.checkBoxTaskFinished.setChecked(true);
        }

        holder.checkBoxTaskFinished.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SugarContext.init(context);
                taskList.get(position).setFinished(b);
                taskList.get(position).save();
                SugarContext.terminate();
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
