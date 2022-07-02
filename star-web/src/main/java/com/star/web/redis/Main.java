package com.star.web.redis;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.star.web.entity.DictTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.*;

/**
 * @author : wlh
 * @date Date : 2022年06月28日 10:32
 */

public class Main {
		public static void main(String[] args) {

				DictTest dictTest = new DictTest();
				dictTest.setCode("aaaa");
				DictTest children = new DictTest();
				children.setCode("aaaaabbbbb");


				Map<String, Object> map = new HashMap<>();
				List<String> list = new ArrayList<>();
				map.put("default", list);



				Object value = map.get("default");

				List<String> defaultValue = (List<String>) value;
				System.out.println(defaultValue);

		}
}
