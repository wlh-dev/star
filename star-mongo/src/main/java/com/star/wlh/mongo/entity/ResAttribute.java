package com.star.wlh.mongo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Data
@Document(collection = "resAttribute")
public class ResAttribute {
    @Id
    private Object id;

    private String tenantId;

    private String code;

    private String name;

    private Boolean builtin;

    private String type;

    private HashMap<String,Object> params;

    private Boolean required;

    private List<String> resOwners;

    private Boolean deleted;

    private Boolean arrayValue;

    private Object categoryMask;

    private Boolean readonly;
}
