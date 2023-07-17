package com.formbuilder.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.formbuilder.api.dto.VisitServiceDto;
import com.formbuilder.api.service.VisitServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class VisitServiceController {

	private final VisitServiceService visitServiceService;

	@PostMapping("/visit-services")
	public ResponseEntity<VisitServiceDto> createVisitService( @RequestBody VisitServiceDto visitServiceDto){
		return ResponseEntity.ok(visitServiceService.createVisitService(visitServiceDto));
	}
	
	@PutMapping("/visit-services/{visitServiceId}")
	public ResponseEntity<VisitServiceDto> updateVisitService(@RequestBody JsonNode fields,
			@PathVariable String visitServiceId){

		return ResponseEntity.ok(visitServiceService.updateVisitService(visitServiceId, fields));
	}
	
	@GetMapping("/visit-services/{visitServiceId}")
	public ResponseEntity<VisitServiceDto> getVisitServiceById(@PathVariable String visitServiceId){
		return ResponseEntity.ok(visitServiceService.getVisitServiceById(visitServiceId));
	}
	
	@GetMapping("/visit-services")
	public ResponseEntity<List<VisitServiceDto>> getVisitServiceByVisitId(@RequestParam String visitId){
		return ResponseEntity.ok(visitServiceService.getVisitServiceByVisitId(visitId));
	}
}
