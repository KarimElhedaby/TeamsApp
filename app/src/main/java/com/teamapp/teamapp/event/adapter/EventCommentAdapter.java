package com.teamapp.teamapp.event.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.model.Comment_FeedbackData;

import java.util.List;

/**
 * Created by karim on 2/24/18.
 */

public class EventCommentAdapter extends RecyclerView.Adapter<EventCommentAdapter.VH> {
    private List<Comment_FeedbackData> dataList;
    private int rowLayout;
    private Context context;

    public static class VH extends RecyclerView.ViewHolder {


        TextView data;


        public VH(View v) {
            super(v);

            data = (TextView) v.findViewById(R.id.Comment_contentTV);
        }
    }

    public void addData(Comment_FeedbackData data) {
        dataList.add(data);
        notifyDataSetChanged();
    }


    public EventCommentAdapter(List<Comment_FeedbackData> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public EventCommentAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new EventCommentAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(EventCommentAdapter.VH holder, int position) {
        holder.data.setText(dataList.get(position).getData());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
