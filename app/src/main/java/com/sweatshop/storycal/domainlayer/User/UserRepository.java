package com.sweatshop.storycal.domainlayer.User;

import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.Repository;

/**
 * Created by kevin on 25/07/2017.
 */

public interface UserRepository extends Repository<User> {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    void addPhotoToAlbum(long userId, String month, String year, String testphoto);
}

