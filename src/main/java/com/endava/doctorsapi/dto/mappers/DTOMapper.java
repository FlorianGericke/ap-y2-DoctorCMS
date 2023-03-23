package com.endava.doctorsapi.dto.mappers;

import org.springframework.stereotype.Service;

@FunctionalInterface
public interface DTOMapper<T,R> {
	R map(T value);
}
