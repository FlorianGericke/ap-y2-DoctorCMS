package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.CmsException;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import com.endava.doctorsapi.tables.address.Address;
import com.endava.doctorsapi.tables.address.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FacilityService extends BaseService<Facility, FacilityRepo> {

	private final AddressRepo addressRepo;

	@Autowired
	public FacilityService(FacilityRepo facilityRepo, AddressRepo addressRepo) {
		super(facilityRepo);
		this.addressRepo = addressRepo;
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

	public Facility patchAddress(long facilityId, long addressId) {
		Facility facilityRef = repo.findById(facilityId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, this, "Facility not found"));
		Address addressRef = addressRepo.findById(addressId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, this, "Address not found"));

		facilityRef.assignAddress(addressRef);
		return repo.save(facilityRef);
	}

	public Facility Facility(Long id, Facility facility) {
		return this.put(id, facility.getName());
	}
}
