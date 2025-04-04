package com.dbms.project.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NewsService {
    public List<String> getStockNews() {
        return Arrays.asList(
                "Sensex rises 150 points amid market rally.",
                "Nifty 50 at all-time high, investor confidence grows.",
                "US Fed rate decision expected next week.",
                "Apple stock surges after record-breaking iPhone sales."
        );
    }
}
