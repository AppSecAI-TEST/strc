package com.sweatshop.storycal.domainlayer.User;


import com.sweatshop.storycal.domainlayer.Entity;
import com.sweatshop.storycal.domainlayer.Post.Post;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevin on 25/07/2017.
 */

public class User extends Entity {
    private String name;
    private String username;
    private String password;
    private String email;
    private String imageSource;
    private int numOfStories;
    private int followers;
    private int following;
    private List<Post> posts;

    public User() {
        posts = new LinkedList<Post>();
    }

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumOfStories() {
        return numOfStories;
    }

    public void setNumOfStories(int numOfStories) {
        this.numOfStories = numOfStories;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public Post createPost(long userId, String post) {
        return new Post(userId, post);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
