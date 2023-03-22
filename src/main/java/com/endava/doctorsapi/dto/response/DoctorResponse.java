package com.endava.doctorsapi.dto.response;

import java.util.Set;

public record DoctorResponse(
		long id,
		String firstName,
		String lastName,
		Set<FacilitiesDepartmentResponse> facilitiesDepartmentResponse
) {
}
