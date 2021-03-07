package com.avinty.hr.repository;

import com.avinty.hr.model.Department;
import com.avinty.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findByEmployee(Employee employee);

	List<Department> findByManager(Employee employee);

	//---

}
