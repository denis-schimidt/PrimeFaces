package br.com.schimidtsolutions.jsf.validation;

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
@Constraint(validatedBy=IntervaloValidator.class)
public @interface Intervalo {
	long menor();
	long maior();
	String message() default "Número fora do intervalo previsto!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
