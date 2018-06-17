package com.teamapp.teamapp.members.ui;

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
import com.teamapp.teamapp.group.model.Group;
import com.teamapp.teamapp.members.adapter.MemberView_Adapter;
import com.teamapp.teamapp.members.model.Member;
import com.teamapp.teamapp.user.model.User;
import com.teamapp.teamapp.utils.ActivityLauncher;
import com.teamapp.teamapp.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberView_Activity extends AppCompatActivity {
    @BindView(R.id.recycler_membersView)
    RecyclerView recycler_membersView;

    private MemberView_Adapter memberView_adapter;
    private List<Member> memberList;
    private Member member;
    Group group;
    List<User> users;
    User user;
    List<User> userList;
    StatefulLayout stateful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.membersView_toolbar);
        stateful = (StatefulLayout) findViewById(R.id.stateful);

        setSupportActionBar(toolbar);
        memberList = new ArrayList<>();
        userList = new ArrayList<>();
        users = new ArrayList<>();
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            group = (Group) bundle.getSerializable(ActivityLauncher.MYGROUP_KEY);

            get_allUsers();
            Utilities.showLoadingDialog(MemberView_Activity.this, R.color.colorPrimary);
            getMembers_Data();
            getSupportActionBar().setTitle(group.getGroup_name() + " Members");
        } else {
            finish();
        }

    }

    public void get_allUsers() {
        AndroidNetworking.get(" http://team-space.000webhostapp.com/index.php/api/users")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(new User().getClass(),
                        new ParsedRequestListener<List<User>>() {
                            @Override
                            public void onResponse(List<User> response) {
                                users = response;
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Recived_users", anError.toString());

                            }
                        });

    }


    public void getMembers_Data() {

        AndroidNetworking.get("http://team-space.000webhostapp.com/index.php/api/members")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(new Member().getClass(),
                        new ParsedRequestListener<List<Member>>() {
                            @Override
                            public void onResponse(List<Member> response) {
                                for (int i = 0; i < response.size(); i++) {

                                    if ((response.get(i).getGroups_Community_Community_id1()
                                                    == group.getCommunity_Community_id1())
                                           && (response.get(i).getGroups_Group_id()
                                                    == group.getGroup_id())
                                            ) {

                                        member = response.get(i);
                                        memberList.add(member);

                                    }

                                }
                                Log.d("Members_selected", "Number of user received: "
                                        + memberList.toString());

                                add_Users();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Members_selected", anError.toString());
                                stateful.showError(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        add_Users();
                                    }
                                });
                            }
                        });

    }

    public void add_Users() {

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < memberList.size(); j++) {

                if (users.get(i).getUser_id() == memberList.get(j).getUser_id()) {
                    user = users.get(i);
                    userList.add(user);

                    recycler_membersView.setLayoutManager
                            (new LinearLayoutManager(getApplicationContext()
                                    , LinearLayoutManager.VERTICAL, false));
                    Utilities.dismissLoadingDialog();

                    memberView_adapter = new MemberView_Adapter(userList,
                            R.layout.membersview_row, getApplicationContext());
                    if (userList.size() == 0) {
                        Utilities.dismissLoadingDialog();
                        stateful.getInAnimation();
                        stateful.showEmpty();
                    }

                    recycler_membersView.setAdapter(memberView_adapter);
                    Log.d("User_selected", "Number of user received: "
                            + userList.toString());
                }
            }
        }
    }
}




