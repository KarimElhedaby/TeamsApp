package com.teamapp.teamapp.profile.ui;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.profile.adapter.ProfileFillAdapter;
import com.teamapp.teamapp.profile.model.ProfileData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileViewActivity extends AppCompatActivity {

    @BindView(R.id.profile_Interests_View_RV)
    RecyclerView profileF_Interest_view_RV;
    @BindView(R.id.profile_skills_View_RV)
    RecyclerView profileF_skill_view_RV;

    private ProfileFillAdapter interest_adapter;
    private ProfileFillAdapter skill_adapter;
    private List<ProfileData> data_interest_List;
    private List<ProfileData> data_skill_list;
    private ProfileData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile_view);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        data_interest_List = new ArrayList<>();
        data_skill_list = new ArrayList<>();
        profileF_Interest_view_RV.setLayoutManager
                (new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false));

        profileF_skill_view_RV.setLayoutManager
                (new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false));


        interest_adapter = new ProfileFillAdapter(data_interest_List, R.layout.profile_recycler_item, this);
        skill_adapter = new ProfileFillAdapter(data_skill_list, R.layout.profile_recycler_item, this);

        add_testData();
        profileF_Interest_view_RV.setAdapter(interest_adapter);
        profileF_skill_view_RV.setAdapter(skill_adapter);


    }
    void add_testData() {

        data = new ProfileData("playing football");
        interest_adapter.addData(data);
        data = new ProfileData("read story football");
        interest_adapter.addData(data);
        data = new ProfileData( "swim day");
        interest_adapter.addData(data);
        data = new ProfileData( "playing football");
        interest_adapter.addData(data);

        data = new ProfileData("playing football");
        skill_adapter.addData(data);
        data = new ProfileData("read story football");
        skill_adapter.addData(data);
        data = new ProfileData( "swim day");
        skill_adapter.addData(data);
        data = new ProfileData( "playing football");
        skill_adapter.addData(data);

    }

}
