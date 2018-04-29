package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Vendor;

import java.util.List;

public interface VendorService extends UserService<Vendor>{
    @Override
    List<Vendor> getAll();
}
