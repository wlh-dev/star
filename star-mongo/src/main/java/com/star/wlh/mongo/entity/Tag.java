package com.star.wlh.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 * 打标签的信息。
 * 
 * @author hesy
 */
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 标签文本的正则匹配
	 */
	private static final transient Pattern PATTERN = 
			Pattern.compile("[\u4E00-\u9FA5 . a-z A-Z 0-9 \\- _]{1,25}|[\u4E00-\u9FA5 . a-z A-Z 0-9 \\- _]{1,25}:[\u4E00-\u9FA5 . a-z A-Z 0-9 \\- _]{1,120}");

	/**
	 * K/V 标签的 key 值和 value 值之间的分隔符
	 */
	public static final String SEPARATOR = ":";

	/**
	 * 当标签为普通 text 时，存储标签信息<br>
	 * 当标签为key/value 类型时，存储 KEY 信息。
	 */
	@Field("K")
	private String key;

	/**
	 * 当标签为key/value 类型时，存储 value 信息
	 */
	@Field("V")
	private String value;

	/**
	 * 是否为内置，体现标签打在某个资源对象上这个操作是否是系统内置的
	 */
	@Field("B")
	private boolean builtin;

	/**
	 * 来源
	 */
	@Field("S")
	private String source;
	
	/**
	 * 所有者
	 * 
	 * @since 2.0.8.4
	 */
	@Field("O")
	private String owner;
	
	public Tag() {
		// empty
	}

	public Tag(String tagText) {
		this.key = tagText;
	}

	public Tag(String tagText, boolean builtin) {
		this.key = tagText;
		this.builtin = builtin;
	}

	public Tag(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Tag(String key, String value, boolean builtin) {
		this.key = key;
		this.value = value;
		this.builtin = builtin;
	}

	public Tag(String key, String value, boolean builtin, String source) {
		this.key = key;
		this.value = value;
		this.builtin = builtin;
		this.source = source;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	/**
	 * 根据提供的字符串构造 tag 对象。
	 * 
	 * @param tagText 单个文本或者用 : 分隔的 key/value 对。
	 * @return tag 对象
	 */
	public static Tag fromString(String tagText) {
		if (tagText == null || tagText.isEmpty()) {
			return null;
		}
		String text = tagText.trim();
		if (!PATTERN.matcher(tagText).matches()) {
			throw new IllegalArgumentException("Invalid tag text: " + tagText);
		}
		String[] keyValues = text.split(SEPARATOR, 2);

		if (keyValues.length == 1) {
			return new Tag(keyValues[0]);
		}
		return new Tag(keyValues[0], keyValues[1]);
	}
	
	/**
	 * 转换为字符串形式。
	 * 
	 * @return 如果是 key/value 形式，则用 : 分隔
	 */
	public String asText() {
		if (value == null) {
			return key;
		}
		return key.concat(SEPARATOR).concat(value);
	}
	
	/**
	 * 返回标签的唯一标识。
	 * 
	 * @return 唯一标识
	 */
	public String getIdentifer() {
		StringJoiner sj = new StringJoiner(SEPARATOR);
		sj.add(key);
		if (value != null) {
			sj.add(value);
		}
		if (owner != null) {
			sj.add(owner);
		}
		return sj.toString();
	}
	
	/**
	 * 根据提供的标签唯一标识，构造 tag 对象。
	 * 
	 * @param identifier 唯一标识
	 * @return 标签对象
	 */
	public static Tag fromIdentifer(String identifier) {
		if (identifier == null || identifier.isEmpty()) {
			return null;
		}
		String identifierTxt = identifier.trim();
		String[] keyVals = identifierTxt.split(SEPARATOR, 3);
		if (keyVals.length == 1) {
			return new Tag(keyVals[0]);
		}
		Tag tag = new Tag(keyVals[0], keyVals[1]);
		if (keyVals.length == 3) {
			tag.setOwner(keyVals[2]);
		}
		return tag;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isBuiltin() {
		return builtin;
	}

	public void setBuiltin(boolean builtin) {
		this.builtin = builtin;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public Tag setOwner(String owner) {
		this.owner = owner;
		return this;
	}
	
	public String getOwner() {
		return owner;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || !this.getClass().isInstance(obj)) {
			return false;
		}
		Tag tag = (Tag) obj;
		return Objects.deepEquals(
				new Object[] {key, value, builtin, source, owner}, 
				new Object[] {tag.key, tag.value, tag.builtin, tag.source, tag.owner}
		);
	}

	@Override
	public String toString() {
		return "{key:" + key + ", value:" + value + ", builtin:" + builtin + ", source=" + source  + ", owner=" + owner + "}";
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value, builtin, source, owner);
	}
}
