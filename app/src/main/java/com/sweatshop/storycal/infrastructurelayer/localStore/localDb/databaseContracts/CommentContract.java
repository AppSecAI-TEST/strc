package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts;

import android.provider.BaseColumns;

/**
 * Created by kevin on 27/07/2017.
 */

public class CommentContract implements BaseColumns {
    public final static String COMMENT_TABLE = "comments";
    public final static String COL_COMMENT = "comment";
    public final static String COL_USER_ID = "userId";
    public final static String COL_POST_ID = "post_id";

    public final static String CREATE_TABLE = "CREATE TABLE " + COMMENT_TABLE +
            " (" + _ID + " INTEGER PRIMARY KEY, " +
            COL_USER_ID + " INTEGER," +
            COL_POST_ID + " INTEGER," +
            COL_COMMENT + " TEXT)";

    private CommentContract() {}
}
