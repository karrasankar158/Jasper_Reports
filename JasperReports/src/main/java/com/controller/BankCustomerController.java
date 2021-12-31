package com.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.BankCustomer;
import com.service.BankCustomerService;
import com.util.JasperReportUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/bankCustomer")
public class BankCustomerController {

	@Autowired
	private BankCustomerService service;

	@Autowired
	private JasperReportUtil util;

	/**
	 * 
	         http://localhost:9901/bankCustomer/save

	         request data:

	             {
	                     "name" : "Sankar"
	            }
	 * @param bankCustomer
	 * @return
	 */
	@PostMapping("/save")
	public BankCustomer saveBankCustomer(@RequestBody BankCustomer bankCustomer) {

		return service.saveBankCustomer(bankCustomer);
	}


	//	//http://localhost:9901/bankCustomer/show
	@GetMapping("/show")
	public List<BankCustomer> getAllBankCustomer() {

		return service.getAllBankCustomer();
	}

	@GetMapping("/report/{format}")
	public ResponseEntity<byte[]> generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return util.exportReport(format);
	}

}
