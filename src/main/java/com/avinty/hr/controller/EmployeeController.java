package com.avinty.hr.controller;

import com.avinty.hr.model.Employee;
import com.avinty.hr.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@RestController
@RequestMapping("${application-endpoint-url}" + "/employee")
public class EmployeeController extends BaseControllerImp<Employee, EmployeeService> {

	//---

}
