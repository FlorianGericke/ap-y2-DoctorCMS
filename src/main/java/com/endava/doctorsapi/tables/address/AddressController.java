package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.dto.mappers.AddressMapper;
import com.endava.doctorsapi.dto.request.AddressRequest;
import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.general.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/address")
public class AddressController extends BaseController<Address, AddressService, AddressResponse, AddressMapper> {

	@Autowired
	public AddressController(AddressService addressService, AddressMapper addressMapper) {
		super(addressService, addressMapper);
	}

	@PostMapping()
	public ResponseEntity<AddressResponse> onPost(@RequestBody() AddressRequest addressRequest) {
		return new ResponseEntity<>(mapper.map(service.postAddress(addressRequest)), HttpStatus.OK);
	}


	@PutMapping("/{id}")
	public ResponseEntity<AddressResponse> onPut(@PathVariable(value = "id") Long id,
	                                             @RequestBody() AddressRequest addressRequest) {
		return new ResponseEntity<>(mapper.map(service.putAddress(id, addressRequest)), HttpStatus.OK);
	}

	@PatchMapping("/{addressId}/facility/{facilityId}")
	public ResponseEntity<AddressResponse> onPatch(@PathVariable(value = "addressId") long addressId,
	                                               @PathVariable(value = "facilityId") long facilityId) {
		return new ResponseEntity<>(mapper.map(service.patchFacility(addressId, facilityId)), HttpStatus.OK);
	}
}





