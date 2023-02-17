package com.endava.doctorsapi.doctor;

import com.endava.doctorsapi.EntityStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

	@PutMapping("/")
	public void onPut(@RequestParam(name = "id") Long id, @RequestBody Doctor doc) {
		if (doc.getFirstName() == null || doc.getFirstName().length() < 3 ||
				doc.getLastName() == null || doc.getLastName().length() < 3) {
			throw new DoctorManagementException("Please provide a first name and last name (minimum 3 chars)");
		}
		if (doctorService.get(id).getState().equals(EntityStates.DELETED.toString())){
			throw new DoctorManagementException("Cannot change a deleted Doctor");
		}
		doctorService.put(id, doc);
	}

	@GetMapping("/")
	public Doctor onGet(
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName) {

		if (id != null && firstName == null && lastName == null) {
			return doctorService.get(id);
		}

		if (id == null && firstName != null && lastName != null) {
			return doctorService.get(firstName, lastName);
		}

		throw new DoctorManagementException("Invalid params choose between id or (firstName && lastName)");
	}

	@GetMapping("/all")
	public List<Doctor> onGetAll() {
		return doctorService.getAll();
	}

	@DeleteMapping("/all")
	public void onDeleteAll() {
		doctorService.deleteAll();

	}

	@DeleteMapping()
	@ResponseBody
	public void onDeleteAll(@RequestBody(required = false) DeleteAllById params,
	                        @RequestParam(name = "id", required = false) Long id,
	                        @RequestParam(name = "firstName", required = false) String firstName,
	                        @RequestParam(name = "lastName", required = false) String lastName) {
		if (params != null &&
				id == null &&
				firstName == null &&
				lastName == null) {
			Iterator<Long> ids = Arrays.stream(params.ids()).iterator();
			doctorService.deleteAllById(new Iterable<Long>() {
				@Override
				public Iterator<Long> iterator() {
					return ids;
				}
			});
			return;
		}

		if (id != null && params == null &&
				firstName == null &&
				lastName == null) {
			doctorService.delete(id);
			return;
		}

		if ((firstName != null && lastName != null) &&
				id == null &&
				params == null ) {
			doctorService.delete(firstName, lastName);
			return;
		}

		throw new DoctorManagementException("Invalid params choose between id or (firstName && lastName) or RequestBody with ids:[]");

	}


}
