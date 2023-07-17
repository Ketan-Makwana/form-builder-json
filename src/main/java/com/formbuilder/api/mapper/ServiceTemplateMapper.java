package com.formbuilder.api.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.formbuilder.api.dto.ServiceTemplateDto;
import com.formbuilder.api.entity.ServiceTemplate;
import com.formbuilder.api.entity.VisitService;

@Mapper(
		componentModel = SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		builder = @Builder(disableBuilder = true),
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
		)
public interface ServiceTemplateMapper {

	@Mapping(target = "id", ignore = true)
	public ServiceTemplate toServiceTemplate(ServiceTemplateDto serviceTemplateDto);
	
	@Mapping(target = "id", ignore = true)
	public ServiceTemplate toServiceTemplate(@MappingTarget ServiceTemplate serviceTemplate, ServiceTemplateDto serviceTemplateDto);

	public ServiceTemplateDto toServiceTemplateDto(ServiceTemplate serviceTemplate);
	
	public List<ServiceTemplateDto> toServiceTemplateDtoList(List<ServiceTemplate> serviceTemplateList);
}
