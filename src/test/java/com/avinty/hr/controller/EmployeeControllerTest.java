package com.avinty.hr.controller;

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
 * The type Employee controller test.
 *
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Szo
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTest extends BaseTest{

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Value("${test-url}")
	private String endpointUrl;

	private String url;

	private Employee employee1;

	private Employee employee2;

	/**
	 * Before class.
	 */
	@BeforeAll
	void beforeClass() {
		this.url = this.endpointUrl + "/employee";
	}

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
	 * Find by id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void findById() throws Exception {
		final Employee dummyEmployee = employeeService.save(employee1);

		final MvcResult result =
				super.getMethod(url + "/" + dummyEmployee.getId());

		final Employee actualEmployee =
				this.objectMapper.readValue(
						result.getResponse().getContentAsString(), new TypeReference<>() {
						});

		Assertions.assertNotNull(actualEmployee);
	}

	/**
	 * Save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void save() throws Exception {
		final MvcResult result = super.postMethod(url, new JSONObject(employee1));

		final Employee actualEmployee =
				this.objectMapper.readValue(
						result.getResponse().getContentAsString(), new TypeReference<>() {
						});

		Assertions.assertNotNull(actualEmployee);
	}

	/**
	 * Update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void update() throws Exception {
		final Employee dummyEmployee = employeeService.save(employee1);
		employee2.setId(dummyEmployee.getId());

		final MvcResult result = super.putMethod(url + "/" + dummyEmployee.getId(), new JSONObject(employee2));

		final Employee actualEmployee =
				this.objectMapper.readValue(
						result.getResponse().getContentAsString(), new TypeReference<>() {
						});

		Assertions.assertNotNull(actualEmployee);
	}

	/**
	 * Delete.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void delete() throws Exception {
		final Employee dummyEmployee = employeeService.save(employee1);
		final MvcResult result = super.deleteMethod(url + "/" + dummyEmployee.getId());

		Assertions.assertEquals(result.getResponse().getStatus(), 200);
	}
}