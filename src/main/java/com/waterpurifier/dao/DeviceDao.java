package com.waterpurifier.dao;

import com.waterpurifier.base.MongoBase;
import com.waterpurifier.model.Device;
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

@Repository("DeviceDao")
public class DeviceDao implements MongoBase<Device> {

    @Resource
    private MongoTemplate mongoTemplate;

    public void insert(Device object, String collectionName) {
        mongoTemplate.insert(object, collectionName);
    }

    public void createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
    }

    public Device findOne(Map<String, Object> params, String collectionName) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(params.get("id"))), Device.class, collectionName);
    }

    public List<Device> findAll(Map<String, Object> params, String collectionName) {
        return mongoTemplate.find(new Query(), Device.class, collectionName);
    }

    public void update(Map<String, Object> params, String collectionName) {
        mongoTemplate.upsert(new Query(Criteria.where("_id").is(params.get("id"))), new Update().set("name", params.get("name")), Device.class, collectionName);
    }

    public void remove(Map<String, Object> params, String collectionName) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(params.get("id"))), Device.class, collectionName);
    }
}
