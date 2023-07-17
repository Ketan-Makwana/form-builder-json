package com.formbuilder.api.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.formbuilder.api.dto.VisitServiceDto;
import com.formbuilder.api.entity.ServiceTemplate;
import com.formbuilder.api.entity.VisitService;
import com.formbuilder.api.enumbartions.Status;
import com.formbuilder.api.mapper.VisitServiceMapper;
import com.formbuilder.api.repository.VisitServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitServiceService {
	
	private final VisitServiceRepository visitServiceRepository;
	
	private final VisitServiceMapper visitServiceMapper;
	
	private final ServiceTemplateService serviceTemplateService;
	
	public VisitServiceDto createVisitService(VisitServiceDto visitServiceDto) {
		ServiceTemplate serviceTemplateFromDb = serviceTemplateService.getServiceTemplateByIdFromDb(visitServiceDto.getServiceTemplateId());

		if(CollectionUtils.isNotEmpty(visitServiceRepository.findByVisitIdAndServiceTemplate_Id(visitServiceDto.getVisitId(), visitServiceDto.getServiceTemplateId()))) {
			throw new RuntimeException("Visit-service already exists");
		}
		
		VisitService saveVisitService = visitServiceMapper.toVisitService(visitServiceDto);
		saveVisitService.setServiceTemplate(serviceTemplateFromDb);
		
		visitServiceRepository.save(saveVisitService);
		return visitServiceMapper.toVisitServiceDto(saveVisitService);
	}
	
	public VisitService getVisitServiceByIdFromDb(String visitServiceId) {
		
		VisitService updateVisitService = visitServiceRepository.findById(visitServiceId).orElseThrow(() -> {
			throw new RuntimeException("Visit service not found");
		});
		
		return updateVisitService;
	}
	
	public VisitServiceDto updateVisitService(String visitServiceId, JsonNode fields) {
		
		VisitService updateVisitService = getVisitServiceByIdFromDb(visitServiceId);
		
		updateVisitService.setFields(fields);
		
		visitServiceRepository.save(updateVisitService);
		updateVisitService.getServiceTemplate().setStatus(Status.ACTIVE);
		System.err.println(updateVisitService.getServiceTemplate());
		return visitServiceMapper.toVisitServiceDto(updateVisitService);
	}
	
	public VisitServiceDto getVisitServiceById(String visitServiceId) {

		VisitService visitServiceFromDb = getVisitServiceByIdFromDb(visitServiceId);
		
		return visitServiceMapper.toVisitServiceDto(visitServiceFromDb);
	}

	public List<VisitServiceDto> getVisitServiceByVisitId(String visitId) {
		List<VisitService> visitsFromDb = visitServiceRepository.findByVisitId(visitId);
		return visitServiceMapper.toVisitServiceDtoList(visitsFromDb);
	}
}
