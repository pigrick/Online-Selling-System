package edu.mum.cs490.project.service;

import java.util.List;

import org.springframework.transaction.annotation.*;



@Transactional
public interface ReportService {
	public List<Object> getReportResultList();

}
