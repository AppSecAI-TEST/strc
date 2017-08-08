package com.sweatshop.storycal.presentationlayer.import_photo_gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.sweatshop.storycal.R;

import java.util.LinkedList;

/**
 * Created by arTeam on 03/08/2017.
 */


public class ImageAdapter extends BaseAdapter {

    private Context context;
    public Integer[] images1 ={
            R.drawable.pic1, R.drawable.pic3, R.drawable.pic4,
            R.drawable.pic5, R.drawable.pic12, R.drawable.pic14,
            R.drawable.pic21, R.drawable.pic13, R.drawable.pic3,
            R.drawable.pic20, R.drawable.pic14, R.drawable.pic3,
            R.drawable.pic13, R.drawable.pic15, R.drawable.pic6    };
    public LinkedList<Integer> images;

    public ImageAdapter(Context c){
        context = c;
        images = new LinkedList<>();
        images.add(R.drawable.pic1);
        images.add(R.drawable.pic13);
        images.add(R.drawable.pic3);
        images.add(R.drawable.pic4);
        images.add(R.drawable.pic5);
        images.add(R.drawable.pic6);
        images.add(R.drawable.pic7);
        images.add(R.drawable.pic8);
        images.add(R.drawable.pic9);
        images.add(R.drawable.pic10);
        images.add(R.drawable.pic11);
        images.add(R.drawable.pic12);
    }
    @Override
    public int getCount() {
        return images1.length;
    }

    @Override
    public Object getItem(int position) {
        return images1[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images1[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(240,240));
        return imageView;
    }


}
