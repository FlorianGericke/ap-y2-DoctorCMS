package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.tables.general.EntityStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

	private final AddressRepo addressRepo;

	@Autowired
	public AddressService(AddressRepo addressRepo) {
		this.addressRepo = addressRepo;
	}

	public void postAddress(String street, String houseNumber, int postCode, String location) {
		postAddress(new Address(street, houseNumber, postCode, location));
	}

	public void postAddress(Address address) {
		addressRepo.save(address);
	}

	public void putAddress(Long id, Address address) {
		putAddress(id, address.getStreet(), address.getHouseNumber(), address.getPostCode(), address.getLocation());
	}

	public void putAddress(Long id, String street, String houseNumber, int postCode, String location) {
		Address addr = addressRepo.findById(id)
				.orElseThrow(() -> {
					throw new AddressManagementException("id not found");
				});

		addr.setStreet(street);
		addr.setHouseNumber(houseNumber);
		addr.setPostCode(postCode);
		addr.setLocation(location);
		addressRepo.save(addr);
	}

	public Address get(Long id) {
		return addressRepo.findById(id)
				.orElseThrow(() -> {
					throw new AddressManagementException("id not found");
				});
	}

	public List<Address> getAll() {
		return addressRepo.findAllByStateIsNot(EntityStates.DELETED.toString());
	}

	public void delete(Long id) {
		addressRepo.deleteById(id);
	}

	public void deleteAllById(Iterable<? extends Long> longs) {
		addressRepo.deleteAllById(longs);
	}

	public void deleteAll() {
		addressRepo.deleteAll();
	}
}
