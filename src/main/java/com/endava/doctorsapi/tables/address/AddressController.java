package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.tables.doctor.DeleteAllById;
import com.endava.doctorsapi.tables.doctor.DoctorManagementException;
import com.endava.doctorsapi.tables.general.EntityStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

		validateAndDo(addr, street, houseNumber, postCode, location, addressService::postAddress);
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id,
	                  @RequestBody(required = false) Optional<Address> addr,
	                  @RequestParam(name = "street", required = false) Optional<String> street,
	                  @RequestParam(name = "houseNumber", required = false) Optional<String> houseNumber,
	                  @RequestParam(name = "postCode", required = false) Optional<String> postCode,
	                  @RequestParam(name = "location", required = false) Optional<String> location) {

		validateAndDo(addr, street, houseNumber, postCode, location, address -> addressService.putAddress(id, address));
	}

	@GetMapping("/{id}")
	public Address onGet(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new DoctorManagementException("Invalid param id is null");
		}
		return addressService.get(id);
	}

	@GetMapping()
	public List<Address> onGetAll() {
		return addressService.getAll();
	}

	@DeleteMapping("/{id}")
	public void onDelete(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new DoctorManagementException("Invalid param id is null");
		}

		if (addressService.get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new DoctorManagementException("Cannot delete a doctor that is already in the state deleted");
		}
		addressService.delete(id);
	}

	@DeleteMapping()
	public void onDeleteAll(@RequestBody(required = false) Optional<DeleteAllById> params) {
		if (params.isPresent()) {
			Iterator<Long> ids = Arrays.stream(params.get().ids()).iterator();
			addressService.deleteAllById(new Iterable<Long>() {
				@Override
				public Iterator<Long> iterator() {
					return ids;
				}
			});
			return;
		}

		addressService.deleteAll();
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
				throw new AddressManagementException("Please provide a valid Street, House number, postCode, and location in RequestBody (min 3 chars)");
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
				throw new AddressManagementException("Please provide a valid Street, House number, postCode, and location in RequestBody (min 3 chars)");
			}

			resolve.accept(new Address(street.get(), houseNumber.get(), parsedPostCode, location.get()));
			return;
		}

		throw new AddressManagementException("Please provide a RequestBody with street, houseNumber, postCode location or Query Params with them");

	}

}





