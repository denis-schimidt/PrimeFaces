package br.com.schimidtsolutions.jsf.entidades.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=PastValidator.class)
public @interface Past {
	
	String message() default "A data precisa ser inferior ou igual a data de hoje!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
