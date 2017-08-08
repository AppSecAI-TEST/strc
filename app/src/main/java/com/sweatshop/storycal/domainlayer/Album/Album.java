package com.sweatshop.storycal.domainlayer.Album;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevin on 25/07/2017.
 */


public class Album {
    private long id;
    private long album_category_id;
    private String month;
    private List<AlbumPicture> photos;

    public Album() {
        photos = new LinkedList<AlbumPicture>();
    }

    public Album(long id, long album_category_id, String month) {
        this.id = id;
        this.album_category_id = album_category_id;
        this.month = month;
        photos = new LinkedList<AlbumPicture>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlbum_category_id() {
        return album_category_id;
    }

    public void setAlbum_category_id(long album_category_id) {
        this.album_category_id = album_category_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    // public void addAlbumPhoto(AlbumPicture)
}
