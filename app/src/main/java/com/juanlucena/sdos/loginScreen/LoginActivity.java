package com.juanlucena.sdos.loginScreen;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.juanlucena.sdos.R;
import com.juanlucena.sdos.webserviceScreen.WebserviceActivity;
import com.juanlucena.sdos.model.User;
import com.juanlucena.sdos.utils.Utils;
import com.orm.SugarContext;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginScreen{

    @BindView(R.id.editTextUsername) EditText editTextUsername;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.loadingFragment) LinearLayout loadingFragment;
    @BindView(R.id.parentView) View parentView;

    private final String USER_ID = "userId";
    private final String IS_ADMIN = "isAdmin";

    private LoginPresenter loginPresenter = new LoginPresenterImpl(LoginActivity.this, LoginActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if(!Utils.checkIfDbExists(LoginActivity.this)) {
            loginPresenter.buildDatabase();
        }
    }

    public void doLogin(View view){

        SugarContext.init(LoginActivity.this);
        List<User> userList = User.find(User.class, "username = ? and password = ?", editTextUsername.getText().toString(), editTextPassword.getText().toString());
        SugarContext.terminate();

        if(userList.size() == 1){
            Intent intent = new Intent(LoginActivity.this, WebserviceActivity.class);
            intent.putExtra(USER_ID, userList.get(0).getUserId());
            intent.putExtra(IS_ADMIN, userList.get(0).isAdministrator());
            startActivity(intent);
        }else{
            editTextUsername.setError(getResources().getString(R.string.loginCredentialsError));
            editTextPassword.setError(getResources().getString(R.string.loginCredentialsError));
        }
    }

    @Override
    public void showProgress() {
        loadingFragment.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingFragment.setVisibility(View.GONE);
    }

    @Override
    public void databaseInitialized(BroadcastReceiver receiver) {
        unregisterReceiver(receiver);
        Snackbar.make(parentView, getResources().getString(R.string.loginDatabaseInitialized), Snackbar.LENGTH_LONG).show();
    }
}
