package com.star.wlh.common.query;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记 Entity 的属性字段是否支持作为查询条件。
 * 
 * @author hesy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface QueryFiled {

	/**
	 * 是否支持查询。
	 * 
	 * @return true、false
	 */
	boolean queryable() default true;
}
