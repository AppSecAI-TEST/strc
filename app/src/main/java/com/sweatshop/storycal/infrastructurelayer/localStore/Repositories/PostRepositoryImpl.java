package com.sweatshop.storycal.infrastructurelayer.localStore.Repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sweatshop.storycal.domainlayer.Post.Comment;
import com.sweatshop.storycal.domainlayer.Post.Post;
import com.sweatshop.storycal.domainlayer.Post.PostRepository;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter.LocalDbAdapter;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.CommentContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.PostContract;

import java.util.List;

/**
 * Created by arTeam on 05/08/2017.
 */

public class PostRepositoryImpl implements PostRepository {

    @Override
    public void add(Post post) {
        ContentValues values = new ContentValues();
        values.put(PostContract.COL_USER_ID, post.getUser().getId());
        values.put(PostContract.COL_POST, post.getPost());
        values.put(PostContract.COL_LIKES, post.getNumberOfLikes());
        LocalDbAdapter.getDatabase().insert(PostContract.POST_TABLE, null, values);
    }

    @Override
    public void remove(Post post) {

    }

    @Override
    public void update(Post post) {
        ContentValues values = new ContentValues();
        values.put(PostContract.COL_POST, post.getPost());
        values.put(PostContract.COL_LIKES, post.getNumberOfLikes());
        values.put(PostContract.COL_NUM_OF_COMMENTS, post.getNumOfComments());
        LocalDbAdapter.getDatabase().update(PostContract.POST_TABLE, values, PostContract._ID + "=?", new String[]{Long.toString(post.getId())});
    }

    @Override
    public List<Post> getAll(Post post) {

        return null;
    }

    @Override
    public Post find(Post post) {
        return null;
    }

    @Override
    public Post get(long id) {
        return null;
    }

    @Override
    public Post getPostByUserId(User user) {

        SQLiteDatabase db = LocalDbAdapter.getDatabase();

        String[] columns = {
                PostContract._ID,
                PostContract.COL_POST,
                PostContract.COL_USER_ID,
                PostContract.COL_NUM_OF_COMMENTS,
                PostContract.COL_LIKES
        };

        Cursor cursor = db.query(
                PostContract.POST_TABLE,
                columns,
                PostContract.COL_USER_ID + "=?",
                new String[]{Long.toString(user.getId())},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst()) {
            return null;
        }
        Post post = new Post();

        post.setId(cursor.getLong(0));
        post.setPost(cursor.getString(1));
        post.setUserId(cursor.getLong(2));
        post.setNumOfComments(cursor.getInt(3));
        post.setNumberOfLikes(cursor.getInt(4));

        String[] commentColumns = {
                CommentContract._ID,
                CommentContract.COL_POST_ID,
                CommentContract.COL_COMMENT,
                CommentContract.COL_USER_ID
        };

        int numOfComments = post.getNumOfComments();

        if(numOfComments > 0) {
            Comment comment = null;
            for (int ctr = 0; ctr < numOfComments; ctr++) {

                cursor = db.query(
                        CommentContract.COMMENT_TABLE,
                        commentColumns,
                        CommentContract.COL_POST_ID + "=?",
                        new String[]{Long.toString(post.getId())},
                        null,
                        null,
                        null
                );

                if(!cursor.moveToFirst())  {
                    continue;
                }

                comment = new Comment(cursor.getLong(0), cursor.getString(1), user);
                post.addComment(comment);
            }
        }

        return post;
    }
}

