package com.endava.doctorsapi.dto.response;


import java.util.Set;

public record FacilityResponse(
		String facilityName,
		Set<AddressResponse> addresses
) {
}
