package com.endava.doctorsapi.general.base;


import com.endava.doctorsapi.general.exceptions.CmsException;
import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BaseControllerClone<Type extends BaseEntity, Service extends BaseService<Type, ?>, Response, Mapper extends Function<Type, Response>> {
	protected final Service service;

	protected final Mapper mapper;

	public BaseControllerClone(Service service, Mapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("/{id}")
	protected ResponseEntity<Response> onGet(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}
		return new ResponseEntity<>(mapper.apply(service.get(id)), HttpStatus.OK) ;
	}

	@GetMapping()
	protected  ResponseEntity<List<Response>> onGetAll() {
		return new ResponseEntity<>(service.getAll().stream().map(mapper).toList(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	protected ResponseEntity<Response> onDelete(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}
		return new ResponseEntity<>(mapper.apply(service.delete(id)), HttpStatus.OK);
	}

	@DeleteMapping()
	protected ResponseEntity<List<Response>> onDeleteAll(@RequestBody(required = false) Optional<DeleteAllById> params) {
		if (params.isPresent()) {
			Iterator<Long> ids = Arrays.stream(params.get().ids()).iterator();
			return new ResponseEntity<>(service.deleteAllById(() -> ids).stream().map(mapper).toList(),HttpStatus.OK);
		}

		return new ResponseEntity<>(service.deleteAll().stream().map(mapper).toList(),HttpStatus.OK);
	}
}
