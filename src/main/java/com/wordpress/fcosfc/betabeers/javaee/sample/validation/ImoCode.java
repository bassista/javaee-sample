package com.wordpress.fcosfc.betabeers.javaee.sample.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Paco Saucedo
 */
@Documented
@Constraint(validatedBy = ImoCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImoCode {

    String message() default "{com.wordpress.fcosfc.betabeers.javaee.sample.validation.ImoCode}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
