package com.sweatshop.storycal.applicationlayer;

import android.content.ContentValues;
import android.database.Cursor;

import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter.LocalDbAdapter;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.LoginUserContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.UserContract;

/**
 * Created by arTeam on 05/08/2017.
 */

public class LocalStoreService {
    public void addLoginUserToLocalStore(User user) {
        ContentValues values = new ContentValues();
        values.put(LoginUserContract._ID, user.getId());
        values.put(LoginUserContract.COL_NAME, user.getName());
        values.put(LoginUserContract.COL_USERNAME, user.getUsername());
        values.put(LoginUserContract.COL_PASSWORD, user.getPassword());
        values.put(LoginUserContract.COL_EMAIL, user.getEmail());
        values.put(LoginUserContract.COL_NUM_OF_STORIES, user.getNumOfStories());
        values.put(LoginUserContract.COL_USER, 1);
        LocalDbAdapter.getDatabase().insert(LoginUserContract.LOGIN_USER_TABLE, null, values);
    }

    public User getUserFromLocalStore(long id) {

        final String[] columns = {
                UserContract._ID,
                UserContract.COL_NAME,
                UserContract.COL_USERNAME,
                UserContract.COL_PASSWORD,
                UserContract.COL_EMAIL,
                UserContract.COL_NUM_OF_STORIES
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                LoginUserContract.LOGIN_USER_TABLE,
                columns,
                UserContract._ID + "=?",
                new String[]{Long.toString(id)},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return null;
        }

        User user = new User();

        user.setId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setUsername(cursor.getString(2));
        user.setPassword(cursor.getString(3));
        user.setEmail(cursor.getString(4));
        user.setNumOfStories(cursor.getInt(5));

        return user;
    }

}
