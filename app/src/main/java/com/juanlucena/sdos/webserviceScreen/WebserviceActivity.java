package com.juanlucena.sdos.webserviceScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.juanlucena.sdos.R;
import com.juanlucena.sdos.addTaskScreen.AddTaskActivity;
import com.juanlucena.sdos.loginScreen.LoginActivity;
import com.juanlucena.sdos.model.WebserviceObject;
import com.juanlucena.sdos.taskListScreen.TaskListActivity;
import com.orm.SugarContext;
import com.orm.query.Select;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebserviceActivity extends AppCompatActivity implements WebserviceScreen {

    @BindView(R.id.appbar) Toolbar appbar;
    @BindView(R.id.loadingFragment) LinearLayout loadingFragment;
    @BindView(R.id.textViewSyncMessage) TextView textViewSyncMessage;
    @BindView(R.id.parentView) View parentView;
    @BindView(R.id.recyclerViewWebService) RecyclerView recyclerViewWebService;
    @BindView(R.id.floatButton) FloatingActionButton floatButton;

    private boolean doubleBackToExitPressedOnce = false;
    private boolean isAdmin;
    private int userId;

    private final String USER_ID = "userId";
    private final String IS_ADMIN = "isAdmin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webservice);
        ButterKnife.bind(WebserviceActivity.this);

        setSupportActionBar(appbar);


        isAdmin = getIntent().getExtras().getBoolean(IS_ADMIN);
        userId = getIntent().getExtras().getInt(USER_ID);

        if(isAdmin){
            floatButton.setImageResource(R.drawable.ic_add_task);
            getSupportActionBar().setTitle("Administrador");
        }else{
            floatButton.setImageResource(R.drawable.ic_task_list);
            getSupportActionBar().setTitle("Técnico");
        }

        SugarContext.init(WebserviceActivity.this);
        if((int)WebserviceObject.count(WebserviceObject.class) == 0){
            WebservicePresenter webservicePresenter = new WebservicePresenterImpl(WebserviceActivity.this, WebserviceActivity.this);
            webservicePresenter.startWebserviceProcess();
        }else{
            populateRecyclerView();
        }
        SugarContext.terminate();
    }

    @Override
    public void populateRecyclerView() {

        SugarContext.init(WebserviceActivity.this);
        List<WebserviceObject> webserviceObjectList = Select.from(WebserviceObject.class).list();
        SugarContext.terminate();

        WebserviceRecyclerViewAdapter webserviceRecyclerViewAdapter = new WebserviceRecyclerViewAdapter(webserviceObjectList);
        recyclerViewWebService.setAdapter(webserviceRecyclerViewAdapter);
        recyclerViewWebService.setLayoutManager(new GridLayoutManager(WebserviceActivity.this, 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webservice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.menuExit:

                AlertDialog.Builder dialog = new AlertDialog.Builder(WebserviceActivity.this);
                dialog.setMessage(getResources().getString(R.string.webserviceExitConfirmation))
                        .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(WebserviceActivity.this, LoginActivity.class));
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.webserviceBackAgainToExit), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void showProgress() {
        textViewSyncMessage.setText(getResources().getString(R.string.webServiceLoadingElements));
        loadingFragment.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingFragment.setVisibility(View.GONE);
        Snackbar.make(parentView, "Carga finalizada", Snackbar.LENGTH_LONG).show();
    }

    public void floatButtonOnCLick(View view){
        if(isAdmin){
            startActivity(new Intent(WebserviceActivity.this, AddTaskActivity.class));
        }else{
            Intent intent = new Intent(WebserviceActivity.this, TaskListActivity.class);
            intent.putExtra(USER_ID, userId);
            startActivity(intent);
        }
    }
}
