package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/address")
public class AddressController extends BaseController<Address, AddressService> {

	public AddressController(AddressService addressService) {
		super(addressService);
	}

	@PostMapping()
	public void onPost(@RequestBody() Optional<Address> address) {
		validate(address);
		service.postAddress(address.get());
	}


	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Address> address) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		validate(address);
		service.putAddress(id, address.get());
	}

	private void validate(Optional<Address> address) {
		if (address.isEmpty()) {
			throw new ControllerException(this, "Please provide a RequestBody withe attributes street, houseNumber, postCode, location");
		}

		if ((address.get().getStreet() == null || address.get().getStreet().length() < 3) ||
				(address.get().getHouseNumber() == null || address.get().getHouseNumber().length() < 1) ||
				address.get().getPostCode() < 0 ||
				(address.get().getLocation() == null || address.get().getLocation().length() < 3)) {
			throw new ControllerException(this, "Please provide a valid Street, House number, postCode, and location in RequestBody (min 3 chars)");
		}
	}
}





