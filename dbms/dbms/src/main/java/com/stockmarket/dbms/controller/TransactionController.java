package com.example.stockmarket.controller;

import com.example.stockmarket.model.Transaction;
import com.example.stockmarket.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/buy")
    public String buy(@RequestBody Transaction transaction) {
        return transactionService.buyStock(transaction);
    }

    @PostMapping("/sell")
    public String sell(@RequestBody Transaction transaction) {
        return transactionService.sellStock(transaction);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactions(@PathVariable int userId) {
        return transactionService.getTransactionsByUser(userId);
    }
}
