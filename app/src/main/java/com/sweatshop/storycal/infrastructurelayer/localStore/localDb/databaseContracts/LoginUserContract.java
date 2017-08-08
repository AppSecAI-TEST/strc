package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by arTeam on 05/08/2017.
 */

public final class LoginUserContract implements BaseColumns {
    public final static String LOGIN_USER_TABLE = "login_user";
    public final static String COL_NAME = "name";
    public final static String COL_USERNAME = "username";
    public final static String COL_PASSWORD = "password";
    public final static String COL_EMAIL = "email";
    public final static String COL_NUM_OF_STORIES = "num_of_stories";
    public final static String COL_NUM_OF_FOLLOWERS = "num_of_followers";
    public final static String COL_NUM_OF_FOLLOWING = "num_of_following";
    public final static String COL_USER = "login_user";
    public final static String CREATE_TABLE =
            "CREATE TABLE " + LOGIN_USER_TABLE + " (" +
                    _ID + " INTEGER," +
                    COL_NAME + " TEXT," +
                    COL_USERNAME + " TEXT," +
                    COL_PASSWORD + " TEXT," +
                    COL_NUM_OF_STORIES + " INTEGER," +
                    COL_EMAIL + " TEXT," +
                    COL_USER + " INTEGER)";

    private  LoginUserContract() {}

}

