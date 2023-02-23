package com.endava.doctorsapi.tables.general.base;

import com.endava.doctorsapi.tables.general.EntityStates;
import com.endava.doctorsapi.tables.general.exceptions.ServiceException;


import java.util.List;

public class ServiceBase<Type extends EntityBase, ID, Repo extends RepoBase<Type, ID>> {
	protected final Repo repo;

	public ServiceBase(Repo repo) {
		this.repo = repo;
	}

	public Type get(ID id) {
		return repo.findById(id)
				.orElseThrow(() -> {
					throw new ServiceException(this, "id " + id + " not found");
				});
	}

	public List<Type> getAll() {
		return repo.findAll();
	}

	public void delete(ID id) {
		if (get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new ServiceException(this, "Cannot delete a doctor that is already in the state deleted");
		}
		repo.deleteById(id);
	}

	public void deleteAllById(Iterable<? extends ID> longs) {
		repo.deleteAllById(longs);
	}

	public void deleteAll() {
		repo.deleteAll();
	}
}
