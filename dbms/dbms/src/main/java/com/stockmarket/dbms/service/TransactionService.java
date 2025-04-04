package com.dbms.project.service;

import com.dbms.project.model.Holding;
import com.dbms.project.model.Stock;
import com.dbms.project.model.Transaction;
import com.dbms.project.repository.HoldingRepository;
import com.dbms.project.repository.StockRepository;
import com.dbms.project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private HoldingRepository holdingRepository;

    public ResponseEntity<?> handleTransaction(Transaction txn) {
        Stock stock = stockRepository.findBySymbol(txn.getStockSymbol());
        if (stock == null) return ResponseEntity.badRequest().body("Invalid stock");

        txn.setPrice(stock.getMarketPrice());

        // BUY
        if (txn.getTransactionType().equalsIgnoreCase("BUY")) {
            transactionRepository.save(txn);
            holdingRepository.addOrUpdateHolding(txn.getUserId(), txn.getStockSymbol(), txn.getQuantity());
            return ResponseEntity.ok("Stock bought successfully");
        }

        // SELL
        if (txn.getTransactionType().equalsIgnoreCase("SELL")) {
            int currentQty = holdingRepository.getQuantity(txn.getUserId(), txn.getStockSymbol());
            if (currentQty < txn.getQuantity()) {
                return ResponseEntity.badRequest().body("Insufficient holdings");
            }
            transactionRepository.save(txn);
            holdingRepository.reduceHolding(txn.getUserId(), txn.getStockSymbol(), txn.getQuantity());
            return ResponseEntity.ok("Stock sold successfully");
        }

        return ResponseEntity.badRequest().body("Invalid transaction type");
    }

    public ResponseEntity<?> getTransactionsByUser(Long userId) {
        List<Transaction> txns = transactionRepository.findByUserId(userId);
        return ResponseEntity.ok(txns);
    }

    public ResponseEntity<?> getHoldingsByUser(Long userId) {
        List<Holding> holdings = holdingRepository.findByUserId(userId);
        return ResponseEntity.ok(holdings);
    }
}
