package com.service;


import java.util.List;

import com.entity.BankCustomer;

public interface BankCustomerService {
	
	public BankCustomer saveBankCustomer(BankCustomer bankCustomer);
	
	public List<BankCustomer> getAllBankCustomer();

}
