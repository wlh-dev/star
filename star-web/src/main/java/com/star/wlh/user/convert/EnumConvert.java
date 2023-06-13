package com.star.wlh.user.convert;

import com.star.wlh.user.enums.GenderEnum;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EnumConvert implements Converter<String, GenderEnum> {
    private final Map<String, GenderEnum> map = new HashMap<>();

    public  EnumConvert() {
        Arrays.stream(GenderEnum.class.getEnumConstants())
                .forEach(x -> {
                            map.put(String.valueOf(x.getValue()), x);
                            map.put(x.toString(), x);
                        }
                );
    }

    @Override
    public GenderEnum convert(@Nonnull String source) {
        return Optional.of(source).map(map::get).orElse(GenderEnum.UNKNOWN);
    }

    public static void main(String[] args) {
        EnumConvert enumConvert = new EnumConvert();
        GenderEnum convert = enumConvert.convert(null);
        System.out.println(convert);
    }

}
