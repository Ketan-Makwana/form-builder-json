package com.formbuilder.api.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.formbuilder.api.dto.VisitServiceDto;
import com.formbuilder.api.entity.ServiceTemplate;
import com.formbuilder.api.entity.VisitService;

@Mapper(
		componentModel = SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		builder = @Builder(disableBuilder = true),
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
		)
public interface VisitServiceMapper {

	@Mapping(target = "id", ignore = true)
	public VisitService toVisitService(VisitServiceDto visitServiceDto);

	@Mapping(target = "serviceTemplateId", source = "serviceTemplate.id")
	@Mapping(target = "serviceTemplateName", source = "serviceTemplate.name")
	@Mapping(target = "serviceTemplateDescription", source = "serviceTemplate.description")
	public VisitServiceDto toVisitServiceDto(VisitService visitService);
	
	public List<VisitServiceDto> toVisitServiceDtoList(List<VisitService> visitServiceList);
}
