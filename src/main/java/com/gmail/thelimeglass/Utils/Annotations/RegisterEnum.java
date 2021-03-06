package com.gmail.thelimeglass.Utils.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterEnum {

    @SuppressWarnings("rawtypes")
    Class ExprClass() default String.class;

    String value();
}