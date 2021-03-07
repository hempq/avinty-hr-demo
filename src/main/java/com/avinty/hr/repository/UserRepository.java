package com.avinty.hr.repository;

import com.avinty.hr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Szo
 **/
public interface UserRepository extends JpaRepository<User, Integer> {

	//---

}
