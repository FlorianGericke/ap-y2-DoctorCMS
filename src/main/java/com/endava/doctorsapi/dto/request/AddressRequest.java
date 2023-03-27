package com.endava.doctorsapi.dto.request;

public record AddressRequest(
		String street,
		String houseNumber,
		String location,
		int postCode
) {
}
