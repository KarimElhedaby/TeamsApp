package com.teamapp.teamapp.event.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.model.EventData;
import com.teamapp.teamapp.utils.ActivityLauncher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetails extends AppCompatActivity {

    @BindView(R.id.eventTitle_details_TV)
    TextView eventTitle_details_TV;
    @BindView(R.id.eventDescription_details_TV)
    TextView eventDescrption_details_TV;
    @BindView(R.id.event_community_details_TV)
    TextView eventCommunity_details_TV;
    @BindView(R.id.eventLocation_details_TV)
    TextView eventLocation_details_TV;
    @BindView(R.id.eventDate_details_TV)
    TextView eventDate_details_TV;
    @BindView(R.id.eventTime_details_TV)
    TextView eventTime_details_TV;

    private EventData eventData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            eventData = (EventData) bundle.getSerializable(ActivityLauncher.Event_KEY);
            fillEvent_Data();
        } else {
            finish();
        }

    }

    private void fillEvent_Data() {


        eventTitle_details_TV.setText(eventData.getEvent_title());
        eventDescrption_details_TV.setText(eventData.getEvent_description());
        eventDate_details_TV.setText(String.valueOf(eventData.getEvent_day()));
        eventTime_details_TV.setText(String.valueOf(eventData.getEven_month()));

    }

}
