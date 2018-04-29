package edu.mum.cs490.project.model.form.user;

import edu.mum.cs490.project.domain.Vendor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by Erdenebayar on 4/21/2018
 */
public class VendorSignUpForm extends UserSignUpForm implements Serializable {

    @NotBlank
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
