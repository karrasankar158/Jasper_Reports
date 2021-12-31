package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.entity.BankCustomer;
import com.service.BankCustomerService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class JasperReportUtil {

	@Autowired
	private BankCustomerService service;

	//public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

	public ResponseEntity<byte[]> exportReport(String reportFormat) throws FileNotFoundException, JRException {

		byte[] data=null;
		HttpHeaders headers=null;

		//set title
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("title","Full Stack Developer");

		//retrieve list of all BankCustomer data
		List<BankCustomer> bank=service.getAllBankCustomer(); 
		/*
		 * //Load file and compile it
		 * file=ResourceUtils.getFile("Specialization.jrxml"); 
		 */

		//get list of all BankCustomer data as a constructor parameter. 
		JRBeanCollectionDataSource listOfBankCustomers=new JRBeanCollectionDataSource(bank);

		//locating and compiling jasper .jrxml file
		//JasperReport compiledJasperReport=JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/bank.jrxml"));

		//JasperReport compiledJasperReport=JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/bank12.jrxml"));

		JasperReport compiledJasperReport=JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/bank15.jrxml"));

		//supplied list of BankCustomer  objects
		JasperPrint jasperPrint=JasperFillManager.fillReport(compiledJasperReport,parameters, listOfBankCustomers);

		//checking which type of report is generating...
		if(reportFormat.equalsIgnoreCase("html")) {

			//local report generation as a HTML
			JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:\\Users\\Sankar Karra\\Desktop\\Project1"+"//bank.html");

		}
		if(reportFormat.equalsIgnoreCase("pdf")) {

			//local report generation as a PDF
			//JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Sankar Karra\\Desktop\\Project1"+"//bank.pdf");

			data=JasperExportManager.exportReportToPdf(jasperPrint);

			//CONTENT-DISPOSITION is displaying PDF report in browser
			headers=new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=BankCustomer.pdf");

			//headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=BankCustomer.pdf");
		}
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}


}
