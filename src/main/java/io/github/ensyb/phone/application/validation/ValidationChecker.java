package io.github.ensyb.phone.application.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ValidationChecker {
	
	/**
	 * -- REFAKTOROVAT OVU halabuku -- 
	 */
	private final Map<String, Map<String, String>> report;

	public ValidationChecker(final Object type) {
		this.report = new HashMap<>();
		Field[] fields = type.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Validate) {
					Validate validate = (Validate) annotation;
					Class<? extends Validator>[] validators = validate.validators();
					for (Class<? extends Validator> validator : validators) {
						try {
							Constructor<?> ctor = validator.getConstructor();
							ctor.setAccessible(true);
							Object inst = ctor.newInstance();
							Validator validatorInst = (Validator) inst;
							if (!validatorInst.isValid(field.get(type))) {
								if (!report.containsKey(validate.name())) {
									report.put(validate.name(), new HashMap<>());
									report.get(validate.name()).put(validatorInst.validatorName(),
											validatorInst.failedReason());
								} else {
									Map<String, String> fails = report.get(validate.name());
									fails.put(validatorInst.validatorName(), validatorInst.failedReason());
								}
							}
						} catch (IllegalAccessException | InstantiationException | IllegalArgumentException e) {
							//let na drugi svijet  
							e.printStackTrace();
						} catch (InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						} 
					}

				}
			}
		}
	}

	public String generateReport() {
		StringBuilder reportString = new StringBuilder();
		for (Map.Entry<String, Map<String, String>> entry : report.entrySet()) {
			reportString.append(entry.getKey()).append(" ");
			Map<String, String> vas = entry.getValue();
			for (Map.Entry<String, String> elem : vas.entrySet()) {
				reportString.append(elem.getKey()).append(" ").append(elem.getValue());
			}
		}
		return reportString.toString();

	}
}
