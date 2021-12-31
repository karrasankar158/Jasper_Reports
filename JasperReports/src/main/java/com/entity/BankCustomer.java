package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_customer_tab")
public class BankCustomer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bank_customer_id_col")
	private Integer id;
	
	@Column(name = "bank_customer_name_col")
	private String name;

}
