package com.sweatshop.storycal.presentationlayer.fb_friends2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.domainlayer.User.User;

import java.util.ArrayList;

/**
 * Created by arTeam on 29/07/2017.
 */

public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private ArrayList<User> userArrayList;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameText;
        public TextView nameText;

        public MyViewHolder(View view) {
            super(view);
            usernameText = (TextView) view.findViewById(R.id.usernameText);
            nameText = (TextView) view.findViewById(R.id.nameText);
        }
    }

    public UserAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User u = userArrayList.get(position);
        holder.usernameText.setText(u.getUsername());
        holder.nameText.setText(String.valueOf(u.getName()));
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_row,parent, false);
        return new MyViewHolder(v);
    }
}