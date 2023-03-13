package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.tables.department.Department;
import com.endava.doctorsapi.tables.general.base.ControllerBase;
import com.endava.doctorsapi.tables.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/facility")
public class FacilityController extends ControllerBase<Facility, FacilityRepo, FacilityService> {

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

}
