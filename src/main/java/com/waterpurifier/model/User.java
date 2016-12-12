package com.waterpurifier.model;

import com.waterpurifier.base.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bin.shen on 10/12/2016.
 */

public class User extends BaseModel {

    public String tel;

    public String password;

    public String name;

    public int gender;

    public BigDecimal amount;
}
