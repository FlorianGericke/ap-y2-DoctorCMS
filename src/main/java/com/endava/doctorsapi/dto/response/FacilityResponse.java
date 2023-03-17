package com.endava.doctorsapi.dto.response;


public record FacilityResponse(
		String facilityName,
		AddressResponse[] addresses
) {
}
