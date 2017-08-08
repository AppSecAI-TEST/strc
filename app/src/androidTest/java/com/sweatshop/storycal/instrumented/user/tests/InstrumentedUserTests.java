package com.sweatshop.storycal.instrumented.user.tests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.applicationlayer.UserServiceImpl;
import com.sweatshop.storycal.domainlayer.Album.AlbumCategory;
import com.sweatshop.storycal.domainlayer.Post.Comment;
import com.sweatshop.storycal.domainlayer.Post.PostRepository;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.domainlayer.User.UserRepository;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.PostRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.UserRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter.LocalDbAdapter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by kevin on 27/07/2017.
 */

@RunWith(AndroidJUnit4.class)
public class InstrumentedUserTests {
    @Test
    public void registerUserUnit() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        LocalDbAdapter.init(appContext);
        UserRepository userRepository = new UserRepositoryImpl();
        PostRepository postRepository = new PostRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository, postRepository);
        User user = new User("kevin monteclar", "kevin", "kevin", "kevin@gmail.com");
        String expectedUsername = "kevin";
        userService.register(user);
        user = userRepository.getUserByUsername("kevin");
        Assert.assertEquals("Register successful", expectedUsername, user.getUsername());
        System.out.println(user.getName());
    }

    @Test
    public void loginUserUnit() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        LocalDbAdapter.init(appContext);
        UserRepository userRepository = new UserRepositoryImpl();
        PostRepository postRepository = new PostRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository, postRepository);
        User user = new User();
        user.setUsername("kevin");
        user.setPassword("kevin");
        Assert.assertNotNull(userService.login(user));
    }

    @Test
    public void addPostUserUnit() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        LocalDbAdapter.init(appContext);
        UserRepository userRepository = new UserRepositoryImpl();
        PostRepository postRepository = new PostRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository, postRepository);
        User user = new User();
        user.setId(1);
        userService.addPost(user, user.createPost(user.getId(), "Hello World"));
        user = userService.getUserById(user.getId());
        Assert.assertTrue(user.getPosts().size() > 0);
    }

    @Test
    public void addCommentUnit() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        LocalDbAdapter.init(appContext);
        UserRepository userRepository = new UserRepositoryImpl();
        PostRepository postRepository = new PostRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository, postRepository);
        User user = new User();
        user.setId(1);
        user = userService.getUserById(user.getId());
        Comment comment = new Comment();
        comment.setText("This is a comment");
        userService.addComent(user, user.getPosts().get(0), comment);
        user = userService.getUserById(user.getId());
        Assert.assertTrue("failed in adding comment", user.getPosts().get(0).getNumOfComments() > 0);
    }

    @Test
    public void ChangeUserProfilePic() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        LocalDbAdapter.init(appContext);
        UserRepository userRepository = new UserRepositoryImpl();
        PostRepository postRepository = new PostRepositoryImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, postRepository);
        User user = new User();
        user.setId(1);
        user = userService.getUserById(user.getId());
        String current = user.getImageSource();
        user.setImageSource("test");
        userService.setUserProfilePicture(user);
        String expected = "test";
        user = userService.getUserById(user.getId());
        Assert.assertEquals(expected, user.getImageSource());
    }

    @Test
    public void addPhotoToAlbum() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        LocalDbAdapter.init(appContext);

        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        userRepository.addPhotoToAlbum(2, "August", "2019", "test photo");

        AlbumCategory albumCategory = userRepository.findAlbumCategory(2, "2017");

        Assert.assertNotNull(albumCategory);

    }
}

