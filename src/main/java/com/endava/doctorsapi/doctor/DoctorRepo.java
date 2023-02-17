package com.endava.doctorsapi.doctor;

import com.endava.doctorsapi.EntityStates;
import jakarta.persistence.PostRemove;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.parser.Entity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	@Override
	default void delete(Doctor entity) {
		if (entity.getDeletedAt() != null) {
			throw new DoctorManagementException("Cannot delete Doctor entity twice");
		}
		Method deleteMethod = Arrays.stream(
						entity.getClass()
								.getMethods())
				.filter(method -> method.isAnnotationPresent(PostRemove.class))
				.findFirst()
				.orElse(null);

		if (deleteMethod == null) {
			throw new DoctorManagementException("You need to provide a method wit Annotation @PostRemove");
		}

		try {
			deleteMethod.invoke(entity);
			this.save(entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new DoctorManagementException("[" + e.getClass().getName() + "] :" + e.getMessage());
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
	default void deleteById(Long aLong) {
		this.delete(this.findAll()
				.stream()
				.filter(entity -> entity.getId().equals(aLong))
				.findFirst()
				.orElseThrow(() -> {
					throw new DoctorManagementException("id not found");
				})
		);
	}

	@Override
	default void deleteAllById(Iterable<? extends Long> longs) {
		longs.forEach(this::deleteById);
	}
}
