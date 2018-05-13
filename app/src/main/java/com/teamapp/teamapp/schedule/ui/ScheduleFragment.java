package com.teamapp.teamapp.schedule.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.ui.EventModule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    @BindView(R.id.createprojectB)
    Button createProjoctB;
    @BindView(R.id.createMeetigB)
    Button createMeetingB;
    @BindView(R.id.createEventB)
    Button createEventB;
    @BindView(R.id.createVoteB)
    Button createVoteB;


    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.createEventB)
    public void createEvent() {
        startActivity(new Intent(getActivity(), EventModule.class));

    }

}
