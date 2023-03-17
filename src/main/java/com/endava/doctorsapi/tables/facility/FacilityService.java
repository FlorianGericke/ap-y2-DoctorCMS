package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService extends BaseService<Facility, FacilityRepo> {

	@Autowired
	public FacilityService(FacilityRepo facilityRepo) {
		super(facilityRepo);
	}

	public void postFacility(String name) {
		repo.save(new Facility(name));
	}

	public void postFacility(Facility facility) {
		this.postFacility(facility.getName());
	}

	public void put(Long id, String name) {
		Facility facility = repo.findById(id)
				.orElseThrow(() -> {
					throw new CmsException("id not found");
				});
		facility.setName(name);
		repo.save(facility);
	}

	public void put(Long id, Facility facility) {
		this.put(id, facility.getName());
	}
}
