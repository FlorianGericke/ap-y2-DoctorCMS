package com.endava.doctorsapi.general.base;

import com.endava.doctorsapi.general.EntityStates;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public class BaseService<Type extends BaseEntity, Repo extends JpaRepository<Type, Long>> {
	protected final Repo repo;

	public BaseService(Repo repo) {
		this.repo = repo;
	}

	public Type get(long id) {
		return repo.findById(id)
				.orElseThrow(() -> {
					throw new ServiceException(this, "id " + id + " not found");
				});
	}

	public List<Type> getAll() {
		return repo.findAll();
	}

	public void delete(long id) {
		if (get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new ServiceException(this, "Cannot delete a doctor that is already in the state deleted");
		}
		repo.deleteById(id);
	}

	public void deleteAllById(Iterable<? extends Long> longs) {
		repo.deleteAllById(longs);
	}

	public void deleteAll() {
		repo.deleteAll();
	}
}
