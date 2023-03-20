package com.endava.doctorsapi.dto.response;

import java.util.Set;

public record FacilityResponse(
		long id,
		String facilityName,
		Set<AddressResponse> addresses
) {
}
