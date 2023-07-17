package com.formbuilder.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formbuilder.api.entity.VisitService;

public interface VisitServiceRepository extends JpaRepository<VisitService, String> {

	List<VisitService> findByVisitIdAndServiceTemplate_Id(String visitId, String serviceId);

	VisitService findOneByVisitIdAndServiceTemplate_Id(String visitId, String serviceId);

	List<VisitService>  findByVisitId(String visitId);

}
