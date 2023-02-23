package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.tables.general.ControllerBase;
import com.endava.doctorsapi.tables.general.DeleteAllById;
import com.endava.doctorsapi.tables.general.EntityStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController extends ControllerBase<Doctor,DoctorRepo,DoctorService> {

	@Autowired
	public DoctorController(DoctorService doctorService) {
		super(doctorService);
	}

	@PostMapping()
	public void onPost(@RequestBody(required = false) Optional<Doctor> doc,
	                   @RequestParam(name = "firstname", required = false) Optional<String> firstname,
	                   @RequestParam(name = "lastname", required = false) Optional<String> lastname) {

		if (doc.isPresent() && firstname.isEmpty() && lastname.isEmpty()) {
			if (doc.get().getFirstName() == null || doc.get().getFirstName().length() < 3 ||
					doc.get().getLastName() == null || doc.get().getLastName().length() < 3) {
				throw new DoctorManagementException("Please provide a first name and last name (minimum 3 chars)");
			}
			service.postDoctor(doc.get());
			return;
		}

		if (doc.isEmpty() && firstname.isPresent() && lastname.isPresent()) {
			if (firstname.get().length() < 3 || lastname.get().length() < 3) {
				throw new DoctorManagementException("Please provide a first name and last name (minimum 3 chars)");
			}
			service.postDoctor(firstname.get(), lastname.get());
			return;
		}

		throw new DoctorManagementException("Please provide a RequestBody firstname and lastname or Query Params with them");
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id,
	                  @RequestBody(required = false) Optional<Doctor> doc,
	                  @RequestParam(name = "firstname", required = false) Optional<String> firstname,
	                  @RequestParam(name = "lastname", required = false) Optional<String> lastname) {
		if (id == null) {
			throw new DoctorManagementException("Invalid param id is null");
		}

		if(doc.isEmpty() && firstname.isPresent() && lastname.isPresent()) {
			if (service.get(id).getState().equals(EntityStates.DELETED.toString())) {
				throw new DoctorManagementException("Cannot change a deleted Doctor");
			}
			if (firstname.get().length() < 3|| lastname.get().length() < 3) {
				throw new DoctorManagementException("Please provide a first name and last name (minimum 3 chars)");
			}
			service.put(id, firstname.get(), lastname.get());
			return;
		}

		if(doc.isPresent() && firstname.isEmpty() && lastname.isEmpty()) {
			if (doc.get().getFirstName() == null || doc.get().getFirstName().length() < 3 ||
					doc.get().getLastName() == null || doc.get().getLastName().length() < 3) {
				throw new DoctorManagementException("Please provide a first name and last name (minimum 3 chars)");
			}
			service.put(id, doc.get().getFirstName(), doc.get().getLastName());
			return;
		}

		throw new DoctorManagementException("Please provide a RequestBody firstname and lastname or Query Params with them");

	}
}
