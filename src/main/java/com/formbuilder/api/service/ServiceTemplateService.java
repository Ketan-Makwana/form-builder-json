package com.formbuilder.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.formbuilder.api.dto.ServiceTemplateDto;
import com.formbuilder.api.entity.ServiceTemplate;
import com.formbuilder.api.enumbartions.Status;
import com.formbuilder.api.mapper.ServiceTemplateMapper;
import com.formbuilder.api.repository.ServiceTemplateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceTemplateService {
	
	private final ServiceTemplateRepository serviceTemplateRepository;
	
	private final ServiceTemplateMapper serviceTemplateMapper;
	
	public ServiceTemplate getServiceTemplateByIdFromDb(String id) {
		
		ServiceTemplate serviceTemplateFromDb = serviceTemplateRepository.findById(id).orElseThrow(() -> {
			throw new RuntimeException("Service with id not found");
		});
		if(!serviceTemplateFromDb.getStatus().equals(Status.ACTIVE)) {
			throw new RuntimeException("Service with id not found");
		}
		return serviceTemplateFromDb;
	}
	
	public ServiceTemplateDto createServiceTemplate(ServiceTemplateDto serviceTemplateDto) {
		if(CollectionUtils.isNotEmpty(serviceTemplateRepository.findByName(serviceTemplateDto.getName()))) {
				throw new RuntimeException("Service with name already exists");
		}
		ServiceTemplate saveServiceTemplate = serviceTemplateMapper.toServiceTemplate(serviceTemplateDto);
		
		serviceTemplateRepository.save(saveServiceTemplate);
		return serviceTemplateMapper.toServiceTemplateDto(saveServiceTemplate);
	}
	
	public ServiceTemplateDto updateServiceTemplate(String id, ServiceTemplateDto serviceTemplateDto) {
		List<ServiceTemplate> saveOrUpdateServiceTemplateList = new ArrayList<>();
		
		ServiceTemplate serviceTemplateFromDb = getServiceTemplateByIdFromDb(id);
		
		if(CollectionUtils.isNotEmpty(serviceTemplateRepository.findByIdNotAndName(id, serviceTemplateDto.getName()))) {
				throw new RuntimeException("Service with name already exists");
		}
		
		ServiceTemplate saveServiceTemplate = null;
		if(CollectionUtils.isEmpty(serviceTemplateFromDb.getVisitServiceList())) {
			
			serviceTemplateMapper.toServiceTemplate(serviceTemplateFromDb, serviceTemplateDto);
			
		}else {
			saveServiceTemplate = serviceTemplateMapper.toServiceTemplate(serviceTemplateDto);
			if(!saveServiceTemplate.getName().equals(serviceTemplateFromDb.getName()) 
					|| !saveServiceTemplate.getDescription().equals(serviceTemplateFromDb.getDescription())
					|| !saveServiceTemplate.getFields().equals(serviceTemplateFromDb.getFields())) {
				saveOrUpdateServiceTemplateList.add(saveServiceTemplate);
				serviceTemplateFromDb.setStatus(Status.INACTIVE);
			}
		}

		saveOrUpdateServiceTemplateList.add(serviceTemplateFromDb);
		
		serviceTemplateRepository.saveAll(saveOrUpdateServiceTemplateList);
		
		if(ObjectUtils.isNotEmpty(saveServiceTemplate)) {
			return serviceTemplateMapper.toServiceTemplateDto(saveServiceTemplate);
		}else {
			return serviceTemplateMapper.toServiceTemplateDto(serviceTemplateFromDb);
		}
	}
	
	public ServiceTemplateDto getServiceTemplateById(String id) {
		
		return serviceTemplateMapper.toServiceTemplateDto(getServiceTemplateByIdFromDb(id));
	}
	
	public List<ServiceTemplateDto> getAllServiceTemplate() {
		
		return serviceTemplateMapper.toServiceTemplateDtoList(serviceTemplateRepository.findAll());
	}
}
