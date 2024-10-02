package com.org.expenseapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TrendResponse
{
	String     month;
	BigDecimal unit;
}
