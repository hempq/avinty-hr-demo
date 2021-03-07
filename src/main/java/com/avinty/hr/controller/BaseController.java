package com.avinty.hr.controller;

import java.util.List;

import com.avinty.hr.model.BaseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/

/**
 * @param <M> model
 */
public interface BaseController<M extends BaseEntity> {

	@GetMapping(value = "")
	List<M> findAll();

	@GetMapping(value = "/{id}")
	M findById(@PathVariable Integer id);

	@PostMapping(
			value = "",
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	M save(@RequestBody M m);

	@PutMapping(
			value = "/{id}",
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	M update(@PathVariable Integer id, @RequestBody M m);

	@DeleteMapping(value = "/{id}")
	ResponseEntity<String> delete(@PathVariable Integer id);

}
