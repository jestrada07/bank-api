package com.gradientbankapi.bankapi.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "first_Name")
    private String first_name;

    @NotEmpty
    @Column(name = "last_Name") //creates a column named last_name
    private String last_name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true) //deletes when not associated with a parent, prevents from infinite loop
    @JsonManagedReference
    private Set<Address> address = new HashSet<>(); //initialize the hashset - does not allow duplicates - allows fast lookups - does not maintain insertion order

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true) //deletes when not associated with a parent, prevents from infinite loop
    private Set<Account> accounts = new HashSet<>();

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_Name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }


//    public Set<Account> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(Set<Account> accounts) {
//        this.accounts = accounts;
//    }

}

