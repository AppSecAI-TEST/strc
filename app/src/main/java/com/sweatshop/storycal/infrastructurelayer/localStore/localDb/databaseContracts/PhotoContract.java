package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by arTeam on 05/08/2017.
 */

public class PhotoContract implements BaseColumns {
    public static final String TABLE_NAME = "photo";
    public static final String COL_PICTURE = "picture";
    public static final String COL_TEST_PICTURE = "test_picture";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COL_PICTURE + " BLOB," +
            COL_TEST_PICTURE + " TEXT" +
            ")";
}
