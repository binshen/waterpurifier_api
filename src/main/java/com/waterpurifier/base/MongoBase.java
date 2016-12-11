package com.waterpurifier.base;

import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by bin.shen on 10/12/2016.
 */

public interface MongoBase<T> {

    public void insert(T object,String collectionName);

    public T findOne(Map<String,Object> params, String collectionName);

    public List<T> findAll(Map<String,Object> params, String collectionName);

    public void update(Map<String,Object> params,String collectionName);

    public void createCollection(String collectionName);

    public void remove(Map<String,Object> params,String collectionName);
}