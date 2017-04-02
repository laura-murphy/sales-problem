package com.example.salesproblem.test.java;

import com.example.salesproblem.main.java.ProductTotals;
import com.example.salesproblem.main.java.SaleAdjustment;
import com.example.salesproblem.main.java.ProductSale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Laura on 25/03/2017.
 */
class SaleAdjustmentTest {
    private ProductTotals testApple;
    @BeforeEach
    void setUp() {
        testApple = new ProductTotals("apple", 1, 32);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void applyAdjustmentAdd() {
        SaleAdjustment adjustment = new SaleAdjustment("add", "apple", 12);
        int newCost = adjustment.ApplyAdjustment(testApple.getTotalCost());
        assertEquals(44, newCost);
    }

    @Test
    void applyAdjustmentSubtract() {
        SaleAdjustment adjustment = new SaleAdjustment("subtract", "apple", 12);
        int newCost = adjustment.ApplyAdjustment(testApple.getTotalCost());
        assertEquals(20, newCost);
    }

    @Test
    void applyAdjustmentMultiply() {
        SaleAdjustment adjustment = new SaleAdjustment("multiply", "apple", 2);
        int newCost = adjustment.ApplyAdjustment(testApple.getTotalCost());
        assertEquals(64, newCost);
    }

    @Test
    void applyAdjustmentInvalidOperator() {
        SaleAdjustment adjustment = new SaleAdjustment("monkeys", "apple", 2);
        int newCost = adjustment.ApplyAdjustment(testApple.getTotalCost());
        assertEquals(32, newCost);
    }

}