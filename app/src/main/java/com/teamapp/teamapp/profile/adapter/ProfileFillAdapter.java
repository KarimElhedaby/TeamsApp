package com.teamapp.teamapp.profile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teamapp.teamapp.R;
import com.teamapp.teamapp.profile.model.ProfileData;

import java.util.List;

/**
 * Created by karim on 2/19/18.
 */

public class ProfileFillAdapter extends RecyclerView.Adapter<ProfileFillAdapter.VH> {
    private List<ProfileData> dataList;
    private int rowLayout;
    private Context context;


    public static class VH extends RecyclerView.ViewHolder {
        public RelativeLayout viewBackground, viewForeground;

        TextView data;


        public VH(View v) {
            super(v);

            data =  v.findViewById(R.id.profileF_recycle_dataTV);
            viewBackground = v.findViewById(R.id.view_background);
            viewForeground = v.findViewById(R.id.view_foreground);
        }
    }

    public void addData(ProfileData data) {
        dataList.add(data);
        notifyDataSetChanged();
    }


    public ProfileFillAdapter(List<ProfileData> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ProfileFillAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.data.setText(dataList.get(position).getData());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

