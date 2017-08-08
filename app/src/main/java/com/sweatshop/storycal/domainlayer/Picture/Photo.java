package com.sweatshop.storycal.domainlayer.Picture;

import android.graphics.Bitmap;

/**
 * Created by arTeam on 05/08/2017.
 */

public class Photo {
    private long id;
    private Bitmap image;
    private String text;

    public Photo() {}

    public Photo(long id, Bitmap image, String text) {
        this.id = id;
        this.image = image;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
