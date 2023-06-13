package com.star.wlh.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @Classname ResourceEntity
 * @Description TODO
 * @Date 2023/6/6 19:51
 * @Created by wlh
 */
@Document(collation = "resource")
@CompoundIndexes(
        @CompoundIndex(name = "tenant_code_serial", unique = true, def = "{'tenant:1','resourceCode':1,'serial':1}")
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * mongoID
     */
    private String id;
    private String name;
    private String serial;
    private String tenant;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private String resourceCode;
    private String identifiers;
    private LinkedHashMap<String, AttributeValue> attrValues = new LinkedHashMap<>();
    private boolean deleted = false;


}
