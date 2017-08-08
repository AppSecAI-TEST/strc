package com.sweatshop.storycal.domainlayer.Album;

import com.sweatshop.storycal.domainlayer.Picture.Photo;

/**
 * Created by arTeam on 05/08/2017.
 */

public class AlbumPicture {
    private long album_id;
    private long picture_id;
    private Album album;
    private Photo photo;

    public AlbumPicture() {}

    public AlbumPicture(long album_id, long picture_id, Album album, Photo photo) {
        this.album_id = album_id;
        this.picture_id = picture_id;
        this.album = album;
        this.photo = photo;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public long getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(long picture_id) {
        this.picture_id = picture_id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
