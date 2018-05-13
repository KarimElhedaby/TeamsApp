package com.teamapp.teamapp.event.ui;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.adapter.My_EventAdapter;
import com.teamapp.teamapp.event.model.EventData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventFragment extends Fragment {


    @BindView(R.id.recycler_my_events)
    RecyclerView recycler_my_events;

    private My_EventAdapter my_eventAdapter;
    private List<EventData> eventData;
    private EventData data;

    public MyEventFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MyEventFragment newInstance() {
        MyEventFragment fragment = new MyEventFragment();
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

        View view = inflater.inflate(R.layout.fragment_my_event, container, false);
        eventData = new ArrayList<>();
        ButterKnife.bind(this, view);
        recycler_my_events.setLayoutManager
                (new LinearLayoutManager(view.getContext()
                        , LinearLayoutManager.VERTICAL, false));

        my_eventAdapter = new My_EventAdapter(eventData, R.layout.my_events_item, view.getContext());
        add_testData();
        recycler_my_events.setAdapter(my_eventAdapter);
        return view;

    }

    void add_testData() {

        data = new EventData(10, 7, "run", "marthon run");
        my_eventAdapter.addData(data);
        data = new EventData(11, 12, "write", "writting event");
        my_eventAdapter.addData(data);
        data = new EventData(12, 7, "science", "science festival");
        my_eventAdapter.addData(data);
        data = new EventData(10, 5, "7amada", "event fe el7amada 5ales");
        my_eventAdapter.addData(data);


    }

}