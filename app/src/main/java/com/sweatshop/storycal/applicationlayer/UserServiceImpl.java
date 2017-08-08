package com.sweatshop.storycal.applicationlayer;

import android.content.ContentValues;
import android.util.Log;

import com.sweatshop.storycal.domainlayer.Post.Comment;
import com.sweatshop.storycal.domainlayer.Post.Post;
import com.sweatshop.storycal.domainlayer.Post.PostRepository;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.domainlayer.User.UserRepository;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter.LocalDbAdapter;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.CommentContract;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseContracts.UserContract;

/**
 * Created by kevin on 27/07/2017.
 */

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void register(User user) {
        try {
            User temp = userRepository.getUserByUsername(user.getUsername());
            if(temp == null) {
                userRepository.add(user);
                Log.i("User: ", "register successful, eyay");
            }
        }catch (Exception e) {
            Log.e("Register failed: ", e.getMessage());
        }
    }

    @Override
    public User login(User user) {
        try {
            User temp = userRepository.getUserByUsername(user.getUsername());
            if(temp == null) {
                return null;
            }
            if(temp.getPassword().equals(user.getPassword())) {
                return temp;
            }
        }catch (Exception e) {
            Log.e("Login failed: ", e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return userRepository.getUserByEmail(email);
        }catch (Exception e) {
            Log.e("Login failed: ", e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return userRepository.getUserByUsername(username);
        }catch (Exception e) {
            Log.e("username failed: ", e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(long id) {
        try {
            User user = userRepository.get(id);
            int numOfStories = user.getNumOfStories();
            for(int i = 0; i < numOfStories; i++) {
                user.addPost(postRepository.getPostByUserId(user));
            }
            return user;
        }catch (Exception e ) {
            Log.e("Get user: ", "Exception " + e.getMessage());
        }
        return null;
    }

    @Override
    public void addPost(User user, Post post) {
        try {
            post.setUser(user);
            this.postRepository.add(post);
            user.setNumOfStories(user.getNumOfStories() + 1);
            userRepository.update(user);
        }catch (Exception e) {
            Log.e("Post exception: ", e.getMessage());
        }
    }

    @Override
    public void addComent(User user, Post post, Comment comment) {
        try {
            comment.setUserId(user.getId());
            comment.setPostId(post.getId());

            ContentValues values = new ContentValues();
            values.put(CommentContract.COL_POST_ID, post.getId());
            values.put(CommentContract.COL_USER_ID, user.getId());
            values.put(CommentContract.COL_COMMENT, comment.getText());

            LocalDbAdapter.getDatabase().insert(CommentContract.COMMENT_TABLE, null, values);
            post.setNumOfComments(post.getNumOfComments() + 1);
            postRepository.update(post);
        }catch(Exception e) {
            Log.e("Comment failed: ", e.getMessage());
        }
    }

    public void setUserProfilePicture(User user) {
        try {
            ContentValues values = new ContentValues();
            values.put(UserContract.COL_IMAGE_SOURCE, user.getImageSource());
            LocalDbAdapter.getDatabase().update(UserContract.USER_TABLE, values, UserContract._ID + "=?", new String[]{Long.toString(user.getId())});
        }catch (Exception e) {
            Log.e("User Img src: ",  e.getMessage());
        }
    }
}
