package com.waterpurifier.dao;

import com.waterpurifier.model.Auth;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Repository("AuthDao")
public class AuthDao {

    private final String collectionName = "auths";

    @Resource
    private MongoTemplate mongoTemplate;

    public Auth findByTel(String tel) {
        return mongoTemplate.findOne(new Query(Criteria.where("tel").is(tel)), Auth.class, collectionName);
    }

    public Auth findByTelAndCode(String tel, String code) {
        return mongoTemplate.findOne(new Query(Criteria.where("tel").is(tel).and("code").is(code)), Auth.class, collectionName);
    }

    public void update(String id, String code, double created) {
        mongoTemplate.upsert(new Query(Criteria.where("_id").is(id)), new Update().set("code", code).set("created", created), Auth.class, collectionName);
    }

    public void insert(Auth auth) {
        mongoTemplate.insert(auth, collectionName);
    }
}
