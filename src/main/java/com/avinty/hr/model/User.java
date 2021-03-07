package com.avinty.hr.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "hr_user_id_seq_gen", sequenceName = "hr_user_id_seq", allocationSize = 1)
@Table(name = "hr_user", schema = "public")
public class User extends BaseEntity {

	@NotNull
	@Column(unique = true, nullable = false)
	private String email;

	@NotNull
	@Column(length = 128, nullable = false)
	private String password;

	private String firstName;

	private String lastName;

	private Date birthDate;

	private String phone;

	private String address;

	private Date validUntil;

	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] profilePicture;

}
