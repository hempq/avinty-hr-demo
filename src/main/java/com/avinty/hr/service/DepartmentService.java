package com.avinty.hr.service;

import com.avinty.hr.model.Department;
import com.avinty.hr.model.Employee;
import com.avinty.hr.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@Service
public class DepartmentService extends BaseServiceImpl<Department, DepartmentRepository> {

	public DepartmentService(DepartmentRepository repository) {
		super(repository);
	}

	List<Department> findByEmployee(Employee employee) {
		return repository.findByEmployee(employee);
	}

	List<Department> findByManager(Employee employee) {
		return repository.findByManager(employee);
	}
}
