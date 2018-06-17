package com.teamapp.teamapp.group.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.group.model.Group;

import java.util.List;

/**
 * Created by karim on 6/12/18.
 */

public class MyGroups_Adapter extends RecyclerView.Adapter<MyGroups_Adapter.VH> {
    private List<Group> dataList;
    private int rowLayout;
    private Context context;

    public static class VH extends RecyclerView.ViewHolder {

        TextView my_groupDescriptionTV;
        TextView my_groupnameTV;
        ImageView my_groupIV;

        public VH(View v) {
            super(v);
            my_groupDescriptionTV = (TextView) v.findViewById(R.id.my_groupDescriptionTV);
            my_groupnameTV = (TextView) v.findViewById(R.id.my_groupTitleTV);
            my_groupIV = (ImageView) v.findViewById(R.id.mygroups_IV);

        }
    }

    public MyGroups_Adapter(List<Group> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public MyGroups_Adapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MyGroups_Adapter.VH(view);
    }

    @Override
    public void onBindViewHolder(MyGroups_Adapter.VH holder, final int position) {
        holder.my_groupDescriptionTV.setText(String.valueOf(dataList.get(position).getGroup_description()));
        holder.my_groupnameTV.setText(String.valueOf(dataList.get(position).getGroup_name()));
        Glide.with(context).load(dataList.get(position).getGroup_picture()).
                into(holder.my_groupIV);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}

