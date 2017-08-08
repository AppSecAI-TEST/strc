package com.sweatshop.storycal.domainlayer.Picture;


import android.graphics.Bitmap;

import com.sweatshop.storycal.domainlayer.Entity;

import java.util.Date;

/**
 * Created by kevin on 25/07/2017.
 */

public final class Picture extends Entity {
    private String imageSource;
    private Bitmap image;
    private Date dateOfPictureTaken;

    public Picture() {}

    public Picture(String imageSource, Date dateOfPictureTaken) {
        this.imageSource = imageSource;
        this.dateOfPictureTaken = dateOfPictureTaken;
    }

    public final String getImageSource() {
        return imageSource;
    }

    public final void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public final Date getDateOfPictureTaken() {
        return dateOfPictureTaken;
    }

    public final void setDateOfPictureTaken(Date dateOfPictureTaken) {
        this.dateOfPictureTaken = dateOfPictureTaken;
    }

    public String toString() {
        return imageSource;
    }
}
