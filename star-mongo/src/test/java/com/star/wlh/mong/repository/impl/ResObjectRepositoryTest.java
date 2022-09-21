package com.star.wlh.mong.repository.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.star.wlh.MongoBaseTest;
import com.star.wlh.mongo.base.Constants;
import com.star.wlh.mongo.base.ConstantsFields;
import com.star.wlh.mongo.entity.Item;
import com.star.wlh.mongo.entity.ModelDescEntity;
import com.star.wlh.mongo.entity.PrimarySwitchEntity;
import com.star.wlh.mongo.entity.RequestItem;
import com.star.wlh.mongo.entity.ResAttribute;
import com.star.wlh.mongo.entity.ResObject;
import com.star.wlh.mongo.entity.ResRelationShip;
import com.star.wlh.mongo.entity.ResSubObject;
import com.star.wlh.mongo.entity.SourceType;
import com.star.wlh.mongo.entity.Tag;
import com.star.wlh.mongo.repository.ResObjectRepository;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 16:44
 */

public class ResObjectRepositoryTest extends MongoBaseTest {
	private static final Logger logger = LoggerFactory.getLogger(ResObjectRepositoryTest.class);
	private static final Map<String, String> classCodeMap = new HashMap<>();
	private String physicalStatusCode;

	static {
		classCodeMap.put("VpnGateway", "VpnGateway");
		classCodeMap.put("VideoConferenceControlUnit", "VideoConferenceControlUnit");
		classCodeMap.put("ContactCenterAccessGateway", "ContactCenterAccessGateway");
		classCodeMap.put("InternetBehaviorGateway", "InternetBehaviorGateway");
		classCodeMap.put("VideoConferenceTerminal", "VideoConferenceTerminal");
		classCodeMap.put("NetworkController", "NetworkController");
		classCodeMap.put("MediaGateway", "MediaGateway");
		classCodeMap.put("ProxyServer", "ProxyServer");
		classCodeMap.put("NetworkTrafficCollectionAndDiversionTool", "NetworkTrafficCollectionAndDiversionTool");
		classCodeMap.put("WirelessAccessController", "WirelessAccessController");
		classCodeMap.put("LoadBalancer", "LB");
		classCodeMap.put("IpsecVpnGateway", "IpsecVpnGateway");
		classCodeMap.put("NetworkPerformanceTestAndDiagnosticTool", "NetworkPerformanceTestAndDiagnosticTool");
		classCodeMap.put("WirelessAccessPoint", "WirelessAccessPoint");
		classCodeMap.put("IpPhone", "IpPhone");
		classCodeMap.put("Router", "10202");
		classCodeMap.put("SwitchAndRouter", "SwitchAndRouter");
		classCodeMap.put("DWDM", "DWDM");
		classCodeMap.put("Switch", "10201");
		classCodeMap.put("Firewall", "10203");
	}

	@Autowired private ResObjectRepository resObjectRepository;

	private static final Map<String, List<String>> SOURCE_MAP = new HashMap<>();

	@Test public void ListTest() {
		Aggregation aggregation = Aggregation.newAggregation(
						Aggregation.group("createSource").first("createSource").as("createSource").first("classCode").as("classCode"));
		AggregationResults<SourceType> aggregate = resObjectRepository.aggregate(aggregation);
		logger.info("aggregate:{}", aggregate);
		List<SourceType> mappedResults = aggregate.getMappedResults();
		for (SourceType mappedResult : mappedResults) {
			String classCode = mappedResult.getClassCode();
			String createSource = mappedResult.getCreateSource();
			SOURCE_MAP.putIfAbsent(classCode, new ArrayList<>());
			List<String> list = SOURCE_MAP.get(classCode);
			list.add(createSource);
		}
		logger.info("SOURCE_LIST:{}", SOURCE_MAP);
	}

	@Test public void sdnClassCodeTest() {
		StringJoiner joiner = new StringJoiner(",");
		for (String key : classCodeMap.keySet()) {
			joiner.add(key);
		}
		logger.info("keys:{}", joiner);
	}

	private static void initSwitchResObject(String source) {
		String url = "http://10.1.60.114/store/openapi/v2/resources/batch_save?apikey=5f18514fe82f11ea90dc005056981a0d&source=" + source;
		JSONArray resObjectList = new JSONArray();
		for (int i = 10; i < 20; i++) {
			JSONObject entries = new JSONObject();
			entries.set("serial_number", "test_serial_number_" + i);
			entries.set("classCode", "Switch");
			entries.set("is_active", "Y");
			entries.set("desc", "test_desc_" + i);
			entries.set("gateway", "test_gateway_" + i);
			entries.set("net_sys_code", "test_net_sys_code_" + i);
			resObjectList.add(entries);

		}
		logger.info("url=:{}", url);
		logger.info("resObjectList:{}", resObjectList);
		String body = HttpRequest.post(url).body(resObjectList.toJSONString(0)).execute().body();
		logger.info("body:{}", body);
	}

	@Test public void modelDescEntityTest() {
		Map<String, String> stringStringMap = queryModelDescByType();
		logger.info("stringStringMap:{}", stringStringMap);
	}

	private static Map<String, String> queryModelDescByType() {
		Map<String, String> map;
		List<ModelDescEntity> entitys = getModelDescEntityList();
		map = entitys.stream().collect(Collectors.toMap(ModelDescEntity::getCode, ModelDescEntity::getDesc));
		return map;
	}

	private static List<ModelDescEntity> getModelDescEntityList() {
		List<ModelDescEntity> modelDescEntities = new ArrayList<>();
		ModelDescEntity modelDescEntity = new ModelDescEntity();
		modelDescEntity.setCode("code");
		modelDescEntity.setType("types");
		modelDescEntity.setId("ddss");
		modelDescEntity.setTenantId("tenantId");
		modelDescEntity.setModelId("modelId");
		modelDescEntities.add(modelDescEntity);
		return modelDescEntities;

	}

	/**
	 * 添加交换机的配置子项信息
	 * {
	 * "_id" : ObjectId("6322e61f4d75df7dd5b2a11a"),
	 * "tenantId" : "e10adc3949ba59abbe56e057f20f88dd",
	 * "subClassCode" : "UserInfo",
	 * "ciId" : "6306d7a04d75df478b6bd095",
	 * "parentId" : "6322e61f4d75df7dd5b2a118",
	 * "identifier" : "UserInfo##User##Linux",
	 * "attrValues" : {
	 * "user" : "qaaaaa"
	 * },
	 * "objectVersion" : 1,
	 * "onlyOne" : false
	 * },
	 */
	@Test public void addSwitchSubObjectTest() {
		//查询所有的带已采集标签的交换机
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("classCode").is("Switch"), Criteria.where("tags").elemMatch(Criteria.where("K").is("已采集")));
		Query query = new Query(criteria);
		List<ResObject> resObjects = resObjectRepository.find(query, ResObject.class, "resObject");
		logger.info("查询数据量是:{}", resObjects.size());
		Random random = new Random();
		for (ResObject resObject : resObjects) {
			String mongoId = resObject.getId().toString();
			ResSubObject resSubObject = new ResSubObject();
			resSubObject.setTenantId("e10adc3949ba59abbe56e057f20f88dd");
			resSubObject.setSubClassCode("DeviceManInfo");
			resSubObject.setCiId(mongoId);
			resSubObject.setParentId(mongoId);
			resSubObject.setIdentifier("DeviceManInfo##Switch##Switch");
			resSubObject.setObjectVersion(1);
			resSubObject.setOnlyOne(false);
			JSONObject attrValues = new JSONObject();

			attrValues.set("chassis_id", "0500_" + random.nextInt(100));
			resSubObject.setAttrValues(attrValues);
			ResSubObject subObject = resObjectRepository.save(resSubObject, "resSubObject");
			String parentId = subObject.getId().toString();

			logger.info("id:{}", parentId);
			//创建设备管理机箱项
			logger.info("开始创建设备管理机箱项");
			// 随机获取一个不和当前的mongoId 相同的父类id
			ResObject parentResObject = getParentMongoId(resObjects, random, mongoId);
			ResObject.AttrValues parentAttrValues = parentResObject.getAttrValues();
			// 创建对象 DeviceManChassisItems
			ResSubObject item = new ResSubObject();
			item.setObjectVersion(1);
			item.setOnlyOne(false);
			item.setTenantId("e10adc3949ba59abbe56e057f20f88dd");
			item.setIdentifier("DeviceManChassisItems##DeviceManInfo##Switch");
			item.setParentId(parentId);
			item.setCiId(mongoId);
			item.setSubClassCode("DeviceManChassisItems");
			JSONObject itemAttrValues = new JSONObject();
			itemAttrValues.set("slot_id", random.nextInt());
			itemAttrValues.set("device_serial_number", parentAttrValues.get("serial_number").getV());
			itemAttrValues.set("device_name", parentAttrValues.get("name").getV());
			item.setAttrValues(itemAttrValues);

			ResSubObject saveItem = resObjectRepository.save(item, "resSubObject");
			logger.info("Item:{}", saveItem);

		}

	}

	private ResObject getParentMongoId(List<ResObject> collect, Random random, String mongoId) {
		ResObject resObject = collect.get(random.nextInt(100));
		if (mongoId.equals(resObject.getId().toString())) {
			return getParentMongoId(collect, random, mongoId);
		}
		return resObject;
	}

	@Test public void createSwitchTest() {
		List<Object> dataCenterList = new ArrayList<>();
		JSONArray postBody = new JSONArray();

		dataCenterList.add(0, "NH");
		dataCenterList.add(1, "BJ");
		String base = "20220922_";
		for (int i = 0; i < 100; i++) {
			String serialNumber = base + i;
			JSONObject entries = new JSONObject();
			entries.set("serial_number", serialNumber);
			entries.set("classCode", "Switch");
			entries.set("name", "交换机设备_" + serialNumber);
			entries.set("responsibility_center", "运营中心");
			entries.set("is_active", "Y");
			entries.set("physical_status", "08");
			int res = i % 2;
			entries.set("rel_datacenter", dataCenterList.get(res));
			postBody.add(entries);
		}
		String addTagsUrl = "http://10.1.60.114/store/openapi/v2/resources/tags/add?apikey=5f18514fe82f11ea90dc005056981a0d";
		String batchSaveUrl = "http://10.1.60.114/store/openapi/v2/resources/batch_save?apikey=5f18514fe82f11ea90dc005056981a0d&source=ADS";
		String body = HttpRequest.post(batchSaveUrl).body(postBody.toJSONString(0)).execute().body();
		RequestItem requestItem = JSONUtil.toBean(body, RequestItem.class);
		List<Item> items = requestItem.getItems();
		for (Item item : items) {
			String mongoId = item.getItem();
			String response = HttpRequest.post(addTagsUrl).header("res_owner", "CMDB").header("resource_id", mongoId)
							.header("content-type", "application/json").body("已采集").execute().body();
			logger.info("response:{}", response);
		}

	}

	@Test public void process() {
		Map<String, ResRelationShip> relationLbMap = new HashMap<>();
		// 查询数据库中上次执行的结果, 每次计算任务将“虚设备”标签全量清除
		logger.info("清除所有虚设备标签");
		removeVirtualDeviceTags();
		Map<String, String> virtualSwitchList = new HashMap<>();
		Map<String, PrimarySwitchEntity> primarySwitchList = new HashMap<>();
		setPhysicalStatusCode();
		String midId = "000000000000000000000000";
		int pageSize = 2000;

		logger.info("开始执行数据处理");
		// 查询总行设备
		while (true) {
			Query query = initResObjectQuery(midId, pageSize);
			List<ResObject> resObject = resObjectRepository.find(query, ResObject.class, "resObject");
			midId = resObject.get(resObject.size() - 1).getId().toString();
			filterResObject(resObject, virtualSwitchList, primarySwitchList);
			if (resObject.size() != pageSize) {
				break;
			}
		}
		logger.info("完成主设备和虚设备的分组 疑似主设备数据量为:{},疑似虚设备数据量为:{} ", primarySwitchList.size(), virtualSwitchList.size());
		// 对主设备进行配置子项的查询
		queryResSubObject(primarySwitchList);

		// 对疑似虚设备和疑似主设备进行处理
		for (String virtualMongoId : virtualSwitchList.keySet()) {
			String virtualSerialNumber = virtualSwitchList.get(virtualMongoId);
			Collection<PrimarySwitchEntity> values = primarySwitchList.values();
			List<PrimarySwitchEntity> res = values.stream().filter(it -> it.getDeviceSerialNumber().equals(virtualSerialNumber))
							.collect(Collectors.toList());
			if (!res.isEmpty()) {
				logger.info("查找到虚设备在主设备子项device_serial_number中, 虚设备id:{},虚设备序列号:{},主设备信息:{}", virtualMongoId, virtualSerialNumber, res);
				//创建关系 主设备包含虚设备
				for (PrimarySwitchEntity re : res) {
					ResRelationShip resRelationShip = new ResRelationShip();
					// 主设备id
					String primaryMongoId = re.getMongoId();
					resRelationShip.setSrcId(primaryMongoId);
					resRelationShip.setDstId(virtualMongoId);
					resRelationShip.setDstClzCode("Switch");
					resRelationShip.setSrcClzCode("Switch");
					resRelationShip.setTypeCode("Contains");
					resRelationShip.setTenantId("e10adc3949ba59abbe56e057f20f88dd");
					relationLbMap.put(primaryMongoId + Constants.KEY_LINE + virtualMongoId + Constants.KEY_LINE + "Contains", resRelationShip);
				}
			}
		}
		logger.info("开始保存交换机虚设备和主设备之间包含关系");
		logger.info("创建关系请求体:{}", relationLbMap);

	}

	private void removeVirtualDeviceTags() {
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where(ConstantsFields.CLASS_CODE.getKey()).is("Switch"),
						Criteria.where(ConstantsFields.TENANT_ID.getKey()).is("e10adc3949ba59abbe56e057f20f88dd"));
		Query query = new Query(criteria);
		Update update = new Update();
		update.pull("tags", new Tag("虚设备", false));
		resObjectRepository.updateMulti(query, update, "resObject");
	}

	private Query updateSwitchQuery(List<String> virtual) {
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where(ConstantsFields.TENANT_ID.getKey()).is("e10adc3949ba59abbe56e057f20f88dd"),
						Criteria.where(ConstantsFields.CLASS_CODE.getKey()).is("Switch"),
						Criteria.where(ConstantsFields.MONGO_ID.getKey()).in(virtual));
		return new Query(criteria);
	}

	private void queryResSubObject(Map<String, PrimarySwitchEntity> primarySwitchList) {
		List<String> queryList = new ArrayList<>();
		for (String mongoId : primarySwitchList.keySet()) {
			queryList.add(mongoId);
			if (queryList.size() >= 2000) {
				initResSubObjectQuery(queryList, primarySwitchList);
				queryList.clear();
			}
		}
		initResSubObjectQuery(queryList, primarySwitchList);
		queryList.clear();
	}

	private void initResSubObjectQuery(List<String> queryList, Map<String, PrimarySwitchEntity> primarySwitchList) {
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where(ConstantsFields.TENANT_ID.getKey()).is("e10adc3949ba59abbe56e057f20f88dd"),
						// 配置子项的编码为设备管理机箱项:DeviceManChassisItems
						Criteria.where("subClassCode").is("DeviceManChassisItems"), Criteria.where("ciId").in(queryList),
						Criteria.where("identifier").is("DeviceManChassisItems##DeviceManInfo##Switch"));
		Query query = new Query(criteria);
		query.fields().include("attrValues").include("ciId");
		List<ResSubObject> resSubObjects = resObjectRepository.find(query, ResSubObject.class, "resSubObject");
		for (ResSubObject resSubObject : resSubObjects) {
			String ciId = resSubObject.getCiId();
			JSONObject attrValues = resSubObject.getAttrValues();
			Object deviceSerialNumber = attrValues.get("device_serial_number");
			if (deviceSerialNumber != null) {
				PrimarySwitchEntity primarySwitchEntity = primarySwitchList.get(ciId);
				primarySwitchEntity.setDeviceSerialNumber(deviceSerialNumber.toString());
			}
		}

	}

	/**
	 * 查找物理状态的字典Map
	 */
	@Test public void setPhysicalStatusCode() {
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("code").is("physical_status"),
						Criteria.where(ConstantsFields.TENANT_ID.getKey()).is("e10adc3949ba59abbe56e057f20f88dd"));
		Query query = new Query(criteria);
		ResAttribute resAttribute = resObjectRepository.findOne(query, ResAttribute.class, "resAttribute");
		if (resAttribute == null) {
			throw new RuntimeException("字段查询失败,字段中物理状态 编码:physical_status,租户id TenantId:" + "e10adc3949ba59abbe56e057f20f88dd");
		}
		HashMap<String, Object> params = resAttribute.getParams();
		Object options = params.get("options");
		JSONArray optionsArray = JSONUtil.parseArray(options);
		for (Object object : optionsArray) {
			JSONObject optionObject = JSONUtil.parseObj(object);
			String code = optionObject.getStr("code");
			String name = optionObject.getStr("name");
			if ("已加电".equals(name)) {
				physicalStatusCode = code;
			}
		}
		// 如果为空  这只一个默认值
		if (physicalStatusCode == null) {
			physicalStatusCode = "08";
		}
	}

	/**
	 * @param resObject         mongo查询每页结果
	 * @param virtualSwitchList
	 * @param primarySwitchList
	 */
	private void filterResObject(List<ResObject> resObject, Map<String, String> virtualSwitchList,
					Map<String, PrimarySwitchEntity> primarySwitchList) {
		for (ResObject object : resObject) {
			String mongoId = object.getId().toString();

			ResObject.AttrValues attrValues = object.getAttrValues();
			String serialNumber = attrValues.get("serial_number").getV().toString();

			// 过滤出总行交换机
			//责任中心去掉为空的
			if (attrValues.get("responsibility_center") == null || attrValues.get("responsibility_center").getV() == null || "".equals(
							attrValues.get("responsibility_center").getV())) {
				logger.info("过滤责任中心为空的数据：{}", object);
				continue;
			} else {
				// 责任中心去掉包含“测试”和“CS”字样的
				String responsibilityCenter = attrValues.get("responsibility_center").getV().toString();
				if (responsibilityCenter.contains("测试") || responsibilityCenter.contains("CS")) {
					logger.info("过滤掉责任中心包含测试和CS字样数据:{}", object);
					continue;
				}
			}
			// 所在可用区去掉“测试中心”和“分行园区”
			if (attrValues.get("rel_datacenter") != null && attrValues.get("rel_datacenter").getV() != null) {
				Object relDatacenter = attrValues.get("rel_datacenter").getV();
				JSONObject relDatacenterObject = JSONUtil.parseObj(relDatacenter);
				String name = relDatacenterObject.getStr("name");
				if ("测试中心".equals(name) || "分行园区".equals(name)) {
					logger.info("所在可用区去掉“测试中心”和“分行园区”:{}", object);
					continue;
				}
			}
			// 过滤物理状态 非 已加电的设备
			if (attrValues.get("physical_status") == null || attrValues.get("physical_status").getV() == null || !physicalStatusCode.equals(
							attrValues.get("physical_status").getV())) {
				logger.info("过滤物理状态 非 已加电的设备”:{}", object);
				continue;
			}

			// 虚设备ip为空
			if (attrValues.get("ip") == null || attrValues.get("ip").getV() == null && "".equals(attrValues.get("ip").getV())) {
				// 疑似虚设备
				virtualSwitchList.put(mongoId, serialNumber);
			}

			// 主设备 标签上有已采集的
			List<Tag> tags = object.getTags();
			for (Tag tag : tags) {
				String key = tag.getKey();
				if ("已采集".equals(key)) {
					PrimarySwitchEntity primarySwitchEntity = new PrimarySwitchEntity();
					primarySwitchEntity.setMongoId(mongoId);
					primarySwitchEntity.setSerialNumber(serialNumber);
					primarySwitchList.put(mongoId, primarySwitchEntity);
				}
			}
		}
	}

	/**
	 * 创建ResObject查询条件
	 *
	 * @param midId    起始mongoId
	 * @param pageSize 分页大小
	 * @return Query
	 */
	private Query initResObjectQuery(String midId, int pageSize) {
		Criteria criteria = new Criteria().andOperator(Criteria.where("_id").gt(new ObjectId(midId)),
						Criteria.where(ConstantsFields.CLASS_CODE.getKey()).is("Switch"),
						Criteria.where(ConstantsFields.ATTR_VALUES_IS_ACTIVE_V.getKey()).is("Y"));
		Query query = new Query(criteria);
		// 根据ID进行排序
		query.with(Sort.by(Sort.Direction.ASC, "_id"));
		query.fields().include("attrValues.serial_number").include("attrValues.responsibility_center").include("attrValues.physical_status")
						.include("attrValues.rel_datacenter").include("attrValues.ip").include("tags");
		query.skip(0).limit(pageSize);
		return query;
	}
}
