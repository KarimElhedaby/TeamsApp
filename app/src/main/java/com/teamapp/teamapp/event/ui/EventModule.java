package com.teamapp.teamapp.event.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.adapter.All_EventAdapter;
import com.teamapp.teamapp.event.model.EventData;
import com.teamapp.teamapp.ui.HomeActivity;
import com.teamapp.teamapp.utils.ActivityLauncher;

public class EventModule extends AppCompatActivity implements All_EventAdapter.OnEventClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_module);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.all_events:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.event_container,
                                    AllEventsFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.my_events:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.event_container,
                                    MyEventFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_optionl_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.event_home) {
            startActivity(new Intent(EventModule.this, HomeActivity.class));
            return true;
        } else if (id == R.id.event_create) {
            startActivity(new Intent(EventModule.this, CreateEventActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onEventClick(EventData eventData) {
        ActivityLauncher.openEvent_DetailsActivity(this,eventData);
    }
}
