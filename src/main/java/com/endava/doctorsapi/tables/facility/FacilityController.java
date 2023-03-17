package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.dto.mappers.DtoFacilityMapper;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.general.EntityStates;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/facility")
public class FacilityController extends BaseController<Facility, FacilityService, FacilityResponse, DtoFacilityMapper> {

	@Autowired
	public FacilityController(FacilityService facilityService, DtoFacilityMapper mapper) {
		super(facilityService, mapper);
	}

	@PostMapping()
	public ResponseEntity<FacilityResponse> onPost(@RequestBody() Optional<Facility> facility) {

		validate(facility);

		return new ResponseEntity<>(mapper.apply(service.postFacility(facility.get())), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FacilityResponse> onPut(@PathVariable(value = "id") Long id,
	                                              @RequestBody() Optional<Facility> facility) {

		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		if (service.get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new CmsException("Cannot change a deleted object");
		}

		validate(facility);

		return new ResponseEntity<>(mapper.apply(service.put(id, facility.get().getName())), HttpStatus.OK);
	}

	private void validate(Optional<Facility> facility) {
		if (facility.isEmpty()) {
			throw new CmsException("Please provide a RequestBody withe attribute name");
		}

		if (facility.get().getName() == null || facility.get().getName().length() < 2) {
			throw new CmsException("Please provide a name (minimum 2 chars)");
		}
	}

}
