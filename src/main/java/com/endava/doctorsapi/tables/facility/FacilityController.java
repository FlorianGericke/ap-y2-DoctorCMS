package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.dto.mappers.FacilityMapper;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.general.EntityStates;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/facility")
public class FacilityController extends BaseController<Facility, FacilityService, FacilityResponse, FacilityMapper> {

	@Autowired
	public FacilityController(FacilityService facilityService, FacilityMapper mapper) {
		super(facilityService, mapper);
	}

	@PostMapping()
	public ResponseEntity<FacilityResponse> onPost(@RequestBody() Optional<Facility> facility) {

		validate(facility);

		return new ResponseEntity<>(mapper.map(service.postFacility(facility.get())), HttpStatus.OK);
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

		return new ResponseEntity<>(mapper.map(service.put(id, facility.get().getName())), HttpStatus.OK);
	}

	@PatchMapping("/{facilityId}/address/{addressId}")
	public ResponseEntity<FacilityResponse> onPatch(@PathVariable(value = "facilityId") long facilityId,
	                    @PathVariable(value = "addressId") long addressId){
		return new ResponseEntity<>(mapper.map(service.patchAddress(facilityId,addressId)),HttpStatus.OK);
	}

	@PatchMapping("/{facId}/department/{depId}/doctor/{docId}")
	public ResponseEntity<FacilityResponse> onPatch(@PathVariable("facId") Optional<Long> facId,
	                                                  @PathVariable("depId") Optional<Long> depId,
	                                                  @PathVariable("docId") Optional<Long> docId) {

		return new ResponseEntity<>(mapper.map(service.patchDoctorFacilityDepartment(docId.get(), facId.get(), depId.get())), HttpStatus.OK);
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
