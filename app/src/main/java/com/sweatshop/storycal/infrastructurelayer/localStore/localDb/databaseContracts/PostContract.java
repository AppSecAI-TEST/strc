package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by kevin on 27/07/2017.
 */

public class PostContract implements BaseColumns {
    public final static String POST_TABLE = "posts";
    public final static String COL_USER_ID = "user_id";
    public final static String COL_POST = "post";
    public final static String COL_LIKES = "likes";
    public final static String COL_NUM_OF_COMMENTS = "num_of_comments";

    public final static String CREATE_TABLE = "CREATE TABLE " + POST_TABLE + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COL_POST + " TEXT," +
            COL_USER_ID + " INTEGER," +
            COL_NUM_OF_COMMENTS + " INTEGER," +
            COL_LIKES + " INTEGER)";


    private PostContract() {}
}
