package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.dto.mappers.AddressMapper;
import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/address")
public class AddressController extends BaseController<Address, AddressService, AddressResponse, AddressMapper> {

	@Autowired
	public AddressController(AddressService addressService, AddressMapper addressMapper) {
		super(addressService,addressMapper);
	}

	@PostMapping()
	public ResponseEntity<AddressResponse> onPost(@RequestBody() Optional<Address> address) {
		validate(address);
		return new ResponseEntity<>(mapper.map(service.postAddress(address.get())),HttpStatus.OK);
	}


	@PutMapping("/{id}")
	public ResponseEntity<AddressResponse> onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Address> address) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		validate(address);
		return new ResponseEntity<>(mapper.map(service.putAddress(id, address.get())),HttpStatus.OK);
	}

	@PatchMapping("/{addressId}/facility/{facilityId}")
	public ResponseEntity<AddressResponse> onPatch(@PathVariable(value = "addressId") long addressId,
	                                               @PathVariable(value = "facilityId") long facilityId){
		return new ResponseEntity<>(mapper.map(service.patchFacility(addressId, facilityId)),HttpStatus.OK);
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





