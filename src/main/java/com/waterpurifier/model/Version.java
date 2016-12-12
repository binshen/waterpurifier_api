package com.waterpurifier.model;

import com.waterpurifier.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by bin.shen on 12/12/2016.
 */

@Document
public class Version extends BaseModel {

    public int type;

    public float version;
}
