package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.general.EntityStates;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/facility")
public class FacilityController extends BaseController<Facility, FacilityService> {

	@Autowired
	public FacilityController(FacilityService facilityService) {
		super(facilityService);
	}

	@PostMapping()
	public void onPost(@RequestBody() Optional<Facility> facility) {

		if (facility.isEmpty()) {
			throw new CmsException("Please provide a RequestBody withe attribute name");
		}

		if (facility.get().getName() == null || facility.get().getName().length() < 2) {
			throw new CmsException("Please provide a name (minimum 2 chars)");
		}

		service.postFacility(facility.get());
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id,
	                  @RequestBody() Optional<Facility> facility) {

		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		if (facility.isEmpty()) {
			throw new CmsException("Please provide a RequestBody withe attribute name");
		}

		if (service.get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new CmsException("Cannot change a deleted object");
		}

		if (facility.get().getName() == null || facility.get().getName().length() < 2) {
			throw new CmsException("Please provide a name (minimum 2 chars)");
		}
		service.put(id, facility.get());
	}

}
