package com.avinty.hr.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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
@SequenceGenerator(name = "employee_id_seq_gen", sequenceName = "employee_id_seq", allocationSize = 1)
@Table(name = "employee", schema = "public")
public class Employee extends BaseEntity {

	@NotNull
	@Column(unique = true, nullable = false)
	private String email;

	@NotNull
	@Column(length = 66, nullable = false)
	private String password;

	@OneToMany(mappedBy = "employee")
	private Collection<Department> departments = new ArrayList<>();

	public Collection<Department> getDepartments() {
		if (departments != null) return new ArrayList<>(departments);
		return null;
	}

	public void addDepartment(Department department) {
		if (departments.contains(department))
			return;
		departments.add(department);
		department.setEmployee(this);
	}

	public void removeDepartment(Department department) {
		if (!departments.contains(department))
			return;
		departments.remove(department);
		department.setEmployee(null);
	}

}
