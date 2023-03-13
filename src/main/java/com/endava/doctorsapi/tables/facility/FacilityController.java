package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.tables.general.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/facilitys")
public class FacilityController extends ControllerBase<Facility, FacilityRepo, FacilityService> {

	@Autowired
	public FacilityController(FacilityService facilityService) {
		super(facilityService);
	}
}
