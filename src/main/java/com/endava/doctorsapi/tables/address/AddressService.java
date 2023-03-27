package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.dto.request.AddressRequest;
import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import com.endava.doctorsapi.tables.facility.FacilityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseService<Address, AddressRepo> {

	private final FacilityService facilityService;

	@Autowired
	public AddressService(AddressRepo addressRepo, FacilityService facilityService) {
		super(addressRepo);
		this.facilityService = facilityService;
	}

	public Address postAddress(String street, String houseNumber, int postCode, String location) {
		return repo.save(new Address(street, houseNumber, postCode, location));
	}

	public Address postAddress(AddressRequest addressRequest) {
		return postAddress(addressRequest.street(),
				addressRequest.houseNumber(),
				addressRequest.postCode(),
				addressRequest.location());
	}

	public Address putAddress(Long id, AddressRequest addressRequest) {
		return putAddress(id, addressRequest.street(),
				addressRequest.houseNumber(),
				addressRequest.postCode(),
				addressRequest.location());
	}

	public Address putAddress(Long id, String street, String houseNumber, int postCode, String location) {
		Address addr = repo.findById(id)
				.orElseThrow(() -> {
					throw new ServiceException(this, "id not found");
				});

		addr.setStreet(street);
		addr.setHouseNumber(houseNumber);
		addr.setPostCode(postCode);
		addr.setLocation(location);
		return repo.save(addr);
	}

	@Transactional
	public Address patchFacility(long addressId, long facilityId) {
		facilityService.patchAddress(facilityId, addressId);
		return repo.findById(addressId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, this, "Address not found"));
	}
}
