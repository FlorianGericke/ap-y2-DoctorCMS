package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.general.base.ControllerBase;
import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping("api/v1/address")
public class AddressController extends ControllerBase<Address,AddressRepo,AddressService> {

	public AddressController(AddressService addressService) {
		super(addressService);
	}

	@PostMapping()
	public void onPost(@RequestBody(required = false) Optional<Address> addr,
	                   @RequestParam(name = "street", required = false) Optional<String> street,
	                   @RequestParam(name = "houseNumber", required = false) Optional<String> houseNumber,
	                   @RequestParam(name = "postCode", required = false) Optional<String> postCode,
	                   @RequestParam(name = "location", required = false) Optional<String> location) {

		validateAndDo(addr, street, houseNumber, postCode, location, service::postAddress);
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id,
	                  @RequestBody(required = false) Optional<Address> addr,
	                  @RequestParam(name = "street", required = false) Optional<String> street,
	                  @RequestParam(name = "houseNumber", required = false) Optional<String> houseNumber,
	                  @RequestParam(name = "postCode", required = false) Optional<String> postCode,
	                  @RequestParam(name = "location", required = false) Optional<String> location) {

		validateAndDo(addr, street, houseNumber, postCode, location, address -> service.putAddress(id, address));
	}

	private void validateAndDo(Optional<Address> address,
	                           Optional<String> street,
	                           Optional<String> houseNumber,
	                           Optional<String> postCode,
	                           Optional<String> location,
	                           Consumer<Address> resolve) {

		if (address.isPresent() &&
				street.isEmpty() &&
				houseNumber.isEmpty() &&
				postCode.isEmpty() &&
				location.isEmpty()
		) {
			if ((address.get().getStreet() == null || address.get().getStreet().length() < 3) ||
					(address.get().getHouseNumber() == null || address.get().getHouseNumber().length() < 1) ||
					address.get().getPostCode() < 0 ||
					(address.get().getLocation() == null || address.get().getLocation().length() < 3)
			) {
				throw new ControllerException(this, "Please provide a valid Street, House number, postCode, and location in RequestBody (min 3 chars)");
			}

			resolve.accept(address.get());
			return;
		}

		if (address.isEmpty() &&
				street.isPresent() &&
				houseNumber.isPresent() &&
				postCode.isPresent() &&
				location.isPresent()
		) {
			int parsedPostCode = Integer.parseInt(postCode.get());

			if (street.get().length() < 3 ||
					houseNumber.get().length() < 1 ||
					parsedPostCode < 0 ||
					location.get().length() < 3
			) {
				throw new ControllerException(this, "Please provide a valid Street, House number, postCode, and location in RequestBody (min 3 chars)");
			}

			resolve.accept(new Address(street.get(), houseNumber.get(), parsedPostCode, location.get()));
			return;
		}

		throw new ControllerException(this, "Please provide a RequestBody with street, houseNumber, postCode location or Query Params with them");
	}
}





