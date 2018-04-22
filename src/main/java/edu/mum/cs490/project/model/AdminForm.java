package edu.mum.cs490.project.model;

import edu.mum.cs490.project.domain.Admin;

import java.io.Serializable;

/**
 * Created by Erdenebayar on 4/21/2018
 */
public class AdminForm extends UserForm implements Serializable {

    private String firstName;
    private String lastName;

    public AdminForm(Admin admin) {
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
