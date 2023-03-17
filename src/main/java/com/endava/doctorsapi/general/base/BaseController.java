package com.endava.doctorsapi.general.base;


import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class BaseController<Type extends BaseEntity,Service extends BaseService<Type, ?>> {
	protected final Service service;

	public BaseController(Service service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	protected Type onGet(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new ControllerException(this, "Invalid param id is null");
		}
		return service.get(id);
	}

	@GetMapping()
	protected List<Type> onGetAll() {
		return service.getAll();
	}

	@DeleteMapping("/{id}")
	protected void onDelete(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new ControllerException(this, "Invalid param id is null");
		}
		service.delete(id);
	}

	@DeleteMapping()
	protected void onDeleteAll(@RequestBody(required = false) Optional<DeleteAllById> params) {
		if (params.isPresent()) {
			Iterator<Long> ids = Arrays.stream(params.get().ids()).iterator();
			service.deleteAllById(() -> ids);
			return;
		}

		service.deleteAll();
	}
}
