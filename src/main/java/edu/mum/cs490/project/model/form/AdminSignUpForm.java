package edu.mum.cs490.project.model.form;

import edu.mum.cs490.project.domain.Admin;

import java.io.Serializable;

/**
 * Created by Erdenebayar on 4/21/2018
 */
public class AdminSignUpForm extends UserSignUpForm implements Serializable {

    private String firstName;
    private String lastName;

    public AdminSignUpForm() {
    }

    public AdminSignUpForm(Admin admin) {
        super(admin);
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
