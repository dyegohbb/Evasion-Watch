package br.com.evasion.watch.validation;

import java.time.LocalDate;

import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndDateValidator implements ConstraintValidator<EndDateValidation, String>{

	private String startDate;
	
	@Override
    public void initialize(EndDateValidation constraintAnnotation) {
        startDate = constraintAnnotation.startDate();
    }
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			
			if(value == null || value.isBlank()) {
				return true;
			}
			
			BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
			Object startDateValue = beanWrapper.getPropertyValue(startDate);
			Object endDateValue = beanWrapper.getPropertyValue("endDate");

			if (startDateValue == null || endDateValue == null) {
				return true;
			}

			LocalDate start = (LocalDate) startDateValue;
			LocalDate end = (LocalDate) endDateValue;

			return end.isAfter(start);
		} catch (Exception e) {
			return true;
		}
	}

}
