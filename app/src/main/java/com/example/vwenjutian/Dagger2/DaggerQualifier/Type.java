package com.example.vwenjutian.Dagger2.DaggerQualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {
    String value() default "";
}
