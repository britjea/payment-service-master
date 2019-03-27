package br.com.fiap.paymentservice.repository;

import br.com.fiap.paymentservice.model.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private static List<Transaction> transactions = new ArrayList<>();

    private static Long id = 0L;

    public Transaction getById(Long id) {

        final Transaction savedTransaction = transactions.stream().filter(transaction -> transaction.getId() == id).findFirst().get();

        return savedTransaction;
    }

    public Transaction save(Transaction transaction) {
        this.id = this.id + 1;
        transaction.setId(this.id);
        transactions.add(transaction);

        return transaction;
    }

    public void delete(Transaction transaction) {

        transactions.remove(transaction);
    }

    public void update(Long id, Transaction transaction) {

        Transaction savedTransaction = getById(id);

        transactions.remove(savedTransaction);

        BeanUtils.copyProperties(transaction, savedTransaction, "id");

        transactions.add(savedTransaction);
    }



}
