package com.waterpurifier.model;

import com.waterpurifier.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bin.shen on 10/12/2016.
 */

@Document
public class User extends BaseModel {

    public String tel;

    public String password;

    public String name;

    public int gender;

    public BigDecimal amount;
}
