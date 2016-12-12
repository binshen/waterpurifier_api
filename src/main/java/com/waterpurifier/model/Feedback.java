package com.waterpurifier.model;

import com.waterpurifier.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Document
public class Feedback extends BaseModel {

    public String userID;

    public String feedback;

    public Double created;
}
