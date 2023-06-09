package com.gradientbankapi.bankapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

//POJO - plain old java object
@Entity
public class Customer {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String first_name;
    @NotEmpty
    private String last_name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Address> address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_name = first_Name;
    }

    public String getLast_Name() {
        return last_name;
    }

    public void setLast_Name(String last_Name) {
        this.last_name = last_Name;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }
}
