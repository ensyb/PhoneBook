package io.github.ensyb.phone.application.repository.mappers;

@FunctionalInterface
public interface DataOutputMapper<Type> {

	public Object[] mapper(Type object);
}
