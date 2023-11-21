package com.example.Dodot.domain;


import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
    List<Customer> findByStatusPcondition(String pcondition);
    List<Customer> findByStatus(com.example.Dodot.domain.Status status);
    List<Customer> findAll(Sort sort);
}


