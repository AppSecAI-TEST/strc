package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by kevin on 25/07/2017.
 */

public class UserContract implements BaseColumns {
    public final static String USER_TABLE = "user";
    public final static String COL_NAME = "name";
    public final static String COL_USERNAME = "username";
    public final static String COL_PASSWORD = "password";
    public final static String COL_EMAIL = "email";
    public final static String COL_NUM_OF_STORIES = "num_of_stories";
    public final static String COL_NUM_FOLLOWERS = "num_followers";
    public final static String COL_NUM_FOLLOWING = "num_following";
    public final static String COL_IMAGE_SOURCE = "image_source";

    public final static String CREATE_TABLE =
            "CREATE TABLE " + USER_TABLE + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COL_NAME + " TEXT," +
                    COL_USERNAME + " TEXT," +
                    COL_PASSWORD + " TEXT," +
                    COL_NUM_OF_STORIES + " INTEGER," +
                    COL_NUM_FOLLOWERS + " INTEGER," +
                    COL_NUM_FOLLOWING + " INTEGER," +
                    COL_IMAGE_SOURCE + " TEXT," +
                    COL_EMAIL + " TEXT)";

    private UserContract() {}
}
