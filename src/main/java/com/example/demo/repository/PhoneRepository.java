package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Phones;

public interface PhoneRepository extends CrudRepository<Phones, Long> {

}
