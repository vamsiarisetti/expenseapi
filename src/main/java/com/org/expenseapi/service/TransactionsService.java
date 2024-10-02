package com.org.expenseapi.service;

import com.org.expenseapi.dto.SummaryDto;
import com.org.expenseapi.dto.SummaryResponse;
import com.org.expenseapi.dto.TransactionData;
import com.org.expenseapi.dto.TrendResponse;
import com.org.expenseapi.entity.Transactions;
import com.org.expenseapi.repository.TransactionsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TransactionsService
{
	private static final Logger                 logger = LogManager.getLogger (TransactionsService.class);
	private final        TransactionsRepository repository;

	public TransactionsService(TransactionsRepository repository)
	{
		this.repository = repository;
	}

	public SummaryResponse getMonthlySummary(String month, String year)
	{
		logger.info ("fetching summary for {} month and year : {}", month, year);
		List<SummaryDto> summaryResult = repository.findSumAmountGroupByType (month, year)
				.stream ().map (result -> new SummaryDto ((String) result[0], ((BigDecimal) result[1])))
				.collect (Collectors.toList ());

		BigDecimal totalIncome = getTotalIncomeExpense (summaryResult, "Income");
		BigDecimal totalExpense = getTotalIncomeExpense (summaryResult, "Expense");
		BigDecimal balance = totalIncome.subtract (totalExpense);

		return new SummaryResponse (totalIncome, totalExpense, balance, month);
	}

	public List<Transactions> getAllTransactions(String month) {
		return repository.findAllByMonth (month);
	}

	public List<TrendResponse> getSumAmountByTransactionType(String type) {
		return repository.fetchMonthlySumForTransactionType (type)
				.stream ().map (result -> new TrendResponse ((String) result[0], ((BigDecimal) result[1])))
				.collect (Collectors.toList ());
	}

	public List<SummaryDto> getExpenseByCategory(String month, String year) {
		return repository.fetchExpensesByCategory (month, year)
				.stream ().map (result -> new SummaryDto ((String) result[0], ((BigDecimal) result[1])))
				.collect (Collectors.toList ());
	}

	public Transactions saveTransaction(TransactionData transactionData)
	{
		return repository.save (
				new Transactions (transactionData.getTransactionName (), transactionData.getTransactionType (),
						transactionData.getTransactionDate (), transactionData.getTransactionAmount (),
						transactionData.getTransactionMonth (), transactionData.getTransactionCategory (),
						transactionData.getTransactionYear ()));
	}

	private BigDecimal getTotalIncomeExpense(List<SummaryDto> summaryResult, String Income)
	{
		return summaryResult.stream ().filter (incomeExpensePredicate (Income))
				.map (SummaryDto::getTotalTransactionAmount).reduce (BigDecimal.ZERO, BigDecimal::add);
	}

	private Predicate<SummaryDto> incomeExpensePredicate(String transactionType)
	{
		return result -> transactionType.equalsIgnoreCase (result.getTransactionType ());
	}
}
