package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.tables.address.Address;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DtoAddressMapper implements Function<Address, AddressResponse> {
	@Override
	public AddressResponse apply(Address address) {
		return new AddressResponse(address.getId(),
				address.getStreet(),
				address.getHouseNumber(),
				address.getLocation(),
				address.getPostCode(),
				address.getFacilities()
						.stream()
						.map(facility -> new FacilityResponse(facility.getId(), facility.getName(), null))
						.collect(Collectors.toSet()));
	}
}
