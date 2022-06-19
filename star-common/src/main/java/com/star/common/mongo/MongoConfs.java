package com.star.common.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.connection.SocketSettings;
import com.star.common.config.ConfigService;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 数据库配置。
 */
public class MongoConfs {

	/**
	 * 获取mongodbClient的options
	 * 
	 * @return MongoClientOptions
	 */
	public static MongoClientSettings getMongoOptions(boolean wirte, boolean isCmdb) {
		SocketSettings socket = SocketSettings.builder()
				.connectTimeout(ConfigService.getInstance().getMongoSocketTimeout(), TimeUnit.MILLISECONDS).build();
		// 非写入操作，首先使用从节点
		if (isCmdb) {
			return MongoClientSettings.builder().credential(getMongoCredential()).applyToClusterSettings(builder -> builder.hosts(getHosts()))
					.applyToSocketSettings(builder -> builder.applySettings(socket)).readPreference(ReadPreference.primary()).build();
		} else {
			if (!wirte) {
				return MongoClientSettings.builder().credential(getPacificMongoCredential())
						.applyToClusterSettings(builder -> builder.hosts(getPacificHosts())).applyToSocketSettings(builder -> builder.applySettings(socket))
						.readPreference(ReadPreference.secondary()).build();
			}
			return MongoClientSettings.builder().credential(getPacificMongoCredential())
					.applyToClusterSettings(builder -> builder.hosts(getPacificHosts())).applyToSocketSettings(builder -> builder.applySettings(socket))
					.readPreference(ReadPreference.primary()).build();
		}
	}

	/**
	 * 获取MongoCredential
	 * 
	 * @return MongoCredential
	 */
	public static MongoCredential getMongoCredential() {
		return MongoCredential.createCredential(ConfigService.getInstance().getMongoUserName(), ConfigService.getInstance().getMongoAuthDb(),
				ConfigService.getInstance().getMongoPwd().toCharArray());
	}

	/**
	 * 获取pacific库MongoCredential
	 * 
	 * @return MongoCredential
	 */
	public static MongoCredential getPacificMongoCredential() {
		return MongoCredential.createCredential(ConfigService.getInstance().getPacificMongoUserName(),
				ConfigService.getInstance().getMongoAuthDb(), ConfigService.getInstance().getPacificMongoPwd().toCharArray());
	}

	/**
	 * 获取mongodb的hosts
	 * 
	 * @return hosts
	 */
	public static List<ServerAddress> getHosts() {
		List<ServerAddress> hosts = new ArrayList<>();
		for (String host : ConfigService.getInstance().getMongoHosts().split(",")) {
			hosts.add(new ServerAddress(host));
		}
		return hosts;
	}
	
	/**
	 * 获取pacific的mongodb的hosts
	 * 
	 * @return hosts
	 */
	public static List<ServerAddress> getPacificHosts() {
		List<ServerAddress> hosts = new ArrayList<>();
		for (String host : ConfigService.getInstance().getPacificMongoHosts().split(",")) {
			hosts.add(new ServerAddress(host));
		}
		return hosts;
	}

	/**
	 * 获取mongodb中对应数据库中的表集合
	 * 
	 * @param mongoClient mongoClient
	 * @param dbName 数据库名称
	 * @param tableName 表名称
	 * @return 表集合
	 */
	public static MongoCollection<Document> getCollecton(MongoClient mongoClient, String dbName, String tableName) {
		return mongoClient.getDatabase(dbName).getCollection(tableName);
	}
}
