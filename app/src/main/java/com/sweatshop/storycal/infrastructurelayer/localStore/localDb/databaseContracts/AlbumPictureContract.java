package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

/**
 * Created by arTeam on 05/08/2017.
 */

public class AlbumPictureContract  {
    public final static String TABLE_NAME = "AlbumPicture";
    public final static String COL_ALBUM_ID = "album_id";
    public final static String COL_PICTURE_ID = "picture_id";
    public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            COL_ALBUM_ID + " INTEGER, " +
            COL_PICTURE_ID + " INTEGER, " +
            "FOREIGN KEY (" + COL_ALBUM_ID + ") REFERENCES " + AlbumContract.TABLE_NAME + "(" + AlbumContract._ID + ") " +
            ", " +
            "FOREIGN KEY (" + COL_PICTURE_ID + ") REFERENCES " + PhotoContract.TABLE_NAME + "(" + PhotoContract._ID + ")" +
            ");";

}
