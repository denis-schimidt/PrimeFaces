package br.com.schimidtsolutions.jsf.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.LocalDate;

public class PastValidator implements ConstraintValidator<Past, LocalDate> {

	@Override
	public void initialize(final Past annotation ) {}

	@Override
	public boolean isValid(final LocalDate value, final ConstraintValidatorContext context) {
		
		final LocalDate dataHoje = LocalDate.now();
		
		return value != null && value.isBefore( dataHoje ) || value.equals( dataHoje );
	}
}
