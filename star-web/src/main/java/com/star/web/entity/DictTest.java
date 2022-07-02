package com.star.web.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @author : wlh
 * @date Date : 2022年07月01日 15:52
 */

@Data
@Document(collation = "dictTest")
public class DictTest {

		@Id
		private String id;
		private String code;
		private Map<String, String> values;
		private DictTest children;
}
