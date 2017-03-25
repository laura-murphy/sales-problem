package com.example.salesproblem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Laura on 25/03/2017.
 */
class SalesTrackerTest {

    SalesTracker salesTracker;
    ProductSale testApple;
    ProductSale testBanana;
    ProductSale testApple2;

    @BeforeEach
    void setUp() {
        salesTracker = new SalesTracker();
        salesTracker.AddSale("apple", 1, 1);
        salesTracker.AddSale("apple", 2, 3);
        salesTracker.AddSale("banana", 1, 5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSalesForProduct() {
        List<ProductSale> appleSalesList = salesTracker.GetSalesForProduct("apple");
        List<ProductSale> bananaSalesList = salesTracker.GetSalesForProduct("banana");
        assertEquals(2, appleSalesList.size());
        assertEquals(1, bananaSalesList.size());
    }

    @Test
    void addAdjustment() {
        salesTracker.AddAdjustment("add", "banana", 1);
        List<ProductSale> bananas = salesTracker.GetSalesForProduct("banana");
        assert(bananas.get(0).getAdjustedCostPerUnit() == 6);
    }



}