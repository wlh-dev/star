package com.star.wlh.mongo.base;

import java.util.Arrays;
import java.util.List;

/**
 * 常量定义。
 */
public final class Constants {
	private Constants() {}

	/**
	 * 集合名
	 */
	public static final String COL_MODEL_VERSION = "modelVersion";
	public static final String COL_RES_ATTRIBUTE = "resAttribute";
	public static final String COL_RES_CLASS = "resClass";
	public static final String COL_RES_OBJECT = "resObject";
	public static final String COL_RES_KEYS = "resUniqueKey";
	public static final String COL_RES_INTERFACE = "resInterface";
	public static final String COL_RES_HISTORY = "resHistory";
	public static final String COL_RES_RELATION_META = "resRelationMeta";
	public static final String COL_RES_RELATION = "resRelationship";
	public static final String COL_RES_RELATION_TYPE = "resRelationType";
	public static final String COL_RES_LAYER = "resClassLayer";
	
	/**
	 * 字段名
	 */
	public static final String FIELD_NAME = "name";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_BUILTIN = "builtin";
	public static final String FIELD_DEFAULT = "default";
	public static final String FIELD_RES_OWNERS = "resOwners";
	public static final String FIELD_TENANT_ID = "tenantId";
	public static final String FIELD_CLASS_CODE = "classCode";
	public static final String FIELD_RESID = "resId";
	public static final String FIELD_SORT_INDEX = "sortIndex";
	public static final String FIELD_DELETED = "deleted";
	public static final String FIELD_ATTR_CONFS = "attrConfs";
	public static final String FIELD_KEY_IDENTIFIERS = "keyIdentifiers";
	public static final String FIELD_KEY_IDENTIFIERS_TXT = "keyIdentifiersText";
	public static final String FIELD_OBJECT_VERSION = "objectVersion";
	public static final String FIELD_ATTR_VALUES = "attrValues";
	public static final String FIELD_CREATE_TIME = "createTime";
	public static final String FIELD_UPDATE_TIME = "updateTime";
	public static final String FIELD_CHANGE = "change";
	public static final String FIELD_SOURCES = "sources";
	public static final String FIELD_IS_ACTIVE = "is_active";
	public static final String FIELD_MONGO_ID = "_id";
	public static final String FLOW_NO = "flowNo";
	public static final String FLOW_STATUS = "status";

	/**
	 * 关系节点类型
	 */
	public static final String NODE_TYPE_CLASS = "CLASS";
	public static final String NODE_TYPE_INF = "INTERFACE";
	
	/**
	 * 数据类型
	 */
	public static final String DT_STRING = "singleRowText";
	public static final String DT_IP_LIST = "ipList";
	public static final String DT_USER = "user";
	public static final String DT_DEPT = "dept";
	public static final String DT_REF = "reference";
	public static final String DT_ZONE_SEL = "zoneSelection";
	
	
	/**
	 * 其他
	 */
	public static final String RES_KEYS = "resKeys";
	public static final String DB_ID = "_id";
	
	// 唯一标识符
	
	/**
	 * 多个关键属性值之间的分隔符
	 */
	public static final String KEY_DELIMITER = "@#@";

	/**
	 * 关键属性编码和属性值之间的分隔符
	 */
	public static final String KEY_SEPARATOR = ":";
	
	/**
	 * 多组关键属性之间的分隔符
	 */
	public static final String MULTI_KEY_SEPARATOR = "&&";

	public static final String KEY_LINE = "-";

	/**
	 * 关系定义
	 */
	public static final String RELATION_TYPE_CONNECT = "Links";

	/**
	 * 常量值定义
	 */
	public static final String VAL_TENANT_ID = "e10adc3949ba59abbe56e057f20f88dd";
	public static final List<String> DICT_TYPE = Arrays.asList("listSel", "multiSel", "singleSel");


}
