package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Admin;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AdminService{

    public List<Admin> getAll();
}
