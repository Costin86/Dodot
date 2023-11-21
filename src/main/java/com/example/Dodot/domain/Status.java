package com.example.Dodot.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statusid;
    private String pcondition;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<Customer> customers;

    public Status() {}

    public Status(String pcondition) {
        this.pcondition = pcondition;
    }

    public Long getStatusid() {
        return statusid;
    }

    public void setStatusid(Long statusid) {
        this.statusid = statusid;
    }

    public String getPcondition() {
        return pcondition;
    }

    public void setPcondition(String pcondition) {
        this.pcondition = pcondition;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Status [statusid=" + statusid + ", Pcondition=" + pcondition + "]";
    }
}
