package com.org.expenseapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class TransactionData
{
	private String     transactionName;
	private String     transactionType;
	private String     transactionDate;
	private BigDecimal transactionAmount;
	private String     transactionMonth;
	private String     transactionCategory;
	private String     transactionYear;
}
