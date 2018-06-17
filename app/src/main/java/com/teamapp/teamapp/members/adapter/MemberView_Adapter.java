package com.teamapp.teamapp.members.adapter;

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
import com.teamapp.teamapp.user.model.User;

import java.util.List;

/**
 * Created by karim on 6/12/18.
 */

public class MemberView_Adapter extends RecyclerView.Adapter<MemberView_Adapter.VH> {
    private List<User> dataList;
    private int rowLayout;
    private Context context;


    public static class VH extends RecyclerView.ViewHolder {


        TextView my_memberEMailTV;
        TextView my_memberNameTV;
        ImageView my_MemberIV;

        public VH(View v) {
            super(v);
            my_memberEMailTV = (TextView) v.findViewById(R.id.my_membersEmailTV);
            my_memberNameTV = (TextView) v.findViewById(R.id.my_membersNameTV);
            my_MemberIV = (ImageView) v.findViewById(R.id.my_members_IV);

        }
    }

    public MemberView_Adapter(List<User> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public MemberView_Adapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MemberView_Adapter.VH(view);
    }

    @Override
    public void onBindViewHolder(MemberView_Adapter.VH holder, final int position) {
        holder.my_memberEMailTV.setText(String.valueOf(dataList.get(position).getEmail()));
        holder.my_memberNameTV.setText(String.valueOf(dataList.get(position).getName()));
        Glide.with(context).load(dataList.get(position).getPicture()).
                into(holder.my_MemberIV);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

