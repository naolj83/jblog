package com.douzone.jblog.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})	// annotation을 어디에 붙여야 하는가 -> Method와 TYPE(class)에
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
//	public String value() default "USER";
	public String role() default "USER";
	public boolean test() default false;
}
