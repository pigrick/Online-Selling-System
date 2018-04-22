package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Admin;
import edu.mum.cs490.project.repository.AdminRepository;
import edu.mum.cs490.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }
}
