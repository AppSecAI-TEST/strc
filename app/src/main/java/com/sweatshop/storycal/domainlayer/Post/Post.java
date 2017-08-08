package com.sweatshop.storycal.domainlayer.Post;

import com.sweatshop.storycal.domainlayer.Entity;
import com.sweatshop.storycal.domainlayer.Picture.Picture;
import com.sweatshop.storycal.domainlayer.User.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevin on 25/07/2017.
 */

public class Post extends Entity {
    private long userId;
    private User user;
    private List<Comment> comments;
    private String post;
    private Picture picture;
    private int numberOfLikes;
    private int numOfComments;

    public Post() {
        this.comments = new LinkedList<Comment>();
    }

    public Post(long userId, String post) {
        this.userId = userId;
        this.post = post;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumOfComments() {
        return numOfComments;
    }

    public void setNumOfComments(int numOfComments) {
        this.numOfComments = numOfComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", user=" + user +
                ", comments=" + comments +
                ", post='" + post + '\'' +
                ", picture=" + picture +
                ", numberOfLikes=" + numberOfLikes +
                ", numOfComments=" + numOfComments +
                '}';
    }
}
