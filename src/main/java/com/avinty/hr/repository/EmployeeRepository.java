package com.avinty.hr.repository;

import com.avinty.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//---

}
