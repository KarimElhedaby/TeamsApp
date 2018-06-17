package com.teamapp.teamapp.community.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.community.adapter.MyCommunites_Adapter;
import com.teamapp.teamapp.community.model.Community;
import com.teamapp.teamapp.group.model.Group;
import com.teamapp.teamapp.schedule.ui.ScheduleFragment;
import com.teamapp.teamapp.utils.ActivityLauncher;

public class CommunityActivity extends AppCompatActivity
        implements MyCommunites_Adapter.On_myCommunity_ClickListener {
    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.communitycontainer);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommunityActivity.this,
                        CreateCommunityActivity.class));
            }
        });

    }

    @Override
    public void on_myCommunity_Click(Community community) {
        ActivityLauncher.openMyCommunity_DetailsActivity(this, community);
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MyCommunitesFragment();

                case 1:
                    return new ScheduleFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "My Communites ";
                case 1:
                    return "Communites";

            }
            return null;
        }


    }
}



