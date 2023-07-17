package com.formbuilder.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formbuilder.api.dto.ServiceTemplateDto;
import com.formbuilder.api.service.ServiceTemplateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ServiceTemplateController {

	private final ServiceTemplateService serviceTemplateService;

	@PostMapping("/service-templates")
	public ResponseEntity<ServiceTemplateDto> createServiceTemplate(@RequestBody @Valid ServiceTemplateDto serviceTemplateDto){
		return ResponseEntity.ok(serviceTemplateService.createServiceTemplate(serviceTemplateDto));
	}
	
	@PutMapping("/service-templates/{serviceTemplateId}")
	public ResponseEntity<ServiceTemplateDto> createServiceTemplate(@RequestBody @Valid ServiceTemplateDto serviceTemplateDto,
			@PathVariable String serviceTemplateId){
		return ResponseEntity.ok(serviceTemplateService.updateServiceTemplate(serviceTemplateId, serviceTemplateDto));
	}
	
	@GetMapping("/service-templates/{serviceTemplateId}")
	public ResponseEntity<ServiceTemplateDto> getServiceTemplateById(@PathVariable String serviceTemplateId){
		return ResponseEntity.ok(serviceTemplateService.getServiceTemplateById(serviceTemplateId));
	}

	@GetMapping("/service-templates")
	public ResponseEntity<List<ServiceTemplateDto>> getAllServiceTemplate(){
		return ResponseEntity.ok(serviceTemplateService.getAllServiceTemplate());
	}
	
}
