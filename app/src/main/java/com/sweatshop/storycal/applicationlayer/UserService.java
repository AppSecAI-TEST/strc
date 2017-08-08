package com.sweatshop.storycal.applicationlayer;

import com.sweatshop.storycal.domainlayer.Post.Comment;
import com.sweatshop.storycal.domainlayer.Post.Post;
import com.sweatshop.storycal.domainlayer.User.User;

/**
 * Created by kevin on 25/07/2017.
 */

public interface UserService  {
    void register(User user);
    User login(User user);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User getUserById(long id);
    void addPost(User user, Post post);
    void addComent(User user, Post post, Comment comment);
}
