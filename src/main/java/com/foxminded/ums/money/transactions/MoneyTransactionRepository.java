package com.foxminded.ums.money.transactions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MoneyTransactionRepository extends CrudRepository<MoneyTransaction, UUID> {
    @Query(value = "SELECT moneytransactions FROM MoneyTransaction moneytransactions " +
            " WHERE moneytransactions.sender.id = :id")
    List<MoneyTransaction> findBySender(@Param("id") UUID personId);

    @Query(value = "SELECT moneytransactions FROM MoneyTransaction moneytransactions " +
            " WHERE moneytransactions.receiver.id = :id")
    List<MoneyTransaction> findByReceiver(@Param("id") UUID personId);
}