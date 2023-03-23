package com.endava.doctorsapi.dto.response;

import jakarta.persistence.Column;

public record PatientResponse(
		long id,
		String insuranceNumber,
		String firstName,
		String lastName,
		int age,
		String phone) {

}
