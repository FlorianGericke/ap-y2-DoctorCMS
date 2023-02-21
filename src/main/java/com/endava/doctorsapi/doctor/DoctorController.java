package com.endava.doctorsapi.doctor;

import com.endava.doctorsapi.EntityStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

	private final DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@PostMapping
	public void onPost(@RequestBody Doctor doc) {
		if (doc.getFirstName() == null || doc.getFirstName().length() < 3 ||
				doc.getLastName() == null || doc.getLastName().length() < 3) {
			throw new DoctorManagementException("Please provide a first name and last name (minimum 3 chars)");
		}
		doctorService.postDoctor(doc);
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id, @RequestParam(name = "firstname") String firstname, @RequestParam(name = "lastname") String lastname) {
		if (id == null) {
			throw new DoctorManagementException("Invalid param id is null");
		}
		if (doctorService.get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new DoctorManagementException("Cannot change a deleted Doctor");
		}
		if (firstname.equals("") || lastname.equals("")) {
			throw new DoctorManagementException("Please provide a first name and last name");
		}
		doctorService.put(id, firstname, lastname);
	}

	@GetMapping("/{id}")
	public Doctor onGet(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new DoctorManagementException("Invalid param id is null");
		}
		return doctorService.get(id);
	}

	@GetMapping()
	public List<Doctor> onGetAll() {
		return doctorService.getAll();
	}


	@DeleteMapping("/{id}")
	public void onDelete(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new DoctorManagementException("Invalid param id is null");
		}

		if (doctorService.get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new DoctorManagementException("Cannot delete a doctor that is already in the state deleted");
		}
		doctorService.delete(id);
	}

	@DeleteMapping()
	public void onDeleteAll(@RequestBody(required = false) Optional<DeleteAllById> params) {
		if (params.isPresent()) {
			Iterator<Long> ids = Arrays.stream(params.get().ids()).iterator();
			doctorService.deleteAllById(new Iterable<Long>() {
				@Override
				public Iterator<Long> iterator() {
					return ids;
				}
			});
			return;
		}

		doctorService.deleteAll();
	}
}
