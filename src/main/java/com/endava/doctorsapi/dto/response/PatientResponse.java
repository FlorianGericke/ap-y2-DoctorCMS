package com.endava.doctorsapi.dto.response;

import com.endava.doctorsapi.dto.request.AddressRequest;

public record PatientResponse(
		long id,
		String insuranceNumber,
		String firstName,
		String lastName,
		int age,
		String phone,
		AddressRequest address
) {
}
