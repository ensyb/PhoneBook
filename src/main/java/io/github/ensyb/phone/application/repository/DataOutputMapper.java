package io.github.ensyb.phone.application.repository;

@FunctionalInterface
public interface DataOutputMapper<Type> {

	public Object[] mapper(Type object);
}
