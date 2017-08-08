package com.sweatshop.storycal.domainlayer;

/**
 * Created by kevin on 25/07/2017.
 */

public abstract class Entity {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "Id=" + id +
                '}';
    }
}
