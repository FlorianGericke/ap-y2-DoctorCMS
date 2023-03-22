package com.endava.doctorsapi.dto.response;

import com.endava.doctorsapi.dto.request.FacilityRequest;

import java.util.Set;

public record AddressResponse(
		long id,
		String street,
		String houseNumber,
		String location,
		int postCode,
		Set<FacilityRequest> facilities
) {
}
