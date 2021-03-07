package com.avinty.hr.service;

import com.avinty.hr.model.Employee;
import com.avinty.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@Service
public class EmployeeService extends BaseServiceImpl<Employee, EmployeeRepository> {

	@Autowired
	private DepartmentService departmentService;

	public EmployeeService(EmployeeRepository repository) {
		super(repository);
	}

	@Override
	public Employee save(Employee employee) {
		if (employee.getDepartments() != null)
			employee.getDepartments().forEach(department ->
					employee.addDepartment(departmentService.findById(department.getId())));
		removeEmptyDepartments(employee);
		return super.save(employee);
	}

	@Override
	public Employee update(Integer id, Employee employee) {
		if (employee.getDepartments() != null)
			employee.getDepartments().forEach(department ->
					employee.addDepartment(departmentService.findById(department.getId())));
		removeEmptyDepartments(employee);
		return super.update(id, employee);
	}

	@Override
	public void delete(Integer id) {
		Employee byId = this.findById(id);
		departmentService.findByEmployee(byId).forEach(department -> {
			department.setEmployee(null);
			departmentService.update(department.getId(), department);
		});
		departmentService.findByManager(byId).forEach(department -> {
			department.setManager(null);
			departmentService.update(department.getId(), department);
		});
		super.delete(id);
	}

	private void removeEmptyDepartments(Employee employee) {
		if (employee.getDepartments() != null)
			employee.setDepartments(employee.getDepartments()
					.stream()
					.filter(department -> department.getName() != null)
					.collect(Collectors.toSet()));
	}

}
