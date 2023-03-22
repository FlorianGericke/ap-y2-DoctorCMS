package com.endava.doctorsapi.dto.response;

import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.dto.request.FacilityRequest;

import java.util.Set;

public record DoctorResponse(
		long id,
		String firstName,
		String lastName,

		Set<DepartmentRequest> departmentRequests,
		Set<FacilityRequest> facilities
) {
}
