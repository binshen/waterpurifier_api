package com.waterpurifier.dao;

import com.waterpurifier.base.MongoBase;
import com.waterpurifier.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by bin.shen on 10/12/2016.
 */

@Repository("UserDao")
public class UserDao implements MongoBase<User> {

    @Resource
    private MongoTemplate mongoTemplate;

    public void insert(User object, String collectionName) {
        mongoTemplate.insert(object, collectionName);
    }

    public void createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
    }

    public User findOne(Map<String, Object> params, String collectionName) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(params.get("id"))), User.class, collectionName);
    }

    public List<User> findAll(Map<String, Object> params, String collectionName) {
        return mongoTemplate.find(new Query(), User.class, collectionName);
    }

    public void update(Map<String, Object> params, String collectionName) {
        mongoTemplate.upsert(new Query(Criteria.where("_id").is(params.get("id"))), new Update().set("name", params.get("name")), User.class, collectionName);
    }

    public void remove(Map<String, Object> params, String collectionName) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(params.get("id"))), User.class, collectionName);
    }

    ///////////////////////////////////////////////////
    public User login(String tel, String password) {
        return mongoTemplate.findOne(new Query(Criteria.where("tel").is(tel).where("password").is(password)), User.class, "users");
    }
}
