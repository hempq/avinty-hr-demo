package com.avinty.hr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "department_id_seq_gen", sequenceName = "department_id_seq", allocationSize = 1)
@Table(name = "department", schema = "public")
public class Department extends BaseEntity {

	@NotNull
	@Column(unique = true, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	@JsonIgnoreProperties("departments")
	private Employee manager;


	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private Employee employee;

	public void setEmployee(Employee employee) {
		if (sameAsFormer(employee))
			return;
		Employee oldEmployee = this.employee;
		this.employee = employee;
		if (oldEmployee != null)
			oldEmployee.removeDepartment(this);
		if (employee != null)
			employee.addDepartment(this);
	}

	private boolean sameAsFormer(Employee newEmployee) {
		return Objects.equals(employee, newEmployee);
	}

}
