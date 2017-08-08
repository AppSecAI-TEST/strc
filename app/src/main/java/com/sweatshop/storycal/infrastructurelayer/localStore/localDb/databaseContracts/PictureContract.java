package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by kevin on 27/07/2017.
 */

public class PictureContract implements BaseColumns {
    public final static String PICTURE_TABLE = "pictures";
    public final static String COL_IMAGE_SOURCE = "imageSource";
    public final static String COL_DATE_TAKEN = "date_taken";

    public final static String CREATE_TABLE = "CREATE TABLE " + PICTURE_TABLE + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COL_IMAGE_SOURCE + " TEXT," +
            COL_DATE_TAKEN + " DATETIME)";

    private PictureContract() {}
}
