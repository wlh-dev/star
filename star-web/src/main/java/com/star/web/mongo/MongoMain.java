package com.star.web.mongo;

import com.star.web.entity.DictTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wlh
 * @date Date : 2022年07月01日 15:50
 */

@RestController
@RequestMapping("mongo")
public class MongoMain {

		@Autowired
		private MongoTemplate mongoTemplate;


		@RequestMapping("/test")
		public void mongoTest(){
				DictTest dictTest = new DictTest();
				Map<String, String> map = new HashMap<>();
				map.put("aaa","aaaa");
				map.put("bbb","bbbb");
				map.put("ccc","cccc");
				map.put("ddd","dddd");
				dictTest.setValues(map);
				DictTest save = mongoTemplate.save(dictTest);
				System.out.println("保存结果"+save);
				DictTest saveDictTest = new DictTest();
				saveDictTest.setId(save.getId());
				Map<String, String> saveMap = new HashMap<>();
				saveMap.put("aaa","aaaa");
				saveMap.put("bbb","bbbb");
				saveMap.put("ccc","cccc");
				saveDictTest.setValues(saveMap);
				mongoTemplate.save(saveDictTest);
		}



}
