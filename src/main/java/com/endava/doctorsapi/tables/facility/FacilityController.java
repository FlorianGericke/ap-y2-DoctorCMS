package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.dto.mappers.FacilityMapper;
import com.endava.doctorsapi.dto.request.FacilityRequest;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.general.EntityStates;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/facility")
public class FacilityController extends BaseController<Facility, FacilityService, FacilityResponse, FacilityMapper> {

	@Autowired
	public FacilityController(FacilityService facilityService, FacilityMapper mapper) {
		super(facilityService, mapper);
	}

	@PostMapping()
	public ResponseEntity<FacilityResponse> onPost(@RequestBody() FacilityRequest facilityRequest) {
		return new ResponseEntity<>(mapper.map(service.postFacility(facilityRequest)), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FacilityResponse> onPut(@PathVariable(value = "id") Long id,
	                                              @RequestBody() FacilityRequest facilityRequest) {

		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		if (service.get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new CmsException("Cannot change a deleted object");
		}


		return new ResponseEntity<>(mapper.map(service.put(id, facilityRequest)), HttpStatus.OK);
	}

	@PatchMapping("/{facilityId}/address/{addressId}")
	public ResponseEntity<FacilityResponse> onPatch(@PathVariable(value = "facilityId") long facilityId,
	                                                @PathVariable(value = "addressId") long addressId) {
		return new ResponseEntity<>(mapper.map(service.patchAddress(facilityId, addressId)), HttpStatus.OK);
	}

	@PatchMapping("/{facId}/department/{depId}/doctor/{docId}")
	public ResponseEntity<FacilityResponse> onPatch(@PathVariable("facId") long facId,
	                                                @PathVariable("depId") long depId,
	                                                @PathVariable("docId") long docId) {

		return new ResponseEntity<>(mapper.map(service.patchDoctorFacilityDepartment(docId, facId, depId)), HttpStatus.OK);
	}
}
