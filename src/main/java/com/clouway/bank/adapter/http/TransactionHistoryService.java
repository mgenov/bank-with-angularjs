package com.clouway.bank.adapter.http;

import com.clouway.bank.adapter.http.Json;
import com.clouway.bank.core.Transaction;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Borislav Gadjev <gadjevb@gmail.com>
 */
@At("/v1/transactions")
public class TransactionHistoryService {
  private List<Transaction> transactionList;

  @Get
  public Reply<?> retrieveTransactionHistory() {
    transactionList = generateList(); //todo remove the unnecessary generateList method and implement DAO from which the list will be retrieved

    return Reply.with(transactionList).as(Json.class);
  }

  private List<Transaction> generateList() {
    List<Transaction> transactions = new ArrayList<>();

    for (int i = 0; i < 2; i++) {
      transactions.add(new Transaction(Date.from(LocalDate.of(2017, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), "testUser", "Deposit", 100d + i));
    }

    return transactions;
  }

}
