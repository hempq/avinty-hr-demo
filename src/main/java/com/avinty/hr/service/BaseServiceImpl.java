package com.avinty.hr.service;

import com.avinty.hr.exception.GeneralException;
import com.avinty.hr.exception.IdMismatchException;
import com.avinty.hr.exception.MissingIdException;
import com.avinty.hr.exception.NotFoundException;
import com.avinty.hr.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
/**
 * @param <M> model
 * @param <R> repository
 */
@Transactional
@RequiredArgsConstructor
public class BaseServiceImpl<M extends BaseEntity, R extends JpaRepository<M, Integer>> implements BaseService<M> {

	protected final R repository;

	@Override
	public List<M> findAll() {
		return this.repository.findAll();
	}

	@Override
	public M findById(final Integer id) {
		return this.repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found with id " + id));
	}

	@Override
	public M save(final M m) {
		return this.repository.save(m);
	}

	@Override
	public M update(final Integer id, final M m) {

		if (m == null) {
			throw new GeneralException("Missing object!");
		}

		if (id == null) {
			throw new MissingIdException("Missing id!");
		}

		if (m.getId() == null) {
			throw new MissingIdException("Missing id!");
		}

		if (!m.getId().equals(id)) {
			throw new IdMismatchException("Id does not match with the given one!");
		}

		m.setCreatedBy(this.findById(m.getId()).getCreatedBy());
		m.setCreatedAt(this.findById(m.getId()).getCreatedAt());

		return this.repository.save(m);
	}

	@Override
	public void delete(final Integer id) {

		if (id == null) {
			throw new MissingIdException("Missing id!");
		}

		final M m = this.findById(id);

		this.repository.delete(m);
	}

}
