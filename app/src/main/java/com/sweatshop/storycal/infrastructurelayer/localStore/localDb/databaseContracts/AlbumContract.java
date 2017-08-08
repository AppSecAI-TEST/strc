package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by kevin on 27/07/2017.
 */

public class AlbumContract implements BaseColumns {
    public static final String TABLE_NAME = "Album";
    public static final String COL_ALBUM_CATEGORY_ID = "album_category_id";
    public static final String COL_MONTH = "month";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COL_ALBUM_CATEGORY_ID + " INTEGER," +
            COL_MONTH + " TEXT," +
            "FOREIGN KEY (" + COL_ALBUM_CATEGORY_ID + ") REFERENCES " + AlbumCategoryContract.TABLE_NAME + " (" + AlbumCategoryContract._ID  + ")" +
            ");";
}
