package com.teamapp.teamapp.group.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.gturedi.views.StatefulLayout;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.community.model.Community;
import com.teamapp.teamapp.group.adapter.MyGroups_Adapter;
import com.teamapp.teamapp.group.model.Group;
import com.teamapp.teamapp.utils.ActivityLauncher;
import com.teamapp.teamapp.utils.RecyclerItemClickListener;
import com.teamapp.teamapp.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupActivity extends AppCompatActivity {

    @BindView(R.id.recycler_groups)
    RecyclerView recyclerMy_groups;

    private MyGroups_Adapter myGroups_adapter;
    private List<Group> groupList;
    private Group group;
    Community community;

    StatefulLayout stateful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        stateful = (StatefulLayout) findViewById(R.id.stateful);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myGroups_toolbar);
        setSupportActionBar(toolbar);
        groupList = new ArrayList<>();
        ButterKnife.bind(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            community = (Community) bundle.getSerializable(ActivityLauncher.MYCOMMUNITY_KEY);

            getMy_Group_Data();
            getSupportActionBar().setTitle(community.getCommunity_name() + " Groups");
        } else {
            finish();
        }
        recyclerMy_groups.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerMy_groups
                        , new RecyclerItemClickListener.OnItemClickListener() {


                    @Override
                    public void onItemClick(View view, int position) {
                        group = groupList.get(position);
                        ActivityLauncher.openMyGroup_DetailsActivity(GroupActivity.this, group);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                })
        );
    }

    public void getMy_Group_Data() {
        Utilities.showLoadingDialog(getApplicationContext(), R.color.colorPrimary);
        AndroidNetworking.get("http://team-space.000webhostapp.com/index.php/api/groups")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(new Group().getClass(),
                        new ParsedRequestListener<List<Group>>() {
                            @Override
                            public void onResponse(List<Group> response) {
                                for (int i = 0; i < response.size(); i++) {

                                    if (response.get(i).getCommunity_Community_id1()
                                            == community.getCommunity_id()) {

                                            group = response.get(i);
                                            groupList.add(group);
                                            Utilities.dismissLoadingDialog();

                                            if (groupList.size() == 0) {stateful.showEmpty();}

                                        recyclerMy_groups.setLayoutManager
                                                (new LinearLayoutManager(getApplicationContext()
                                                        , LinearLayoutManager.VERTICAL, false));

                                        myGroups_adapter = new MyGroups_Adapter(groupList,
                                                R.layout.my_groups_row, getApplicationContext());

                                        recyclerMy_groups.setAdapter(myGroups_adapter);

                                        Log.d("Groups_selected", "Number of user received: "
                                                + groupList.toString());
                                    }

                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Groups_selected", anError.toString());
                                stateful.showError(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getMy_Group_Data();
                                    }
                                });

                            }
                        });

    }

}
