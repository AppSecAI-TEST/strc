package com.sweatshop.storycal.infrastructurelayer.localStore.Repositories;

import android.content.ContentValues;
import android.database.Cursor;

import com.sweatshop.storycal.domainlayer.Album.Album;
import com.sweatshop.storycal.domainlayer.Album.AlbumCategory;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.domainlayer.User.UserRepository;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter.LocalDbAdapter;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.AlbumCategoryContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.AlbumContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.AlbumPictureContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.PhotoContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.UserContract;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevin on 25/07/2017.
 */

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void add(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.COL_NAME, user.getName());
        values.put(UserContract.COL_USERNAME, user.getUsername());
        values.put(UserContract.COL_PASSWORD, user.getPassword());
        values.put(UserContract.COL_EMAIL, user.getEmail());
        values.put(UserContract.COL_NUM_OF_STORIES, user.getNumOfStories());
        LocalDbAdapter.getDatabase().insert(UserContract.USER_TABLE, null, values);
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void update(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.COL_NAME, user.getName());
        values.put(UserContract.COL_USERNAME, user.getUsername());
        values.put(UserContract.COL_PASSWORD, user.getPassword());
        values.put(UserContract.COL_EMAIL, user.getEmail());
        values.put(UserContract.COL_NUM_OF_STORIES, user.getNumOfStories());
        LocalDbAdapter.getDatabase().update(UserContract.USER_TABLE, values, UserContract._ID + "=?", new String[] {Long.toString(user.getId())});
    }

    @Override
    public List<User> getAll(User user) {
        return null;
    }

    @Override
    public User find(User user) {
        return null;
    }

    @Override
    public User get(long id) {
        final String[] columns = {
                UserContract._ID,
                UserContract.COL_NAME,
                UserContract.COL_USERNAME,
                UserContract.COL_PASSWORD,
                UserContract.COL_EMAIL,
                UserContract.COL_NUM_OF_STORIES,
                UserContract.COL_NUM_FOLLOWING,
                UserContract.COL_NUM_FOLLOWERS,
                UserContract.COL_IMAGE_SOURCE
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                UserContract.USER_TABLE,
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
        user.setFollowing(cursor.getInt(6));
        user.setFollowers(cursor.getInt(7));
        user.setImageSource(cursor.getString(8));

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        final String[] columns = {
                UserContract._ID,
                UserContract.COL_NAME,
                UserContract.COL_USERNAME,
                UserContract.COL_PASSWORD,
                UserContract.COL_EMAIL
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                UserContract.USER_TABLE,
                columns,
                UserContract.COL_USERNAME + "=?",
                new String[]{username},
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

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        final String[] columns = {
                UserContract._ID,
                UserContract.COL_EMAIL
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                UserContract.USER_TABLE,
                columns,
                UserContract.COL_EMAIL + "=?",
                new String[]{email},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return null;
        }
        User user = new User();

        user.setId(cursor.getLong(0));
        user.setEmail(cursor.getString(1));

        return user;
    }

    @Override
    public void addPhotoToAlbum(long userId, String month,  String year, String testphoto) {
        final AlbumCategory albumCategory = findAlbumCategory(userId, year);

        if(albumCategory == null) {
            long albumCategoryId = insertAlbumCategory(userId, year);

            if(albumCategoryId > 0) {
                long albumId = insertAlbum(albumCategoryId, month);

                if(albumId > 0) {
                    long photoId = insertPhoto(testphoto);

                    if(photoId > 0) {
                        insertAlbumPhoto(albumId, photoId);
                    }
                }
            }

        }else {
            final long albumCategoryId = albumCategory.getId();
            final Album album = findAlbum(albumCategoryId, month);

            if(album == null) {
                long albumId = insertAlbum(albumCategoryId, month);

                if(albumId > 0) {
                    long photoId = insertPhoto(testphoto);

                    if(photoId > 0) {
                        insertAlbumPhoto(albumId, photoId);
                    }
                }
            }else {
                long photoId = insertPhoto(testphoto);

                if(photoId > 0) {
                    insertAlbumPhoto(album.getId(), photoId);
                }
            }
        }
    }

    public AlbumCategory findAlbumCategory(long userId, String year) {
        final String[] columnsToQuery = {
                AlbumCategoryContract._ID
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                AlbumCategoryContract.TABLE_NAME,
                columnsToQuery,
                AlbumCategoryContract.COL_USER_ID + "=? AND " +  AlbumCategoryContract.COL_YEAR + "=?",
                new String[] {Long.toString(userId),  year},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return null;
        }

        final AlbumCategory albumCategory = new AlbumCategory();

        albumCategory.setId(cursor.getLong(0));

        return albumCategory;
    }

    public List<AlbumCategory> findAlbumCategory(long userId) {
        final String[] columnsToQuery = {
                AlbumCategoryContract._ID,
                AlbumCategoryContract.COL_USER_ID,
                AlbumCategoryContract.COL_YEAR
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                AlbumCategoryContract.TABLE_NAME,
                columnsToQuery,
                AlbumCategoryContract.COL_USER_ID + "=?",
                new String[] {Long.toString(userId)},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return null;
        }

        List<AlbumCategory> albumCategories = new LinkedList<AlbumCategory>();
        AlbumCategory albumCategory = null;

        while (cursor.moveToNext()) {
            albumCategory = new AlbumCategory();
            albumCategory.setId(cursor.getLong(0));
            albumCategory.setUser_id(cursor.getLong(1));
            albumCategory.setYear(cursor.getString(2));
            albumCategories.add(albumCategory);
        }
        
        return albumCategories;
    }

    public List<AlbumCategory> getAlbumCategory(long userId) {
        String[] columnsQueary =  {
                AlbumCategoryContract._ID,
                AlbumCategoryContract.COL_USER_ID,
                AlbumCategoryContract.COL_YEAR
        };

        Cursor cursor = LocalDbAdapter.getDatabase().query(
                AlbumCategoryContract.TABLE_NAME,
                columnsQueary,
                AlbumCategoryContract.COL_USER_ID + "=?",
                new String[] {Long.toString(userId)},
                null,
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return new LinkedList<AlbumCategory>();
        }

        List<AlbumCategory> albumCategories = new LinkedList<AlbumCategory>();

        while(cursor.moveToNext()) {
            albumCategories.add(new AlbumCategory(cursor.getLong(0), cursor.getLong(1), cursor.getString(2)));
        }

        for(AlbumCategory albumCategory : albumCategories) {
            albumCategory.setAlbums(findAlbums(albumCategory.getId()));
        }

        return albumCategories;
    }

    private Album findAlbum(long albumCategoryId, String month) {
        final String[] columnsToQuery = {
                AlbumContract._ID
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                AlbumContract.TABLE_NAME,
                columnsToQuery,
                AlbumContract.COL_ALBUM_CATEGORY_ID + "=? AND " + AlbumContract.COL_MONTH + "=?",
                new String[] {Long.toString(albumCategoryId), month},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return null;
        }

        Album album = new Album();

        album.setId(cursor.getLong(0));

        return album;
    }

    private LinkedList<Album> findAlbums(long albumCategoryId) {
        String[] columnsToQuery = {
                AlbumContract._ID,
                AlbumContract.COL_ALBUM_CATEGORY_ID,
                AlbumContract.COL_MONTH
        };

        final Cursor cursor = LocalDbAdapter.getDatabase().query(
                AlbumContract.TABLE_NAME,
                columnsToQuery,
                AlbumContract.COL_ALBUM_CATEGORY_ID + "=?",
                new String[] {Long.toString(albumCategoryId)},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return new LinkedList<Album>();
        }

        LinkedList<Album> albums = new LinkedList<Album>();
        Album album;
        Cursor cursor2 = null;

        columnsToQuery = new String[] {
                AlbumPictureContract.COL_ALBUM_ID,
                AlbumPictureContract.COL_PICTURE_ID
        };

        while(cursor.moveToNext()) {
            album = new Album(cursor.getLong(0), cursor.getLong(1), cursor.getString(1));

            cursor2 = LocalDbAdapter.getDatabase().query(
                    AlbumPictureContract.TABLE_NAME,
                    columnsToQuery,
                    AlbumPictureContract.COL_PICTURE_ID + "=?",
                    new String[] {Long.toString(album.getId())},
                    null,
                    null,
                    null,
                    null
            );

            if(cursor2.moveToFirst()) {
                while(cursor2.moveToNext()) {

                }
            }

            albums.add(album);
        }

        return albums;
    }



    private long insertAlbumCategory(long userId, String year) {
        final ContentValues values = new ContentValues();

        values.put(AlbumCategoryContract.COL_USER_ID, userId);
        values.put(AlbumCategoryContract.COL_YEAR, year);

        return LocalDbAdapter.getDatabase().insert(AlbumCategoryContract.TABLE_NAME, null, values);
    }

    private long insertAlbum(long albumCategoryId, String month) {
        final ContentValues values = new ContentValues();

        values.put(AlbumContract.COL_ALBUM_CATEGORY_ID, albumCategoryId);
        values.put(AlbumContract.COL_MONTH, month);

        return LocalDbAdapter.getDatabase().insert(AlbumContract.TABLE_NAME, null, values);
    }

    private long insertPhoto(String testPhoto) {
        final ContentValues values = new ContentValues();

        values.put(PhotoContract.COL_TEST_PICTURE, testPhoto);

        return LocalDbAdapter.getDatabase().insert(PhotoContract.TABLE_NAME, null, values);
    }

    private long insertAlbumPhoto(long albumId, long photoId) {
        final ContentValues values = new ContentValues();

        values.put(AlbumPictureContract.COL_ALBUM_ID, albumId);
        values.put(AlbumPictureContract.COL_PICTURE_ID, photoId);

        return LocalDbAdapter.getDatabase().insert(AlbumPictureContract.TABLE_NAME, null, values);
    }
}
