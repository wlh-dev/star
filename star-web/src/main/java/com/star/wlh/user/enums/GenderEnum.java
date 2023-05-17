package com.star.wlh.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum implements IEnum<Integer> {
	//default
	UNKNOWN(2,"未知"),
	MAN(1,"男"),
	WOMAN(0,"女");



	@EnumValue
	private final Integer value;
	@JsonValue
	private final String desc;

	GenderEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override public String toString() {
		return this.desc;
	}

	@Override public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
