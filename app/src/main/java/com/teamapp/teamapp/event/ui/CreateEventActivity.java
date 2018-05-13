package com.teamapp.teamapp.event.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.utils.DatePickerFragment;
import com.teamapp.teamapp.utils.TimePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEventActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    String am;
    String month_name;
    @BindView(R.id.eventTitleET)
    EditText eventTitleET;
    @BindView(R.id.eventDescriptionET)
    EditText eventDescrptionET;
    @BindView(R.id.spinner)
    Spinner eventSpinner;
    @BindView(R.id.event_community_TV)
    TextView eventCommunityTV;
    @BindView(R.id.eventLocationET)
    EditText eventLocationET;
    @BindView(R.id.eventAddDateTV)
    TextView eventAddDateTV;
    @BindView(R.id.eventAddTimeTV)
    TextView eventAddTimeTV;
    @BindView(R.id.eventInviteMembersIB)
    ImageButton eventInviteMembersIB;
    @BindView(R.id.event_invite_MembersRV)
    RecyclerView event_invite_MembersRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        ButterKnife.bind(this);
        eventSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        List<String> communites_list = new ArrayList<String>();
        communites_list.add("BR");
        communites_list.add("HR");
        communites_list.add("LOGISTCS");
        communites_list.add("MEDIA");
        communites_list.add("IT");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, communites_list);
// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        eventSpinner.setAdapter(dataAdapter);

        setDefault_Date();
    }

    public void setDefault_Date() {

        Calendar c = Calendar.getInstance();
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR) - 2000;


        switch (month) {
            case 1:
                month_name = "JAN";
                break;
            case 2:
                month_name = "FEB";
                break;
            case 3:
                month_name = "MAR";
                break;
            case 4:
                month_name = "APR";
                break;
            case 5:
                month_name = "MAY";
                break;
            case 6:
                month_name = "JUNE";
                break;
            case 7:
                month_name = "JULY";
                break;
            case 8:
                month_name = "AUG";
                break;
            case 9:
                month_name = "AUG";
                break;
            case 10:
                month_name = "OCT";
                break;
            case 11:
                month_name = "NOV";
                break;
            case 12:
                month_name = "DEC";
                break;


        }

        String date = day + " " + (month_name) + "" + (year);


        if (hour < 12)
            am = "AM";
        else {
            am = "PM";
            hour = hour - 12;
        }

        String time = (hour) + "  " + minutes + "  " + am;

        eventAddDateTV.setText(date);
        eventAddTimeTV.setText(time);


    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        eventCommunityTV.setText(item);

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        eventCommunityTV.setError("please choose community");
    }

    @OnClick(R.id.eventAddDateTV)
    public void addEvent_Date() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    @OnClick(R.id.eventAddTimeTV)
    public void addEvent_Time() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        switch (month + 1) {
            case 1:
                month_name = "JAN";
                break;
            case 2:
                month_name = "FEB";
                break;
            case 3:
                month_name = "MAR";
                break;
            case 4:
                month_name = "APR";
                break;
            case 5:
                month_name = "MAY";
                break;
            case 6:
                month_name = "JUNE";
                break;
            case 7:
                month_name = "JULY";
                break;
            case 8:
                month_name = "AUG";
                break;
            case 9:
                month_name = "AUG";
                break;
            case 10:
                month_name = "OCT";
                break;
            case 11:
                month_name = "NOV";
                break;
            case 12:
                month_name = "DEC";
                break;


        }

        eventAddDateTV.setText(dayOfMonth + " " + (month_name) + "" + (year - 2000));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        eventAddTimeTV.setText((hourOfDay) + "  " + minute);
        if (hourOfDay < 12)
            am = "AM";
        else {
            am = "PM";
            hourOfDay = hourOfDay - 12;
        }

        eventAddTimeTV.setText((hourOfDay) + "  " + minute + "  " + am);


    }
}
