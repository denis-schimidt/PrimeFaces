package br.com.schimidtsolutions.jsf.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntervaloValidator implements ConstraintValidator<Intervalo, Number> {
	private Number numeroMenor;
	private Number numeroMaior;
	
	@Override
	public void initialize(Intervalo constraintAnnotation) {
		numeroMaior = constraintAnnotation.maior();
		numeroMenor = constraintAnnotation.menor();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {

		if( value != null ){
			long valorAsLong = value.longValue();
			
			return numeroMenor.longValue() >= valorAsLong &&
					numeroMaior.longValue() <= valorAsLong;
		}
		
		return false;
	}
}
