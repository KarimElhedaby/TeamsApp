package com.teamapp.teamapp.utils;

import android.content.Context;
import android.content.Intent;

import com.teamapp.teamapp.event.model.EventData;
import com.teamapp.teamapp.event.ui.EventDetails;

/**
 * Created by karim on 3/2/18.
 */

public class ActivityLauncher {

    public static final String Event_KEY = "event";



    public static void openEvent_DetailsActivity(Context context, EventData eventData){
        Intent i = new Intent(context, EventDetails.class);
        i.putExtra(Event_KEY, eventData);
        context.startActivity(i);
    }

}
