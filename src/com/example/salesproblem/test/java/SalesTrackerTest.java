package com.example.salesproblem.test.java;

import com.example.salesproblem.main.java.ProductTotals;
import com.example.salesproblem.main.java.SalesTracker;
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
        ProductTotals apples = salesTracker.GetSalesForProduct("apple");
        ProductTotals bananas = salesTracker.GetSalesForProduct("banana");

        assertEquals(4, apples.getNumberOfSales());
        assertEquals(7, apples.getTotalCost());
        assertEquals(5, bananas.getNumberOfSales());
        assertEquals(5, bananas.getTotalCost());
    }

    @Test
    void addAdjustment() {
        salesTracker.AddAdjustment("add", "banana", 1);
        ProductTotals bananas = salesTracker.GetSalesForProduct("banana");
        assertEquals(10, bananas.getTotalCost());
    }

    @Test
    void SubtractAdjustment() {
        salesTracker.AddSale("orange", 3, 2);
        salesTracker.AddAdjustment("Subtract", "orange", 1);
        ProductTotals oranges = salesTracker.GetSalesForProduct("orange");
        assertEquals(4, oranges.getTotalCost());
    }

    @Test
    void MultiplyAdjustment() {
        salesTracker.AddSale("orange", 3, 2);
        salesTracker.AddAdjustment("Multiply", "orange", 2);
        ProductTotals oranges = salesTracker.GetSalesForProduct("orange");
        assertEquals(12, oranges.getTotalCost());
    }

    @Test
    void getTotalSales() {
        List<ProductTotals> totals = salesTracker.GetTotalSales();
        assertEquals(2, totals.size());
        assertEquals(4, totals.get(0).getNumberOfSales());
        assertEquals(7, totals.get(0).getTotalCost());
    }


}