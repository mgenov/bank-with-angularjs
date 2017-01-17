package com.clouway.bank;

import com.clouway.adapter.builder.JsonBuilder;
import com.clouway.services.TransactionHistoryService;
import com.google.sitebricks.headless.Reply;
import org.junit.Test;

import java.time.LocalDate;

import static com.clouway.adapter.sitebricks.SitebricksMatchers.containsJson;
import static com.clouway.adapter.sitebricks.SitebricksMatchers.isOk;
import static org.junit.Assert.assertThat;

/**
 * @author Borislav Gadjev <gadjevb@gmail.com>
 */
@SuppressWarnings("unchecked")
public class TransactionHistoryServiceTest {
  private TransactionHistoryService service = new TransactionHistoryService();

  @Test
  public void retrieveUserTransactions() {
    Reply<?> actual = service.retrieveTransactionHistory();

    assertThat(actual, isOk());
    assertThat(actual, containsJson(JsonBuilder.aNewJsonArray().withElements(
            JsonBuilder.aNewJson().add("date", LocalDate.of(2017, 1, 1)).add("user", "testUser").add("type", "Deposit").add("amount", 100d),
            JsonBuilder.aNewJson().add("date", LocalDate.of(2017, 1, 1)).add("user", "testUser").add("type", "Deposit").add("amount", 101d)
    )));
  }
}
