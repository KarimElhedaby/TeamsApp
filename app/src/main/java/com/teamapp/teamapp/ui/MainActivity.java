package com.teamapp.teamapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.ui.EventFeedBack;
import com.teamapp.teamapp.profile.ui.ProfileFillActivity;
import com.teamapp.teamapp.profile.ui.ProfileViewActivity;
import com.teamapp.teamapp.user.ui.LoginActivity;
import com.teamapp.teamapp.user.ui.RegisterActivity;
import com.teamapp.teamapp.utils.MyApplication;

import org.json.JSONArray;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button register, login, profile, home, feedback, profileView, logout, updateB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginB);
        register = findViewById(R.id.registerB);
        profile = findViewById(R.id.profileB);
        home = findViewById(R.id.homeB);
        profileView = findViewById(R.id.profileViewB);
        feedback = findViewById(R.id.feedbackB);
        logout = findViewById(R.id.logout);
        updateB = findViewById(R.id.updateB);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        profile.setOnClickListener(this);
        home.setOnClickListener(this);
        feedback.setOnClickListener(this);
        profileView.setOnClickListener(this);
        logout.setOnClickListener(this);
        updateB.setOnClickListener(this);


        if (MyApplication.getPrefManager(MainActivity.this).getUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
        }
        AndroidNetworking.initialize(getApplicationContext());
    }


    public void upadateData() {

        AndroidNetworking.put("http://team-space.000webhostapp.com/index.php/api/users/update/{id}")
                .addPathParameter("id", "65").
                addBodyParameter("Religious", "Muslim").
                setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "data send succsefully",
                                Toast.LENGTH_LONG).show();

                    }


                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("postData", error.getMessage());
//                            Toast.makeText(RegisterActivity.this, error.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//
//                            Log.d("Data", user.toString());

                        Toast.makeText(MainActivity.this, "data send Errorly",
                                Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));


                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginB:
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                break;
            case R.id.registerB:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                this.startActivity(intent1);
                break;
            case R.id.profileB:
                Intent intent2 = new Intent(this, ProfileFillActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.homeB:
                Intent intent3 = new Intent(this, HomeActivity.class);
                this.startActivity(intent3);
                break;
            case R.id.feedbackB:
                Intent intent4 = new Intent(this, EventFeedBack.class);
                this.startActivity(intent4);
                break;
            case R.id.profileViewB:
                Intent intent5 = new Intent(this, ProfileViewActivity.class);
                this.startActivity(intent5);
                break;

            case R.id.logout:
                MyApplication myApplication = new MyApplication();
                myApplication.logout();
                break;
            case R.id.updateB:
                upadateData();
                break;


        }

    }
}
