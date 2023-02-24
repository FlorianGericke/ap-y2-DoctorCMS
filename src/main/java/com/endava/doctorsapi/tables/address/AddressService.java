package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.tables.general.base.ServiceBase;
import com.endava.doctorsapi.tables.general.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends ServiceBase<Address, Long, AddressRepo> {

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
					throw new ServiceException(this, "id not found");
				});

		addr.setStreet(street);
		addr.setHouseNumber(houseNumber);
		addr.setPostCode(postCode);
		addr.setLocation(location);
		repo.save(addr);
	}
}
