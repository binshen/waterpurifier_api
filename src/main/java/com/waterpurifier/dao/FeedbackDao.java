package com.waterpurifier.dao;

import com.waterpurifier.model.Feedback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Repository("FeedbackDao")
public class FeedbackDao {

    private final String collectionName = "feedbacks";

    @Resource
    private MongoTemplate mongoTemplate;

    public void insert(Feedback feedback) {
        mongoTemplate.insert(feedback, collectionName);
    }
}
