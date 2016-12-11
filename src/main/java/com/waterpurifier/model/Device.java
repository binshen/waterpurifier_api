package com.waterpurifier.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by bin.shen on 10/12/2016.
 */

@Document
public class Device implements Serializable {

    public String _id;

    public String mac;

    public Double update_at;
}
