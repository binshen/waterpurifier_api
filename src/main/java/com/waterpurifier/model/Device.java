package com.waterpurifier.model;

import com.waterpurifier.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by bin.shen on 10/12/2016.
 */

@Document
public class Device extends BaseModel {

    public String mac;

    public Double update_at;
}
