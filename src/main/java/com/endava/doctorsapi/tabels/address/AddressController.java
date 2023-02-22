package com.endava.doctorsapi.tabels.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping()
	public void onPost(@RequestBody(required = false) Optional<Address> addr,
	                   @RequestParam(name = "street", required = false) Optional<String> street,
	                   @RequestParam(name = "houseNumber", required = false) Optional<String> houseNumber,
	                   @RequestParam(name = "postCode", required = false) Optional<String> postCode,
	                   @RequestParam(name = "location", required = false) Optional<String> location) {

		if (addr.isPresent() &&
				street.isEmpty() &&
				houseNumber.isEmpty() &&
				postCode.isEmpty() &&
				location.isEmpty()
		) {
			if (addr.get().getStreet() == null ||
					addr.get().getHouseNumber() == null ||
					addr.get().getPostCode() < 0 ||
					addr.get().getLocation() == null
			) {
				throw new AddressManagementException("Please provide a Street, House number, postCode, and location");
			}
			addressService.postAddress(addr.get());
			return;
		}

		if (addr.isEmpty() &&
				street.isPresent() &&
				houseNumber.isPresent() &&
				postCode.isPresent() &&
				location.isPresent()
		) {
			int parsedPostCode = Integer.parseInt(postCode.get());

			if (parsedPostCode < 0) {
				throw new AddressManagementException("Please provide a correct post code");
			}

			addressService.postAddress(street.get(),houseNumber.get(),parsedPostCode,location.get());
			return;
		}

		throw new AddressManagementException("Please provide a RequestBody with street, houseNumber, postCode location or Query Params with them");
	}
}
