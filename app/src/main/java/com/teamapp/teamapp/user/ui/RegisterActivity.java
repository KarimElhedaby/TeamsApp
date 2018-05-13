package com.teamapp.teamapp.user.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.ui.MainActivity;
import com.teamapp.teamapp.user.model.User;
import com.teamapp.teamapp.utils.MyApplication;
import com.teamapp.teamapp.utils.Utilities;

import org.json.JSONArray;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.UsernameET)
    EditText UsernameET;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.PasswordET)
    EditText PasswordET;
    @BindView(R.id.CpasswordET)
    EditText CpasswordET;
    @BindView(R.id.genderRG)
    RadioGroup genderRG;
    @BindView(R.id.registerB)
    Button registerB;

    String userName, password, cPassword, gender, email;
    String gender_index;
    static User user;
    int user_Id;
    static User user_Pref;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MyApplication.getPrefManager(RegisterActivity.this).getUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {

            setContentView(R.layout.activity_register);
            ButterKnife.bind(this);
            AndroidNetworking.initialize(getApplicationContext());

            genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.genderRB1:
                            gender_index = "Male";
                            break;
                        case R.id.genderRB2:
                            gender_index = "Female";
                            break;

                    }
                }
            });
            Get_allMails();


        }
    }

    private void get_EnteredData() {
        userName = UsernameET.getText().toString().trim();
        password = PasswordET.getText().toString().trim();
        cPassword = CpasswordET.getText().toString().trim();
        gender = gender_index;
        email = emailET.getText().toString().trim();


    }

    public void Get_allMails() {
        AndroidNetworking.get(" http://team-space.000webhostapp.com/index.php/api/users")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(new User().getClass(),
                        new ParsedRequestListener<List<User>>() {
                            @Override
                            public void onResponse(List<User> response) {

                                users = response;
                                Log.d("Recived_users", "Number of user received: " +
                                        users.toString());
//                                Toast.makeText(RegisterActivity.this, users.toString(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Recived_users", anError.toString());

                            }
                        });
    }

    public boolean check_MailConsist() {
        get_EnteredData();
        Get_allMails();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                emailET.setError("Email Already consist");
                return true;
            }

        }
        return false;
    }

    @OnClick(R.id.registerB)
    public void postData() {
        get_EnteredData();

        if (userName.isEmpty()) {
            UsernameET.setError(getString(R.string.enter_name));
        } else if (email.isEmpty()) {
            emailET.setError(getString(R.string.enter_email));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError(getString(R.string.email_not_formatted));
        } else if (password.isEmpty()) {
            PasswordET.setError(getString(R.string.enter_password));
        } else if (password.length() < 6) {
            PasswordET.setError(getString(R.string.password_length_error));
        } else if (cPassword.isEmpty()) {
            CpasswordET.setError(getString(R.string.enter_cpassword));
        } else if (password.equals(cPassword) != true) {
            CpasswordET.setError(getString(R.string.confirm_pass_error));
        } else if (genderRG.getCheckedRadioButtonId() == -1) {
            Toast.makeText(RegisterActivity.this, R.string.check_gender, Toast.LENGTH_LONG).show();
        } else if (check_MailConsist()) {
            return;

        } else {
            Utilities.showLoadingDialog(this, Color.GRAY);
            user = new User(email, userName, password, gender);
            user_Pref = new User(user.getUser_id(), email, userName, password, gender);

            AndroidNetworking.post("http://team-space.000webhostapp.com/index.php/api/users/add")
                    .addBodyParameter(user) // posting java object
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Utilities.dismissLoadingDialog();
                        }


                        @Override
                        public void onError(ANError error) {
                            // handle error
//                            Log.d("postData", error.getMessage());
//                            Toast.makeText(RegisterActivity.this, error.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//
//                            Log.d("Data", user.toString());

                            Toast.makeText(RegisterActivity.this, "data send succsefully",
                                    Toast.LENGTH_LONG).show();
                            Log.d("Data", user.toString());
                            Utilities.dismissLoadingDialog();
                            // storing user in shared preferences
                            MyApplication.getPrefManager(RegisterActivity.this).storeUser(user_Pref);
                            // start main activity
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        }
                    });


        }
    }
}
