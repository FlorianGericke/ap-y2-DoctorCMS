package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.tables.general.base.ControllerBase;
import com.endava.doctorsapi.tables.general.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController extends ControllerBase<Doctor, DoctorRepo, DoctorService> {

	@Autowired
	public DoctorController(DoctorService doctorService) {
		super(doctorService);
	}

	@PostMapping()
	public void onPost(@RequestBody(required = false) Optional<Doctor> doc,
	                   @RequestParam(name = "firstname", required = false) Optional<String> firstname,
	                   @RequestParam(name = "lastname", required = false) Optional<String> lastname) {

		validateAndDo(doc, firstname, lastname, service::postDoctor);
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id,
	                  @RequestBody(required = false) Optional<Doctor> doc,
	                  @RequestParam(name = "firstname", required = false) Optional<String> firstname,
	                  @RequestParam(name = "lastname", required = false) Optional<String> lastname) {

		validateAndDo(doc, firstname, lastname, doctor -> service.put(id, doctor));
	}

	private void validateAndDo(Optional<Doctor> doc,
	                           Optional<String> firstname,
	                           Optional<String> lastname,
	                           Consumer<Doctor> resolve) {

		if (doc.isPresent() &&
				firstname.isEmpty() &&
				lastname.isEmpty()
		) {
			if ((doc.get().getFirstName() == null || doc.get().getFirstName().length() < 3) ||
					(doc.get().getLastName() == null || doc.get().getLastName().length() < 3)
			) {
				throw new ControllerException(this, "Please provide a first name and last name (minimum 3 chars)");
			}

			resolve.accept(doc.get());
			return;
		}

		if (doc.isEmpty() &&
				firstname.isPresent() &&
				lastname.isPresent()
		) {
			if (firstname.get().length() < 3 ||
					lastname.get().length() < 3
			) {
				throw new ControllerException(this, "Please provide a first name and last name (minimum 3 chars)");
			}

			resolve.accept(new Doctor(firstname.get(), lastname.get()));
			return;
		}

		throw new ControllerException(this, "Please provide a RequestBody firstname and lastname or Query Params with them");
	}
}
