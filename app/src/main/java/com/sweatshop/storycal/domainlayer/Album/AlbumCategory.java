package com.sweatshop.storycal.domainlayer.Album;

import java.util.List;

/**
 * Created by arTeam on 05/08/2017.
 */


public class AlbumCategory {
    private long id;
    private long user_id;
    private String year;
    private List<Album> albums;

    public AlbumCategory() {}

    public AlbumCategory(long id, long user_id, String year) {
        this.id = id;
        this.user_id = user_id;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
