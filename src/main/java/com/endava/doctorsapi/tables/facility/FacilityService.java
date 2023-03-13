package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.tables.doctor.DoctorRepo;
import com.endava.doctorsapi.tables.general.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FacilityService extends ServiceBase<Facility,Long, FacilityRepo> {

	@Autowired
	public FacilityService(FacilityRepo facilityRepo) {
		super(facilityRepo);
	}
}
