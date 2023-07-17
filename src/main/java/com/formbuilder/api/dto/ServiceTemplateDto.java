package com.formbuilder.api.dto;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceTemplateDto {
	
	private String id;

	@NotBlank(message = "name is required.")
	private String name;

	private String description;
	
	private JsonNode fields;
}
