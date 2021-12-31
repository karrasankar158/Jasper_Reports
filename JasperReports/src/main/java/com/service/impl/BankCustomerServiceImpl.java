package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.BankCustomer;
import com.repository.BankCustomerRepository;
import com.service.BankCustomerService;

@Service
public class BankCustomerServiceImpl implements BankCustomerService {
	
	@Autowired
	private BankCustomerRepository repository;
	

	@Override
	public BankCustomer saveBankCustomer(BankCustomer bankCustomer) {
		return repository.save(bankCustomer);
	}

	@Override
	public List<BankCustomer> getAllBankCustomer() {
		return  repository.findAll();
	}
	
	

}
