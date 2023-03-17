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

	public Facility postFacility(String name) {
		return repo.save(new Facility(name));
	}

	public Facility postFacility(Facility facility) {
		return this.postFacility(facility.getName());
	}

	public Facility put(Long id, String name) {
		Facility facility = repo.findById(id)
				.orElseThrow(() -> {
					throw new CmsException("id not found");
				});
		facility.setName(name);
		return repo.save(facility);
	}

	public Facility Facility(Long id, Facility facility) {
		return this.put(id, facility.getName());
	}
}
