package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.dto.mappers.DtoAddressMapper;
import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseService<Address, AddressRepo> {

	@Autowired
	public AddressService(AddressRepo addressRepo) {
		super(addressRepo);
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
		repo.save(addr);
		return addr;
	}
}
