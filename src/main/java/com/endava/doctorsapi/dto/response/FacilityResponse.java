package com.endava.doctorsapi.dto.response;

import com.endava.doctorsapi.dto.request.AddressRequest;
import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.dto.request.DoctorRequest;

import java.util.Set;

public record FacilityResponse(
		long id,
		String facilityName,
		Set<DepartmentRequest> departments,
		Set<DoctorRequest> doctors,

		Set<AddressRequest> addresses
) {
}
