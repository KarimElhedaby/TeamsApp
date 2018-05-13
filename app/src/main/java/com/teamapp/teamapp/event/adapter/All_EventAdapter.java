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

public class All_EventAdapter extends RecyclerView.Adapter<All_EventAdapter.VH> {
    private List<EventData> dataList;
    private int rowLayout;
    private Context context;

    private OnEventClickListener onEventActionListener;

    public static class VH extends RecyclerView.ViewHolder {


        TextView eventmonthTV;
        TextView eventdayTV;
        TextView eventTitleTV;
        TextView eventDescriptionTV;


        public VH(View v) {
            super(v);

            eventmonthTV = (TextView) v.findViewById(R.id.eventmonthTV);
            eventdayTV = (TextView) v.findViewById(R.id.eventdayTV);
            eventTitleTV = (TextView) v.findViewById(R.id.eventTitleTV);
            eventDescriptionTV = (TextView) v.findViewById(R.id.eventDescriptionTV);

        }
    }

    public void addData(EventData data) {
        dataList.add(data);
        notifyDataSetChanged();
    }


    public All_EventAdapter(List<EventData> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;

        if (context instanceof OnEventClickListener) {
            onEventActionListener = (OnEventClickListener) context;
        } else {
            throw new RuntimeException("Context must implement OnEventClickListener");
        }
    }

    @Override
    public All_EventAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new All_EventAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(final All_EventAdapter.VH holder, final int position) {
        holder.eventdayTV.setText(String.valueOf(dataList.get(position).getEvent_day()));
        holder.eventmonthTV.setText(String.valueOf(dataList.get(position).getEven_month()));
        holder.eventDescriptionTV.setText(dataList.get(position).getEvent_description());
        holder.eventTitleTV.setText(dataList.get(position).getEvent_title());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEventActionListener.onEventClick(dataList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnEventClickListener {
        void onEventClick(EventData eventData);


    }

}

