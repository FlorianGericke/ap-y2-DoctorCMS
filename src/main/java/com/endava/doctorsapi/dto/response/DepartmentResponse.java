package com.endava.doctorsapi.dto.response;

import com.endava.doctorsapi.dto.request.DoctorRequest;
import com.endava.doctorsapi.dto.request.FacilityRequest;

import java.util.Set;

public record DepartmentResponse(
		long id,
		String departmentName,
		Set<FacilityRequest> facilities,
		Set<DoctorRequest> doctors
) {
}
