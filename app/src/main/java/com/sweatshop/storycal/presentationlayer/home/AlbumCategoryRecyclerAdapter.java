package com.sweatshop.storycal.presentationlayer.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.domainlayer.Album.AlbumCategory;
import com.sweatshop.storycal.presentationlayer.main_year.MainYearActivity;

import java.util.List;

/**
 * Created by arTeam on 05/08/2017.
 */

public class AlbumCategoryRecyclerAdapter extends RecyclerView.Adapter<AlbumCategoryRecyclerAdapter.ViewHolder> {
    private List<AlbumCategory> albumCategories;
    private Context context;

    public AlbumCategoryRecyclerAdapter(Context context, List albumCategories) {
        this.albumCategories = albumCategories;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_category_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(albumCategories.get(position));
    }


    @Override
    public int getItemCount() {
        return albumCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView year;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private long id;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            year = (TextView) itemView.findViewById(R.id.year);
            imageView = (ImageView) itemView.findViewById(R.id.albumCategoryImage);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.albumCategoryRelLayout);
        }

        public void setData(AlbumCategory albumCategory) {
            year.setText(albumCategory.getYear());
            imageView.setImageResource(R.drawable.pic16);
            id = albumCategory.getId();
        }

        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(context, MainYearActivity.class);
            intent.putExtra("album_category_id", id);
            context.startActivity(intent);
        }
    }


}

