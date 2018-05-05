package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;

import java.util.List;

public interface VendorService extends UserService<Vendor>{

    List<Vendor> find(String username, String companyName, Status status);

    Vendor getByCompanyName(String companyName);
}
