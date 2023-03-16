package com.star.wlh.user.convert;

import com.star.wlh.user.enums.GenderEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class EnumConvert implements Converter<String, GenderEnum> {
	private final Map<String,GenderEnum> map1 = new HashMap<>();
	private final Map<String,GenderEnum> map2 = new HashMap<>();

	public EnumConvert() {
		Arrays.stream(GenderEnum.class.getEnumConstants())
				.forEach(x->{
					map1.put(String.valueOf(x.getValue()),x);
					map2.put(x.toString(),x);
				}
		);
	}

	@Override public GenderEnum convert(String source) {
		return Optional.of(source)
				.map(map1::get)
				.orElseGet(()-> map2.get(source) ==null? GenderEnum.UNKNOWN:map2.get(source));
	}

}
