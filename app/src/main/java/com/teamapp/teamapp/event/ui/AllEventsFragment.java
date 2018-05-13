package com.teamapp.teamapp.event.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.adapter.All_EventAdapter;
import com.teamapp.teamapp.event.model.EventData;
import com.teamapp.teamapp.utils.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllEventsFragment extends Fragment
        implements All_EventAdapter.OnEventClickListener{


    @BindView(R.id.recycklerallevents)
    RecyclerView recycklerallevents;

    private All_EventAdapter allEventAdapter;
    private List<EventData> eventData;
    private EventData data;

    public AllEventsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllEventsFragment newInstance() {
        AllEventsFragment fragment = new AllEventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_all_events, container, false);
        eventData = new ArrayList<>();
        ButterKnife.bind(this, view);
        recycklerallevents.setLayoutManager
                (new LinearLayoutManager(view.getContext()
                        , LinearLayoutManager.VERTICAL, false));

        allEventAdapter = new All_EventAdapter(eventData, R.layout.all_events_item, view.getContext());
        add_testData();
        recycklerallevents.setAdapter(allEventAdapter);
        return view;

    }

    void add_testData() {

        data = new EventData(10, 5, "play", "playing football");
        allEventAdapter.addData(data);
        data = new EventData(11, 12, "read", "read story football");
        allEventAdapter.addData(data);
        data = new EventData(12, 7, "swim", "swim day");
        allEventAdapter.addData(data);
        data = new EventData(10, 5, "play", "playing football");
        allEventAdapter.addData(data);

    }

    @Override
    public void onEventClick(EventData eventData) {
        ActivityLauncher.openEvent_DetailsActivity(getContext(),eventData);
    }
}
