package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.repository.VendorRepository;
import edu.mum.cs490.project.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Service
public class VendorServiceImpl extends UserServiceImpl<Vendor> implements VendorService{

    protected final VendorRepository repository;

    @Autowired
    public VendorServiceImpl(VendorRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Vendor> find(String username, String companyName, Status status) {
        return repository.find(username, companyName, status);
    }
}
