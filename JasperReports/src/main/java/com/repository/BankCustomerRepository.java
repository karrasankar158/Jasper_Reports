package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BankCustomer;

public interface BankCustomerRepository extends JpaRepository<BankCustomer,Integer> {

}
