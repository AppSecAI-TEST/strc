package com.sweatshop.storycal.domainlayer.Post;

import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.Repository;

/**
 * Created by arTeam on 05/08/2017.
 */

public interface PostRepository extends Repository<Post> {
    Post getPostByUserId(User user);
}
