package com.teamapp.teamapp.members.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otaliastudios.autocomplete.RecyclerViewPresenter;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.members.ui.AddMember_Activity;
import com.teamapp.teamapp.user.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserPresenter extends RecyclerViewPresenter<User> {

    protected Adapter adapter;
    static int userSelected_id;

    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    protected PopupDimensions getPopupDimensions() {
        PopupDimensions dims = new PopupDimensions();
        dims.width = 600;
        dims.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return dims;
    }

    @Override
    protected RecyclerView.Adapter instantiateAdapter() {
        adapter = new Adapter();
        return adapter;
    }

    @Override
    protected void onQuery(@Nullable CharSequence query) {
        List<User> all = AddMember_Activity.USERS2;
        if (TextUtils.isEmpty(query)) {
            adapter.setData(all);
        } else {
            query = query.toString().toLowerCase();
            List<User> list = new ArrayList<>();
            for (User u : all) {
                if (u.getName().toLowerCase().contains(query) || u.getEmail()
                        .toLowerCase().contains(query)) {
                    list.add(u);
                }
            }
            adapter.setData(list);
            Log.e("UserPresenter", "found " + list.size() + " users for query " + query);
        }
        adapter.notifyDataSetChanged();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private List<User> data;

        public class Holder extends RecyclerView.ViewHolder {
            private View root;
            private TextView fullname;
            private TextView email;

            public Holder(View itemView) {
                super(itemView);
                root = itemView;
                fullname = ((TextView) itemView.findViewById(R.id.fullname));
                email = ((TextView) itemView.findViewById(R.id.username));
            }
        }

        public void setData(List<User> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return (isEmpty()) ? 1 : data.size();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.user_mention_row, parent, false));
        }

        private boolean isEmpty() {
            return data == null || data.isEmpty();
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            if (isEmpty()) {
                holder.fullname.setText("No user_mention_row here!");
                holder.root.setOnClickListener(null);
                return;
            }
            final User user = data.get(position);
            holder.fullname.setText(user.getName());
            holder.email.setText(user.getEmail());
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchClick(user);
                    userSelected_id = user.getUser_id();
                }
            });
        }
    }
}
