package com.soft.fire.annotation;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
