package com.org.expenseapi.controller;

import com.org.expenseapi.dto.SummaryDto;
import com.org.expenseapi.dto.SummaryResponse;
import com.org.expenseapi.dto.TransactionData;
import com.org.expenseapi.dto.TrendResponse;
import com.org.expenseapi.entity.Transactions;
import com.org.expenseapi.service.TransactionsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
public class BudgetController
{
	private static final Logger logger = LogManager.getLogger(BudgetController.class);
	TransactionsService service;

	public BudgetController(TransactionsService service)
	{
		this.service = service;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/monthlySummary/{month}/{year}")
	SummaryResponse getMonthlySummary(@PathVariable String month, @PathVariable String year) {
		return service.getMonthlySummary(month, year);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/transactions/{month}")
	List<Transactions> getAllTransactions(@PathVariable String month) {
		return service.getAllTransactions (month);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/trend/{type}")
	List<TrendResponse> getSumAmountByTransactionType(@PathVariable String type) {
		return service.getSumAmountByTransactionType (type);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/category/expense/{month}/{year}")
	List<SummaryDto> getExpenseByCategory(@PathVariable String month, @PathVariable String year) {
		return service.getExpenseByCategory(month, year);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("save")
	Transactions saveTransactions(@RequestBody TransactionData transactionData) {
		return service.saveTransaction(transactionData);
	}
}
