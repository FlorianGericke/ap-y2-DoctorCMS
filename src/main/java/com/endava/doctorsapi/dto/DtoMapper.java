package com.endava.doctorsapi.dto;

import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.tables.address.Address;
import com.endava.doctorsapi.tables.facility.Facility;

import java.util.Set;
import java.util.stream.Collectors;

public class DtoMapper {
	public static AddressResponse AddressToAddressResponse(Address address) {
		Set<Facility> facilitySet = address.getFacilities().stream()
				.map(facility -> new Facility(facility.getName())).collect(Collectors.toSet());

		return new AddressResponse(address.getId(),
				address.getStreet(),
				address.getHouseNumber(),
				address.getPostCode(),
				address.getLocation(),
				facilitySet);
	}

	public static FacilityResponse FacilityToFacilityResponse(Facility facility) {
		Set<Address> addressSet = facility.getAddresses().stream()
				.map(address -> new Address(address.getStreet(),address.getHouseNumber(), address.getPostCode(),address.getLocation())).collect(Collectors.toSet());

		return new FacilityResponse(facility.getId(), facility.getName(), addressSet);
	}
}
