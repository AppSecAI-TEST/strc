package com.sweatshop.storycal.presentationlayer.main_year;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.domainlayer.Album.Album;
import com.sweatshop.storycal.presentationlayer.main_month.MainMonthActivity;

import java.util.List;

/**
 * Created by arTeam on 05/08/2017.
 */

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumViewHolder> {
    private Context context;
    private List<Album> albumList;

    public AlbumRecyclerAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.album_year_view, parent, false);
        return new AlbumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.setData(albumList.get(position));
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    protected class AlbumViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView month;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private Album album;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            month = (TextView) itemView.findViewById(R.id.month);
            imageView = (ImageView) itemView.findViewById(R.id.albumImage);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.albumRelLayout);
        }

        public void setData(Album album) {
            this.album = album;
            month.setText(album.getMonth());
            imageView.setImageResource(R.drawable.pic18);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MainMonthActivity.class);
            intent.putExtra("album_id", album.getId());
            intent.putExtra("album_category_id", album.getAlbum_category_id());
            context.startActivity(intent);
        }
    }
}