package com.teamapp.teamapp.event.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.model.EventData;

import java.util.List;

/**
 * Created by karim on 3/1/18.
 */

public class My_EventAdapter extends RecyclerView.Adapter<My_EventAdapter.VH> {
    private List<EventData> dataList;
    private int rowLayout;
    private Context context;

    public static class VH extends RecyclerView.ViewHolder {


        TextView eventmonthTV;
        TextView eventdayTV;
        TextView eventTitleTV;
        TextView eventDescriptionTV;


        public VH(View v) {
            super(v);

            eventmonthTV = (TextView) v.findViewById(R.id.my_eventmonthTV);
            eventdayTV = (TextView) v.findViewById(R.id.my_eventdayTV);
            eventTitleTV = (TextView) v.findViewById(R.id.my_eventTitleTV);
            eventDescriptionTV = (TextView) v.findViewById(R.id.my_eventDescriptionTV);

        }
    }

    public void addData(EventData data) {
        dataList.add(data);
        notifyDataSetChanged();
    }


    public My_EventAdapter(List<EventData> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public My_EventAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new My_EventAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(My_EventAdapter.VH holder, int position) {
        holder.eventdayTV.setText(String.valueOf(dataList.get(position).getEvent_day()));
        holder.eventmonthTV.setText(String.valueOf(dataList.get(position).getEven_month()));
        holder.eventDescriptionTV.setText(dataList.get(position).getEvent_description());
        holder.eventTitleTV.setText(dataList.get(position).getEvent_title());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

