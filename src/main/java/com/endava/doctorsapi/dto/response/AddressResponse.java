package com.endava.doctorsapi.dto.response;


public record AddressResponse(
		String street,
		String houseNumber,
		int postCode,
		String location,
		FacilityResponse[] facilities
) {
}
