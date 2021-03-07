package com.avinty.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Temporal(value = TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date createdAt;

	@CreatedBy
	@Column(name = "created_by")
	private Integer createdBy;

	@Temporal(value = TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "updated_on")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date updatedOn;

	@LastModifiedBy
	@Column(name = "updated_by")
	private Integer updatedBy;

	@PrePersist
	public void prePersist() {
		this.createdAt = new Date(System.currentTimeMillis());
		//auth-al itt lehetne set-elni a usert
		this.createdBy = 1;
		this.updatedOn = new Date(System.currentTimeMillis());
		this.updatedBy = 1;
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedOn = new Date(System.currentTimeMillis());
		this.updatedBy = 1;
	}
}
