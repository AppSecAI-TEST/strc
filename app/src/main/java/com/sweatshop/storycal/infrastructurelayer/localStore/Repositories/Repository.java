package com.sweatshop.storycal.infrastructurelayer.localStore.Repositories;

import java.util.List;

/**
 * Created by kevin on 25/07/2017.
 */

public interface Repository<T> {
    public void add(T t);
    public void remove(T t);
    public void update(T t);
    public List<T> getAll(T t);
    public T find(T t);
    public T get(long id);
}
