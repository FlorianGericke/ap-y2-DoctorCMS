package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.tables.general.EntityStates;
import com.endava.doctorsapi.tables.general.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService extends ServiceBase<Address, AddressRepo> {

	@Autowired
	public AddressService(AddressRepo addressRepo) {
		super(addressRepo);
	}

	public void postAddress(String street, String houseNumber, int postCode, String location) {
		postAddress(new Address(street, houseNumber, postCode, location));
	}

	public void postAddress(Address address) {
		repo.save(address);
	}

	public void putAddress(Long id, Address address) {
		putAddress(id, address.getStreet(), address.getHouseNumber(), address.getPostCode(), address.getLocation());
	}

	public void putAddress(Long id, String street, String houseNumber, int postCode, String location) {
		Address addr = repo.findById(id)
				.orElseThrow(() -> {
					throw new AddressManagementException("id not found");
				});

		addr.setStreet(street);
		addr.setHouseNumber(houseNumber);
		addr.setPostCode(postCode);
		addr.setLocation(location);
		repo.save(addr);
	}
}
