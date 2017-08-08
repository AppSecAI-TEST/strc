package com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.AlbumCategoryContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.AlbumContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.AlbumPictureContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.CommentContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.LoginUserContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.PhotoContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.PostContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.UserContract;

/**
 * Created by kevin on 25/07/2017.
 */

public final class LocalDbAdapter {
    private final static String DATABASE_NAME = "storycal_localdb.db";
    private final static int DATABASE_VERSION = 1;

    private static LocalDatabaseHelper localDatabaseHelper = null;

    private  LocalDbAdapter() {}

    public static void init(Context appContext) {
        if(localDatabaseHelper == null) {
            localDatabaseHelper = new LocalDatabaseHelper(appContext);
        }
    }

    public static synchronized SQLiteDatabase getDatabase() {
        return localDatabaseHelper.getWritableDatabase();
    }

    private static class LocalDatabaseHelper extends SQLiteOpenHelper {

        public LocalDatabaseHelper(Context appContext) {
            super(appContext, LocalDbAdapter.DATABASE_NAME, null, LocalDbAdapter.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(UserContract.CREATE_TABLE);
                db.execSQL(PostContract.CREATE_TABLE);
                db.execSQL(CommentContract.CREATE_TABLE);
                db.execSQL(LoginUserContract.CREATE_TABLE);
                db.execSQL(AlbumCategoryContract.CREATE_TABLE);
                db.execSQL(AlbumContract.CREATE_TABLE);
                db.execSQL(AlbumPictureContract.CREATE_TABLE);
                db.execSQL(PhotoContract.CREATE_TABLE);
            }catch (Exception e) {
                Log.e("db creation error: ", e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                onCreate(db);
            }catch (Exception e) {
                Log.e("db upgrade error: ", e.getMessage());
            }
        }
    }
}
