package com.endava.doctorsapi.tables.general.base;

import com.endava.doctorsapi.tables.general.EntityStates;
import com.endava.doctorsapi.tables.general.exceptions.CmsException;
import jakarta.persistence.PostRemove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public interface RepoBase<Entity extends EntityBase, Id> extends JpaRepository<Entity, Id> {


	@Override
	default void delete(Entity entity) {
		if (entity.getDeletedAt() != null) {
			throw new CmsException("Cannot delete Doctor entity twice");
		}
		Method deleteMethod = Arrays.stream(
						entity.getClass()
								.getMethods())
				.filter(method -> method.isAnnotationPresent(PostRemove.class))
				.findFirst()
				.orElse(null);

		if (deleteMethod == null) {
			throw new CmsException("You need to provide a method wit Annotation @PostRemove");
		}

		try {
			deleteMethod.invoke(entity);
			this.save(entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CmsException("[" + e.getClass().getName() + "] :" + e.getMessage());
		}
	}

	@Override
	default void deleteAll() {
		this.findAll()
				.stream()
				.filter(entity -> !entity.getState().equals(EntityStates.DELETED.toString()))
				.forEach(this::delete);
	}

	@Override
	default void deleteById(Id id) {
		this.delete(this.findAll()
				.stream()
				.filter(entity -> entity.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> {
					throw new CmsException("id not found");
				})
		);
	}

	@Override
	default void deleteAllById(Iterable<? extends Id> ids) {
		ids.forEach(this::deleteById);
	}
}
