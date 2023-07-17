package com.formbuilder.api.dto;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
public class VisitServiceDto {
	
	private String id;

	private String visitId;

	private String serviceTemplateId;

	private String serviceTemplateName;

	private String serviceTemplateDescription;
	
	private JsonNode fields;
}
