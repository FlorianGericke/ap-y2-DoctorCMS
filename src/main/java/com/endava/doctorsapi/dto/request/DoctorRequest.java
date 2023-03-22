package com.endava.doctorsapi.dto.request;

public record DoctorRequest(
		long id,
		String firstName,
		String lastName
) {
}
