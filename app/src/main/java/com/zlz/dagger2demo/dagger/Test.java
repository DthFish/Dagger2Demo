package com.zlz.dagger2demo.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.releasablereferences.CanReleaseReferences;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2017/1/19.
 */
@Scope
@Documented
@CanReleaseReferences
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
}
