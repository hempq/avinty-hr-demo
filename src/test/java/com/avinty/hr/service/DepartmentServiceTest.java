package com.avinty.hr.service;

import com.avinty.hr.exception.NotFoundException;
import com.avinty.hr.model.Department;
import com.avinty.hr.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Department service test.
 *
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Szo
 */
@Slf4j
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	private Department department1;

	private Department department2;

	/**
	 * Sets data.
	 */
	@BeforeEach
	void setupData() {
		Employee employee = Employee.builder()
				.email("test")
				.password("test")
				.build();

		Employee save = employeeService.save(employee);

		department1 = Department.builder()
				.name("test1")
				.employee(Employee.builder().id(save.getId()).build())
				.manager(Employee.builder().id(save.getId()).build())
				.build();

		department2 = Department.builder()
				.name("test2")
				.employee(Employee.builder().id(save.getId()).build())
				.manager(Employee.builder().id(save.getId()).build())
				.build();
	}

	/**
	 * Find all.
	 */
	@Test
	void findAll() {
		departmentService.save(department1);
		List<Department> all = departmentService.findAll();
		Assertions.assertNotNull(all);
	}

	/**
	 * Find by id.
	 */
	@Test
	void findById() {
		Department department = departmentService.save(department1);
		Department byId = departmentService.findById(department.getId());
		Assertions.assertNotNull(byId);
	}

	/**
	 * Save.
	 */
	@Test
	void save() {
		Assertions.assertNotNull(departmentService.save(department1));
	}

	/**
	 * Update.
	 */
	@Test
	void update() {
		Department department = departmentService.save(department1);
		department2.setId(department.getId());
		Assertions.assertNotNull(departmentService.update(department.getId(),department2));
	}

	/**
	 * Delete.
	 */
	@Test
	void delete() {
		Department department = departmentService.save(department1);
		departmentService.delete(department.getId());
		Assertions.assertThrows(NotFoundException.class, () -> departmentService.findById(department.getId()));
	}
}