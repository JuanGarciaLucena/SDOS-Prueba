package com.juanlucena.sdos.addTaskScreen;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.juanlucena.sdos.R;
import com.juanlucena.sdos.model.Task;
import com.juanlucena.sdos.model.User;
import com.orm.SugarContext;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends AppCompatActivity {

    @BindView(R.id.appbar) Toolbar appbar;
    @BindView(R.id.spinnerTaskList) Spinner spinnerTaskList;
    @BindView(R.id.spinnerTechnicianList) Spinner spinnerTechnicianList;
    @BindView(R.id.editTextTaskTime) EditText editTextTaskTime;
    @BindView(R.id.editTextTaskDescription) EditText editTextTaskDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ButterKnife.bind(AddTaskActivity.this);

        setSupportActionBar(appbar);
        getSupportActionBar().setTitle("Nueva Tarea");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddTaskActivity.this, R.array.valuesTask, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaskList.setAdapter(adapter);

        spinnerTaskList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String query;

                if(i == 0){
                    query = "select * from user where user.has_product_replace = 1 order by user.total_hours_asigned ASC";
                }else if(i == 1){
                    query = "select * from user where user.has_charge = 1 order by user.total_hours_asigned ASC";
                }else if(i == 2){
                    query = "select * from user where user.has_envelop = 1 order by user.total_hours_asigned ASC";
                }else{
                    query = "select * from user where user.has_other = 1 order by user.total_hours_asigned ASC";
                }

                SugarContext.init(AddTaskActivity.this);
                ArrayAdapter<User> userAdapter = new ArrayAdapter<>(
                        AddTaskActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        lessHourAsignedUserList(User.findWithQuery(User.class, query)));
                SugarContext.terminate();

                spinnerTechnicianList.setAdapter(userAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.menuSave:

                if(checkForm()){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(AddTaskActivity.this);
                    dialog.setMessage("¿Guardar tarea?")
                            .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    saveTask();
                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<User> lessHourAsignedUserList(List<User> userList){

        List<User> returnList = new ArrayList<>();
        returnList.add(userList.get(0));

        for(int index = 1; index < userList.size(); index++){
            if(userList.get(index - 1).getTotalHoursAsigned() == userList.get(index).getTotalHoursAsigned()){
                returnList.add(userList.get(index));
            }else{
                break;
            }
        }
        return returnList;
    }

    private boolean checkForm(){
        if(editTextTaskTime.getText().toString().isEmpty() || editTextTaskDescription.getText().toString().isEmpty()){
            editTextTaskTime.setError("Campo vacío");
            editTextTaskDescription.setError("Campo vacío");

            return false;
        }

        return true;
    }

    private void saveTask(){

        int taskTime = Integer.valueOf(editTextTaskTime.getText().toString());
        User userSelected = (User) spinnerTechnicianList.getSelectedItem();
        int UserIdTaskAsigned = userSelected.getUserId();
        String taskName = spinnerTaskList.getSelectedItem().toString();
        String taskDescription = editTextTaskDescription.getText().toString();

        Task task = new Task(taskTime, UserIdTaskAsigned, taskName, taskDescription, false);

        userSelected.setTotalHoursAsigned(userSelected.getTotalHoursAsigned() + taskTime);

        SugarContext.init(AddTaskActivity.this);
        task.save();
        userSelected.save();
        SugarContext.terminate();

        Toast.makeText(AddTaskActivity.this, "Guardado correctamente", Toast.LENGTH_SHORT).show();

        finish();
    }
}
