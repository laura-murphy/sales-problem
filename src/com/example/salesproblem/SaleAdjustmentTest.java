package com.example.salesproblem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Laura on 25/03/2017.
 */
class SaleAdjustmentTest {
    private ProductSale testApple;
    @BeforeEach
    void setUp() {
        testApple = new ProductSale("apple", 1, 32);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void applyAdjustmentAdd() {
        SaleAdjustment adjustment = new SaleAdjustment("add", "apple", 12);
        int newCost = adjustment.ApplyAdjustment(testApple.getCostPerUnit());
        assertEquals(44, newCost);
    }

    @Test
    void applyAdjustmentSubtract() {
        SaleAdjustment adjustment = new SaleAdjustment("subtract", "apple", 12);
        int newCost = adjustment.ApplyAdjustment(testApple.getCostPerUnit());
        assertEquals(20, newCost);
    }

    @Test
    void applyAdjustmentMultiply() {
        SaleAdjustment adjustment = new SaleAdjustment("multiply", "apple", 2);
        int newCost = adjustment.ApplyAdjustment(testApple.getCostPerUnit());
        assertEquals(64, newCost);
    }

    @Test
    void applyAdjustmentInvalidOperator() {
        SaleAdjustment adjustment = new SaleAdjustment("monkeys", "apple", 2);
        int newCost = adjustment.ApplyAdjustment(testApple.getCostPerUnit());
        assertEquals(32, newCost);
    }

}