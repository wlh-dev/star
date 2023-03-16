package com.star.wlh.user.annotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MonitorAnnotation {
	String name();
	MonitorType[] value();
}
