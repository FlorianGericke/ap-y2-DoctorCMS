package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.tables.facility.Facility;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DtoFacilityMapper implements Function<Facility, FacilityResponse> {
	@Override
	public FacilityResponse apply(Facility facility) {
		return new FacilityResponse(
				facility.getId(),
				facility.getName(),
				facility.getAddresses().stream()
						.map(address -> new AddressResponse(
								address.getId(),
								address.getStreet(),
								address.getHouseNumber(),
								address.getLocation(),
								address.getPostCode(),
								null
						))
						.collect(Collectors.toSet())
		);
	}
}
