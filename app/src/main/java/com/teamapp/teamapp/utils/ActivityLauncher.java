package com.teamapp.teamapp.utils;

import android.content.Context;
import android.content.Intent;

import com.teamapp.teamapp.community.model.Community;
import com.teamapp.teamapp.event.model.EventData;
import com.teamapp.teamapp.event.ui.EventDetails;
import com.teamapp.teamapp.group.model.Group;
import com.teamapp.teamapp.group.ui.GroupActivity;
import com.teamapp.teamapp.members.ui.MemberView_Activity;

/**
 * Created by karim on 3/2/18.
 */

public class ActivityLauncher {

    public static final String Event_KEY = "event";
    public static final String MYCOMMUNITY_KEY = "my_community";
    public static final String MYGROUP_KEY = "my_group";

    public static void openEvent_DetailsActivity(Context context, EventData eventData) {
        Intent i = new Intent(context, EventDetails.class);
        i.putExtra(Event_KEY, eventData);
        context.startActivity(i);
    }

    public static void openMyCommunity_DetailsActivity(Context context, Community community) {
        Intent i = new Intent(context, GroupActivity.class);
        i.putExtra(MYCOMMUNITY_KEY, community);
        context.startActivity(i);
    }

    public static void openMyGroup_DetailsActivity(Context context, Group group) {
        Intent i = new Intent(context,MemberView_Activity.class);
        i.putExtra(MYGROUP_KEY, group);
        context.startActivity(i);
    }


}
