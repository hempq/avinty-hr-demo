package com.avinty.hr.service;

import java.util.List;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
/**
 * @param <M> model
 */
public interface BaseService<M> {

    List<M> findAll();

	M findById(Integer id);

    M save(M m);

    M update(Integer id, M m);

    void delete(Integer id);
}
