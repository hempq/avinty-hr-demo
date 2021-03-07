package com.avinty.hr.controller;

import com.avinty.hr.model.BaseEntity;
import com.avinty.hr.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/

/**
 * @param <M> model
 * @param <S> service
 */
public class BaseControllerImp<M extends BaseEntity, S extends BaseService<M>> implements BaseController<M> {

	@Autowired
	protected S service;

	@Override
	public List<M> findAll() {
		return this.service.findAll();
	}

	@Override
	public M findById(@PathVariable final Integer id) {
		return this.service.findById(id);
	}

	@Override
	public M save(@RequestBody final M m) {
		return this.service.save(m);
	}

	@Override
	public M update(@PathVariable final Integer id, @RequestBody final M m) {
		return this.service.update(id, m);
	}

	@Override
	public ResponseEntity<String> delete(@PathVariable final Integer id) {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
