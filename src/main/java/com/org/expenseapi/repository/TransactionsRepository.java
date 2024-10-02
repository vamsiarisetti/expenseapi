package com.org.expenseapi.repository;

import com.org.expenseapi.dto.SummaryDto;
import com.org.expenseapi.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long>
{
	/*@Query("SELECT new com.org.expenseapi.dto.SummaryDto("
					+ "transactionType, SUM(transactionAmount)"
					+ ")"
					+ "FROM TRANSACTIONS GROUP BY transactionType"
	)
	List<SummaryDto> findSumAmountGroupByType(String month);
	*/
	@Query(
			nativeQuery = true,
			value = "SELECT "
					+ "transaction_type, SUM(TRANSACTION_AMOUNT)"
					+ "FROM TRANSACTIONS WHERE TRANSACTION_MONTH = :month AND TRANSACTION_YEAR = :year"
					+ " GROUP BY transaction_type"
	)
	List<Object[]> findSumAmountGroupByType(String month, String year);

	@Query(
			nativeQuery = true,
			value = "SELECT * FROM TRANSACTIONS WHERE transaction_month = :month"
	)
	List<Transactions> findAllByMonth(String month);

	@Query(
			nativeQuery = true,
			value = "SELECT transaction_month,SUM(TRANSACTION_AMOUNT)"
					+ "FROM TRANSACTIONS WHERE TRANSACTION_TYPE= :type GROUP BY TRANSACTION_MONTH"
	)
	List<Object[]> fetchMonthlySumForTransactionType(String type);

	@Query(
			nativeQuery = true,
			value = "SELECT TRANSACTION_CATEGORY, SUM(TRANSACTION_AMOUNT) "
					+ "FROM TRANSACTIONS WHERE TRANSACTION_TYPE ='expense'"
					+ "AND TRANSACTION_MONTH  = :month AND TRANSACTION_YEAR = :year "
					+ "GROUP BY TRANSACTION_CATEGORY"
	)
	List<Object[]> fetchExpensesByCategory(String month, String year);
}
