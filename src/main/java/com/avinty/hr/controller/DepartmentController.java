package com.avinty.hr.controller;

import com.avinty.hr.model.Department;
import com.avinty.hr.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@RestController
@RequestMapping("${application-endpoint-url}" + "/department")
public class DepartmentController extends BaseControllerImp<Department, DepartmentService> {

	//---

}
