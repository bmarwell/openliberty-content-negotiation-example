package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
@Documented
public @interface HttpHeader {

    /**
     * Header name.
     */
    @Nonbinding
    String value() default "";
}
