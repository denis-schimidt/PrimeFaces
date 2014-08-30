package br.com.schimidtsolutions.jsf.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class NaoVazioValidator implements ConstraintValidator<NaoVazio, Object> {

	@Override
	public void initialize(NaoVazio constraintAnnotation) {}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		if( value != null ){
			
			if( value instanceof String ){
				return StringUtils.isNotBlank( (String) value );
			}
		}
		
		return false;
	}
}
