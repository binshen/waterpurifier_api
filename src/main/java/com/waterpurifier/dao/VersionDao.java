package com.waterpurifier.dao;

import com.waterpurifier.model.Version;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Repository("VersionDao")
public class VersionDao {

    private final String collectionName = "versions";

    @Resource
    private MongoTemplate mongoTemplate;

    public Version findByType(int type) {
        return mongoTemplate.findOne(new Query(Criteria.where("type").is(type)), Version.class, collectionName);
    }
}
