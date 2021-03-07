package com.avinty.hr.controller;

import com.avinty.hr.model.Department;
import com.avinty.hr.model.Employee;
import com.avinty.hr.service.DepartmentService;
import com.avinty.hr.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Department controller test.
 *
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Szo
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentControllerTest extends BaseTest{

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;


	@Value("${test-url}")
	private String endpointUrl;

	private String url;

	private Department department1;

	private Department department2;

	/**
	 * Before class.
	 */
	@BeforeAll
	void beforeClass() {
		this.url = this.endpointUrl + "/department";
	}

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
	 * Find by id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void findById() throws Exception {
		final Department dummyDepartment = departmentService.save(department1);

		final MvcResult result =
				super.getMethod(url + "/" + dummyDepartment.getId());

		final Department actualDepartment =
				this.objectMapper.readValue(
						result.getResponse().getContentAsString(), new TypeReference<>() {
						});

		Assertions.assertNotNull(actualDepartment);
	}

	/**
	 * Save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void save() throws Exception {
		final MvcResult result = super.postMethod(url, new JSONObject(department1));

		final Department actualDepartment =
				this.objectMapper.readValue(
						result.getResponse().getContentAsString(), new TypeReference<>() {
						});

		Assertions.assertNotNull(actualDepartment);
	}

	/**
	 * Update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void update() throws Exception {
		final Department dummyDepartment = departmentService.save(department1);
		department2.setId(dummyDepartment.getId());

		final MvcResult result = super.putMethod(url + "/" + dummyDepartment.getId(), new JSONObject(department2));

		final Department actualDepartment =
				this.objectMapper.readValue(
						result.getResponse().getContentAsString(), new TypeReference<>() {
						});

		Assertions.assertNotNull(actualDepartment);
	}

	/**
	 * Delete.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void delete() throws Exception {
		final Department dummyDepartment = departmentService.save(department1);
		final MvcResult result = super.deleteMethod(url + "/" + dummyDepartment.getId());

		Assertions.assertEquals(result.getResponse().getStatus(), 200);
	}
}