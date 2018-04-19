package edu.mum.cs490.project.domain;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {
    private String firstName;
    private String lastName;

    public Administrator(){
        super();
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
