package com.star.wlh.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Map;

/**
 * @Classname AttributeValue
 * @Description TODO
 * @Date 2023/6/6 22:04
 * @Created by wlh
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeValue {
    private static final long serialVersionUID = 1L;
    public static final AttributeValue NULL = new AttributeValue();

    public enum ValueType {
        U, O
    }

    @Field("V")
    private Serializable value;

    /**
     * 当前属性值类别
     */
    @Field("T")
    private ValueType valueType = ValueType.O;

    /**
     * 人工 维护数据值
     */
    @Field("U")
    private Serializable userValue;

    /**
     * 第三方 维护的数据
     */
    @Field("O")
    private Serializable outerValue;

    /**
     * <ul>
     * <li>true: 表示变更有冲突，需要人工调和</li>
     * <li>false: 表示没有变更冲突</li>
     * </ul>
     */
    @Field("C")
    private Boolean conflict;

    /**
     * 最后更新该属性的来源的编码
     */
    @Field("S")
    private String sourceCode;

    /**
     * 额外的属性值信息。
     */
    @Field("E")
    private Map<String, Serializable> extraValues;
}
