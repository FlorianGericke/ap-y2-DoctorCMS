package com.endava.doctorsapi.dto.request;

public record AddressRequest(
		long id,
		String street,
		String houseNumber,
		String location,
		int postCode
) {
}
