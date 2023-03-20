package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import com.endava.doctorsapi.tables.facility.Facility;
import com.endava.doctorsapi.tables.facility.FacilityRepo;
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
		 return postAddress(new Address(street, houseNumber, postCode, location));
	}

	public  Address postAddress(Address address) {
		return repo.save(address);
	}

	public  Address putAddress(Long id, Address address) {
		return putAddress(id, address.getStreet(), address.getHouseNumber(), address.getPostCode(), address.getLocation());
	}

	public  Address putAddress(Long id, String street, String houseNumber, int postCode, String location) {
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
		facilityService.patchAddress(facilityId,addressId);
		Address re = repo.findById(addressId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, this, "Address not found"));
		return re;
	}
}
