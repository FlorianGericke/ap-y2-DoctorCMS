package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.request.FacilityRequest;
import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.tables.address.Address;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressMapper implements DTOMapper<Address, AddressResponse> {
	public AddressResponse map(Address address){
		return new AddressResponse(
				address.getId(),
				address.getStreet(),
				address.getHouseNumber(),
				address.getLocation(),
				address.getPostCode(),
				Optional.ofNullable(address.getFacilities()).orElse(Collections.emptySet())
						.stream()
						.map(res
							-> new FacilityRequest(
								res.getName()
						))
						.collect(Collectors.toSet())
		);
	}
}
