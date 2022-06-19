package com.star.common.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * @author jiangzk
 * @date: 2021年8月5日 下午4:30:24
 */
public class MongoOperUtil {

	public static void loopCollectionData(MongoCollection<Document> dataCollection, Document filter, Function<Document, Void> function) {
		final int pageSize = 1000;
		AtomicReference<ObjectId> lastObjectId = new AtomicReference<>(); // 上次查询的最后一条记录ID，用于定位下次查询的起始记录
		Document projection = null;
		while (true) {
			AtomicInteger count = new AtomicInteger();
			if (lastObjectId.get() != null) {
				filter.append("_id", new Document("$gt", lastObjectId.get())); // 跳过已经查询过的，配合
																																				// ID
																																				// 排序一起使用
			}
			Document sort = new Document("_id", 1); // 升序排序
			dataCollection.find(filter).projection(projection).sort(sort).limit(pageSize).forEach(doc -> {
				ObjectId objectId = doc.getObjectId("_id");
				function.apply(doc);
				lastObjectId.set(objectId);
				count.incrementAndGet();
			});
			// 不满足一页，结束循环
			if (count.get() < pageSize) {
				break;
			}
		}
	}

	public static Document filter(String tenantId, String classCode) {
		return new Document("tenantId", tenantId).append("classCode", classCode);
	}

	public static Document update(Map<String, Object> update) {
		if (update == null || update.isEmpty())
			return null;
		Document upDoc = new Document();
		for(Map.Entry<String, Object> map : update.entrySet()){
			upDoc.append(map.getKey(), map.getValue());
		}
		return new Document("$set", upDoc);
	}
}
