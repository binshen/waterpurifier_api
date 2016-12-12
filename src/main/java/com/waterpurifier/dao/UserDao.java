package com.waterpurifier.dao;

import com.waterpurifier.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bin.shen on 10/12/2016.
 */

@Repository("UserDao")
public class UserDao {

    private final String collectionName = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    public User findById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public List<User> findAll() {
        return mongoTemplate.find(new Query(), User.class, collectionName);
    }


    public User login(String tel, String password) {
        return mongoTemplate.findOne(new Query(Criteria.where("tel").is(tel).where("password").is(password)), User.class, collectionName);
    }

    public User fetchUser(String id, String password) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id).where("password").is(password)), User.class, collectionName);
    }

    public User findByTel(String tel) {
        return mongoTemplate.findOne(new Query(Criteria.where("tel").is(tel)), User.class, collectionName);
    }

    public void changePassword(String id, String password) {
        mongoTemplate.upsert(new Query(Criteria.where("_id").is(id)), new Update().set("password", password), User.class, collectionName);
    }

    public void insert(User user) {
        mongoTemplate.insert(user, collectionName);
    }
}
