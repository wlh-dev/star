package com.star.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * CMDB产品中common.properties和cmdb.properties中配置注入类
 * 
 * @author wlh
 * @date: 2020年5月15日 上午11:28:32
 */
@Component
public class ConfigValue {

	// --------------------------------- sync.propertie
	// --------mongodb相关配置
	/**
	 * mongodb的主机地址
	 */
	@Value("${cmdb.mongodb.hosts:10.1.61.100:27017}")
	private String mongoHosts;
	
	/**
	 * pacific的主机地址
	 */
	@Value("${pacific.mongodb.hosts:10.1.61.100:27017}")
	private String pacificMongoHosts;
	
	/**
	 * mongodb的credentials
	 */
	@Value("${cmdb.mongodb.credentials:mongo:MongoDB_863*^#@admin}")
	private String mongoCredentials;

	/**
	 * mongodb的用户名
	 */
	@Value("${mongodb.username:mongo}")
	private String mongoUserName;

	/**
	 * mongodb的密码
	 */
	@Value("${mongodb.password:MongoDB_863*^#}")
	private String mongoPwd;
	
	/**
	 * pacific库mongodb的用户名
	 */
	@Value("${pacific.mongodb.username:mongo}")
	private String pacificMongoUserName;
	
	/**
	 * pacific库mongodb的密码
	 */
	@Value("${pacific.mongodb.password:MongoDB_863*^#}")
	private String pacificMongoPwd;

	/**
	 * mongodb的AuthenticationDatabase
	 */
	@Value("${crab.mongodb.authenticationDb:admin}")
	private String mongoAuthDb;

	/**
	 * mongodb中crab的数据库
	 */
	@Value("${crab.mongodb.db.main:cmdb}")
	private String mongoCmdbDb;

	/**
	 * mongodb中crab的数据库
	 */
	@Value("${pacific.mongodb.db.main:pacific}")
	private String mongoStoreResDb;

	/**
	 * mongodb的crab的附件数据库
	 */
	@Value("${crab.mongodb.db.files:cmdbFiles}")
	private String mongoDbFile;

	/**
	 * mongodb的connectionsPerHost
	 */
	@Value("${crab.mongodb.connectionsPerHost:60}")
	private int mongoConnectionsPerHost;

	/**
	 * mongodb的threadsAllowedToBlockForConnectionMultiplier
	 */
	@Value("${crab.mongodb.threadsAllowedToBlockForConnectionMultiplier:4}")
	private int mongoThreadsAllowedToBlockForConnectionMultiplier;

	/**
	 * mongodb的maxWaitTime
	 */
	@Value("${crab.mongodb.maxWaitTime:15000}")
	private int mongoMaxWaitTime;

	/**
	 * mongodb的connectTimeout
	 */
	@Value("${crab.mongodb.connectTimeout:15000}")
	private int mongoConnectTimeout;

	/**
	 * mongodb的autoConnectRetry
	 */
	@Value("${crab.mongodb.autoConnectRetry:true}")
	private boolean mongoAutoConnectRetry;

	/**
	 * mongodb的socketKeepAlive
	 */
	@Value("${crab.mongodb.socketKeepAlive:true}")
	private boolean mongoSocketKeepAlive;

	/**
	 * mongodb的socketTimeout
	 */
	@Value("${crab.mongodb.socketTimeout:15000}")
	private int mongoSocketTimeout;

	/**
	 * mongodb的slaveOk
	 */
	@Value("${crab.mongodb.slaveOk:true}")
	private boolean mongoSlaveOk;
	
	/**
	 * 需要阶段top10值的字段
	 */
	@Value("${crab.stat.top.value.attr.codes:name,ip,ci_owner,ci_owner_dept,app_lifecycle_state,common_lifecycle_state,run_status}")
	private String calcTopTenValueAttrCodes;

	public String getMongoHosts() {
		return mongoHosts;
	}

	public void setMongoHosts(String mongoHosts) {
		this.mongoHosts = mongoHosts;
	}

	public String getMongoCredentials() {
		return mongoCredentials;
	}
	
	public String getPacificMongoHosts() {
		return pacificMongoHosts;
	}

	public void setPacificMongoHosts(String pacificMongoHosts) {
		this.pacificMongoHosts = pacificMongoHosts;
	}

	public void setMongoCredentials(String mongoCredentials) {
		this.mongoCredentials = mongoCredentials;
	}

	public String getMongoUserName() {
		return mongoUserName;
	}

	public void setMongoUserName(String mongoUserName) {
		this.mongoUserName = mongoUserName;
	}

	public String getMongoPwd() {
		return mongoPwd;
	}

	public void setMongoPwd(String mongoPwd) {
		this.mongoPwd = mongoPwd;
	}

	public String getMongoAuthDb() {
		return mongoAuthDb;
	}

	public void setMongoAuthDb(String mongoAuthDb) {
		this.mongoAuthDb = mongoAuthDb;
	}

	public String getMongoCmdbDb() {
		return mongoCmdbDb;
	}

	public void setMongoCmdbDb(String mongoCmdbDb) {
		this.mongoCmdbDb = mongoCmdbDb;
	}

	public String getMongoStoreResDb() {
		return mongoStoreResDb;
	}

	public void setMongoStoreResDb(String mongoStoreResDb) {
		this.mongoStoreResDb = mongoStoreResDb;
	}

	public String getMongoDbFile() {
		return mongoDbFile;
	}

	public void setMongoDbFile(String mongoDbFile) {
		this.mongoDbFile = mongoDbFile;
	}

	public int getMongoConnectionsPerHost() {
		return mongoConnectionsPerHost;
	}

	public void setMongoConnectionsPerHost(int mongoConnectionsPerHost) {
		this.mongoConnectionsPerHost = mongoConnectionsPerHost;
	}

	public int getMongoThreadsAllowedToBlockForConnectionMultiplier() {
		return mongoThreadsAllowedToBlockForConnectionMultiplier;
	}

	public void setMongoThreadsAllowedToBlockForConnectionMultiplier(int mongoThreadsAllowedToBlockForConnectionMultiplier) {
		this.mongoThreadsAllowedToBlockForConnectionMultiplier = mongoThreadsAllowedToBlockForConnectionMultiplier;
	}

	public int getMongoMaxWaitTime() {
		return mongoMaxWaitTime;
	}

	public void setMongoMaxWaitTime(int mongoMaxWaitTime) {
		this.mongoMaxWaitTime = mongoMaxWaitTime;
	}

	public int getMongoConnectTimeout() {
		return mongoConnectTimeout;
	}

	public void setMongoConnectTimeout(int mongoConnectTimeout) {
		this.mongoConnectTimeout = mongoConnectTimeout;
	}

	public boolean isMongoAutoConnectRetry() {
		return mongoAutoConnectRetry;
	}

	public void setMongoAutoConnectRetry(boolean mongoAutoConnectRetry) {
		this.mongoAutoConnectRetry = mongoAutoConnectRetry;
	}

	public boolean isMongoSocketKeepAlive() {
		return mongoSocketKeepAlive;
	}

	public void setMongoSocketKeepAlive(boolean mongoSocketKeepAlive) {
		this.mongoSocketKeepAlive = mongoSocketKeepAlive;
	}

	public int getMongoSocketTimeout() {
		return mongoSocketTimeout;
	}

	public void setMongoSocketTimeout(int mongoSocketTimeout) {
		this.mongoSocketTimeout = mongoSocketTimeout;
	}

	public boolean isMongoSlaveOk() {
		return mongoSlaveOk;
	}

	public void setMongoSlaveOk(boolean mongoSlaveOk) {
		this.mongoSlaveOk = mongoSlaveOk;
	}

	public String getPacificMongoUserName() {
		return pacificMongoUserName;
	}

	public void setPacificMongoUserName(String pacificMongoUserName) {
		this.pacificMongoUserName = pacificMongoUserName;
	}

	public String getPacificMongoPwd() {
		return pacificMongoPwd;
	}

	public void setPacificMongoPwd(String pacificMongoPwd) {
		this.pacificMongoPwd = pacificMongoPwd;
	}

	public String getCalcTopTenValueAttrCodes() {
		return calcTopTenValueAttrCodes;
	}

	public void setCalcTopTenValueAttrCodes(String calcTopTenValueAttrCodes) {
		this.calcTopTenValueAttrCodes = calcTopTenValueAttrCodes;
	}

}
