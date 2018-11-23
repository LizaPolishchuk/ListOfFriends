package com.example.android.testapp.dependency;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * This annotation allows dependencies to create a single instance
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface SingleScope {
}
