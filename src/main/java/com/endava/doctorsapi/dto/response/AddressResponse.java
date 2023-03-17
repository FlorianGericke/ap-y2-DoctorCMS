package com.endava.doctorsapi.dto.response;


import java.util.Set;

public record AddressResponse(
		long id,
		String street,
		String houseNumber,
		String location,
		int postCode,
		Set<FacilityResponse> facilities
) {
}
