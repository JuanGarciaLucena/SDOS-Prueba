package com.juanlucena.sdos.taskListScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.juanlucena.sdos.R;
import com.juanlucena.sdos.model.Task;
import com.orm.SugarContext;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListActivity extends AppCompatActivity {

    @BindView(R.id.appbar) Toolbar appbar;
    @BindView(R.id.recyclerViewTaskList) RecyclerView recyclerViewTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        ButterKnife.bind(TaskListActivity.this);

        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Lista de tareas");


        Intent techIntent = getIntent();
        int userId = techIntent.getExtras().getInt("userId");

        SugarContext.init(TaskListActivity.this);
        List<Task> taskList = Task.findWithQuery(Task.class, "SELECT * FROM task WHERE task.task_user_asigned = ?", Integer.toString(userId));
        TaskListAdapter taskListAdapter = new TaskListAdapter(taskList);
        SugarContext.terminate();

        recyclerViewTaskList.setAdapter(taskListAdapter);
        recyclerViewTaskList.setLayoutManager(new GridLayoutManager(TaskListActivity.this, 1));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
