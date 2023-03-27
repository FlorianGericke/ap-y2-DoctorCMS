package com.endava.doctorsapi.dto.request;

public record PatientRequest(
		String insuranceNumber,
		String firstName,
		String lastName,
		int age,
		String phone
) {
}
