package com.star.wlh.user.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.util.StringJoiner;

public enum GenderEnum implements IEnum<Integer> {
	MAN(1,"男"),
	WOMAN(0,"女"),
	UNKNOWN(3,"未知");

	private final Integer value;
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
}
