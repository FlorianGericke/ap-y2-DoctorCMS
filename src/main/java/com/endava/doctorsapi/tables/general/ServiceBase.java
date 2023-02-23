package com.endava.doctorsapi.tables.general;


import com.endava.doctorsapi.tables.address.AddressManagementException;


import java.util.List;

public class ServiceBase<Type extends EntityBase,ID, Repo extends RepoBase<Type, ID>> {
	protected final Repo repo;

	public ServiceBase(Repo repo) {
		this.repo = repo;
	}

	public Type get(ID id) {
		return repo.findById(id)
				.orElseThrow(() -> {
					throw new AddressManagementException("id not found");
				});
	}

	public List<Type> getAll() {
		return repo.findAll();
	}

	public void delete(ID id) {
		repo.deleteById(id);
	}

	public void deleteAllById(Iterable<? extends ID> longs) {
		repo.deleteAllById(longs);
	}

	public void deleteAll() {
		repo.deleteAll();
	}
}
