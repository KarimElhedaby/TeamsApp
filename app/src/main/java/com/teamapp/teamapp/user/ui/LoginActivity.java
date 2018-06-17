package com.teamapp.teamapp.user.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.ui.MainActivity;
import com.teamapp.teamapp.user.model.User;
import com.teamapp.teamapp.utils.MyApplication;
import com.teamapp.teamapp.utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.LoginemailET)
    EditText emailET;
    @BindView(R.id.LoginPasswordET)
    EditText passwordET;
    @BindView(R.id.loginB)
    Button loginB;
    @BindView(R.id.gmailloginTV)
    TextView gmailloginTV;
    @BindView(R.id.RegisterTV)
    TextView registerTV;
    @BindView(R.id.forgotpassTV)
    TextView forgotpassTV;

    List<User> users;
    String password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (MyApplication.getPrefManager(LoginActivity.this).getUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        AndroidNetworking.initialize(getApplicationContext());


    }

    private void get_EnteredData() {
        password = passwordET.getText().toString().trim();
        email = emailET.getText().toString().trim();
    }

    @OnClick(R.id.loginB)
    public void Get_allMails() {
        get_EnteredData();
        if (email.isEmpty()) {
            emailET.setError(getString(R.string.enter_email));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError(getString(R.string.email_not_formatted));
        } else if (password.isEmpty()) {
            passwordET.setError(getString(R.string.enter_password));
        } else {
            Utilities.showLoadingDialog(this, Color.GRAY);

            AndroidNetworking.get("http://team-space.000webhostapp.com/index.php/api/users")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(new User().getClass(),
                            new ParsedRequestListener<List<User>>() {
                                @Override
                                public void onResponse(List<User> response) {
                                    Utilities.dismissLoadingDialog();
                                    users = response;
                                    Log.d("Recived_users", "Number of user_mention_row received: " +
                                            users.toString());

                                    if (check_MailConsist() == true) {
                                        check_MailConsist();

                                    } else {
                                        passwordET.setError("Error inEmail or Password");

                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Log.e("Recived_users", anError.toString());

                                }
                            });
        }

    }

    public boolean check_MailConsist() {
        get_EnteredData();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)
                    && users.get(i).getPassword().equals(password)) {
                Toast.makeText(LoginActivity.this, "true login", Toast.LENGTH_LONG).show();

                // storing user_mention_row in shared preferences
                MyApplication.getPrefManager(LoginActivity.this).storeUser(users.get(i));
                // start main activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                return true;
            }
        }
        return false;
    }

    @OnClick(R.id.RegisterTV)
    public void Register() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}