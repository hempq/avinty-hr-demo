package com.avinty.hr.service;

import com.avinty.hr.exception.NotFoundException;
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
 * The type Employee service test.
 *
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Szo
 */
@Slf4j
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	private Employee employee1;

	private Employee employee2;

	/**
	 * Sets data.
	 */
	@BeforeEach
	void setupData() {
		employee1 = Employee.builder()
				.email("test1")
				.password("test1")
				.build();

		employee2 = Employee.builder()
				.email("test2")
				.password("test2")
				.build();
	}

	/**
	 * Find all.
	 */
	@Test
	void findAll() {
		employeeService.save(employee1);
		List<Employee> all = employeeService.findAll();
		Assertions.assertNotNull(all);
	}

	/**
	 * Find by id.
	 */
	@Test
	void findById() {
		Employee employee = employeeService.save(employee1);
		Employee byId = employeeService.findById(employee.getId());
		Assertions.assertNotNull(byId);
	}

	/**
	 * Save.
	 */
	@Test
	void save() {
		Assertions.assertNotNull(employeeService.save(employee1));
	}

	/**
	 * Update.
	 */
	@Test
	void update() {
		Employee employee = employeeService.save(employee1);
		employee2.setId(employee.getId());
		Assertions.assertNotNull(employeeService.update(employee.getId(),employee2));
	}

	/**
	 * Delete.
	 */
	@Test
	void delete() {
		Employee employee = employeeService.save(employee1);
		employeeService.delete(employee.getId());
		Assertions.assertThrows(NotFoundException.class, () -> employeeService.findById(employee.getId()));

	}
}