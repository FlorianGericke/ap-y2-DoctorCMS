package com.endava.doctorsapi.tables.general;


import com.endava.doctorsapi.tables.address.AddressManagementException;


import java.util.List;

public class ServiceBase<Type extends EntityBase, Repo extends RepoBase<Type, Long>> {
	protected final Repo repo;

	public ServiceBase(Repo repo) {
		this.repo = repo;
	}

	public Type get(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> {
					throw new AddressManagementException("id not found");
				});
	}

	public List<Type> getAll() {
		return repo.findAll();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public void deleteAllById(Iterable<? extends Long> longs) {
		repo.deleteAllById(longs);
	}

	public void deleteAll() {
		repo.deleteAll();
	}
}
