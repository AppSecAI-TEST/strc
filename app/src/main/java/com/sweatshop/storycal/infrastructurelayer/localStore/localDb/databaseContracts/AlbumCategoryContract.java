package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by arTeam on 05/08/2017.
 */


public class AlbumCategoryContract implements BaseColumns {
    public final static String TABLE_NAME = "AlbumCategory";
    public final static String COL_USER_ID = "user_id";
    public final static String COL_YEAR = "year";
    public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COL_USER_ID + " INTEGER," +
            COL_YEAR + " TEXT," +
            "FOREIGN KEY (" + COL_USER_ID + ") REFERENCES "  +   UserContract.USER_TABLE + " ( " + UserContract._ID + "))";
}
