package com.star.wlh.mongo.entity;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : wlh
 * @date Date : 2022年08月05日 17:35
 */

@DefaultSerializer(CompatibleFieldSerializer.class)
@Document(collation = "books")
@CompoundIndexes({@CompoundIndex(name = "idx_name_user", sparse = true, def = "{resId:1}"),})
public class Books {
	@Id
	private ObjectId id;
	/**
	 * 书名
	 */
	private String name;
	/**
	 * 作者id
	 */
	private String authorId;
	/**
	 * 作者名称
	 */
	private String authorName;
}
