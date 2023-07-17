package com.formbuilder.api.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "service")
@SQLDelete(sql = "UPDATE service SET status = 'DELETED' where id=?")
@Where(clause = "status != 'DELETED'")
public class ServiceTemplate extends BaseEntity{

	private static final long serialVersionUID = -4568599159227020860L;

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@Column(name="`id`")
	private String id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@JdbcTypeCode(value = SqlTypes.JSON)
	@Column(name = "fields",columnDefinition = "jsonb")
	private JsonNode fields;
	
	@OneToMany(mappedBy = "serviceTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitService> visitServiceList = new ArrayList<>();
}
