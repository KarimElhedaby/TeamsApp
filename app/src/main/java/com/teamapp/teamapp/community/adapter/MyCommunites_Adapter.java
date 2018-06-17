package com.teamapp.teamapp.community.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.community.model.Community;

import java.util.List;

/**
 * Created by karim on 6/12/18.
 */

public class MyCommunites_Adapter extends RecyclerView.Adapter<MyCommunites_Adapter.VH> {

    private List<Community> dataList;
    private int rowLayout;
    private Context context;

    private On_myCommunity_ClickListener on_myCommunity_clickListener;

    public static class VH extends RecyclerView.ViewHolder {


        TextView my_communityDescriptionTV;
        TextView my_communitynameTV;
        ImageView my_communityIV;

        public VH(View v) {
            super(v);
            my_communityDescriptionTV = (TextView) v.findViewById(R.id.my_communityDescriptionTV);
            my_communitynameTV = (TextView) v.findViewById(R.id.my_CommunityTitleTV);
            my_communityIV = (ImageView) v.findViewById(R.id.mycommunity_IV);

        }
    }

    public MyCommunites_Adapter(List<Community> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;

        if (context instanceof MyCommunites_Adapter.On_myCommunity_ClickListener) {
            on_myCommunity_clickListener = (On_myCommunity_ClickListener) context;
        } else {
            throw new RuntimeException("Context must implement On_Community_ClickListener");
        }
    }

    @Override
    public MyCommunites_Adapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MyCommunites_Adapter.VH(view);
    }

    @Override
    public void onBindViewHolder(MyCommunites_Adapter.VH holder, final int position) {

        holder.my_communityDescriptionTV.setText(String.valueOf(dataList.get(position).getCommunity_description()));
        holder.my_communitynameTV.setText(String.valueOf(dataList.get(position).getCommunity_name()));
        Glide.with(context).load(dataList.get(position).getCommunity_picture()).
                into(holder.my_communityIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_myCommunity_clickListener.on_myCommunity_Click(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface On_myCommunity_ClickListener {
        void on_myCommunity_Click(Community community);

    }


}

