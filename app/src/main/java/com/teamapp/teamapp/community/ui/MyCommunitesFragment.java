package com.teamapp.teamapp.community.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.community.adapter.MyCommunites_Adapter;
import com.teamapp.teamapp.community.model.Community;
import com.teamapp.teamapp.utils.ActivityLauncher;
import com.teamapp.teamapp.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyCommunitesFragment extends Fragment implements
        MyCommunites_Adapter.On_myCommunity_ClickListener {

    @BindView(R.id.recycler_my_Communites)
    RecyclerView recyclerMy_communites;
    int USER_PREF_ID = MyApplication.getPrefManager(getContext()).getUser().getUser_id();
    private MyCommunites_Adapter myCommunites_adapter;
    private List<Community> communityList;
    private Community community;

    public MyCommunitesFragment() {
        // Required empty public constructor
    }

    public static MyCommunitesFragment newInstance(String param1, String param2) {
        MyCommunitesFragment fragment = new MyCommunitesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_communites, container, false);
        communityList = new ArrayList<>();
        ButterKnife.bind(this, view);
        getcommunity_Data();
        return view;
    }

    public void getcommunity_Data() {
        AndroidNetworking.get("http://team-space.000webhostapp.com/index.php/api/community")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(new Community().getClass(),
                        new ParsedRequestListener<List<Community>>() {
                            @Override
                            public void onResponse(List<Community> response) {
                                for (int i = 0; i < response.size(); i++) {
                                    if (response.get(i).getUsers_User_id() == USER_PREF_ID) {

                                        community = response.get(i);
                                        communityList.add(community);

                                        recyclerMy_communites.setLayoutManager
                                                (new LinearLayoutManager(getContext()
                                                        , LinearLayoutManager.VERTICAL, false));
                                        myCommunites_adapter = new MyCommunites_Adapter(communityList,
                                                R.layout.my_communites_row, getContext());
                                        recyclerMy_communites.setAdapter(myCommunites_adapter);

                                        Log.d("Community_selected", "Number of user received: "
                                                + communityList.toString());
                                    }

                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Community_selected", anError.toString());

                            }
                        });

    }

    @Override
    public void on_myCommunity_Click(Community community) {
        ActivityLauncher.openMyCommunity_DetailsActivity(getContext(), community);
    }
}