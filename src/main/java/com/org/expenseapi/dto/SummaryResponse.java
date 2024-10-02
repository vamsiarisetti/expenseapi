package com.org.expenseapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SummaryResponse
{
	BigDecimal income;
	BigDecimal expense;
	BigDecimal balance;
	String     month;
}
