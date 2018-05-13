package com.teamapp.teamapp.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.user.ui.LoginActivity;
import com.teamapp.teamapp.utils.MyApplication;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyApplication.getPrefManager(getApplicationContext()).getUser() == null) {
                    Intent intent = new Intent(SplachActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(SplachActivity.this, MainActivity.class);
                    startActivity(intent);
                }


                finish();
            }
        }, 1000);


    }
}
