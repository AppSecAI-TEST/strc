package com.sweatshop.storycal.domainlayer.Post;

import com.sweatshop.storycal.domainlayer.User.User;

/**
 * Created by kevin on 27/07/2017.
 */


public class Comment {
    private long id;
    private String text;
    private long userId;
    private long postId;
    private User user;

    public Comment() {}

    public Comment(long id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
