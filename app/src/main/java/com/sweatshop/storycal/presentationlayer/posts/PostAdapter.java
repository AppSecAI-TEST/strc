package com.sweatshop.storycal.presentationlayer.posts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.domainlayer.Post.Post;

import java.util.ArrayList;

/**
 * Created by arTeam on 31/07/2017.
 */

public class PostAdapter extends
        RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private ArrayList<Post> postArrayList;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameText;
        public TextView nameText;
        public ImageView postImage;

        public MyViewHolder(View view) {
            super(view);
            usernameText = (TextView) view.findViewById(R.id.usernamePost);
            nameText = (TextView) view.findViewById(R.id.namePost);
            postImage = (ImageView) view.findViewById(R.id.post_image);
        }
    }

    public PostAdapter(ArrayList<Post> postArrayList) { this.postArrayList = postArrayList; }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post p = postArrayList.get(position);
        holder.usernameText.setText(p.getUser().getUsername());
        holder.nameText.setText(String.valueOf(p.getUser().getName()));
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.activity_row_post,parent, false);
        return new MyViewHolder(v);
    }
}
