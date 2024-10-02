package com.org.expenseapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTIONS")
@Data
public class Transactions
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "transaction_name")
	private String     transactionName;
	@Column(name = "transaction_type")
	private String     transactionType;
	@Column(name = "transaction_date")
	private String     transactionDate;
	@Column(name = "transaction_amount")
	private BigDecimal transactionAmount;
	@Column(name = "transaction_month")
	private String     transactionMonth;
	@Column(name = "transaction_category")
	private String     transactionCategory;
	@Column(name = "transaction_year")
	private String     transactionYear;

	public Transactions()
	{
	}

	public Transactions(String transactionName, String transactionType, String transactionDate,
			BigDecimal transactionAmount, String transactionMonth, String transactionCategory, String transactionYear)
	{
		this.transactionName     = transactionName;
		this.transactionType     = transactionType;
		this.transactionDate     = transactionDate;
		this.transactionAmount   = transactionAmount;
		this.transactionMonth    = transactionMonth;
		this.transactionCategory = transactionCategory;
		this.transactionYear     = transactionYear;
	}
}
