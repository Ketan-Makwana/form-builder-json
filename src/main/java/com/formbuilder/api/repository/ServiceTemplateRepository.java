package com.formbuilder.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formbuilder.api.entity.ServiceTemplate;
import com.formbuilder.api.enumbartions.Status;

public interface ServiceTemplateRepository extends JpaRepository<ServiceTemplate, String> {

	List<ServiceTemplate> findByName(String name);
	
	List<ServiceTemplate> findByIdNotAndName(String id, String name);
	
}
