package com.example.salesproblem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Laura on 25/03/2017.
 */
class salesProblemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(null);
    }

    @Test
    void processWellFormedSaleMessageWithQuantity() {
        SalesProblem.processMessage("Sale apple 1 1");
        assertEquals("Sale added", outContent.toString().trim());
    }

    @Test
    void processWellFormedSaleMessageWithoutQuantity() {
        SalesProblem.processMessage("Sale apple 1");
        assertEquals("Sale added", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidCostNoQuantity() {
        SalesProblem.processMessage("Sale apple purple");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidCostWithQuantity() {
        SalesProblem.processMessage("Sale apple purple 1");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidQuantity() {
        SalesProblem.processMessage("Sale apple 1 purple");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageTypeMixedCaseValid() {
        SalesProblem.processMessage("sALe apple 1 1");
        assertEquals("Sale added", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageAddValid() {
        SalesProblem.processMessage("Adjust add apple 1");
        assertEquals("Adjustment successful", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageSubtractValid() {
        SalesProblem.processMessage("Adjust subtract apple 1");
        assertEquals("Adjustment successful", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageMultiplyValid() {
        SalesProblem.processMessage("Adjust multiply apple 1");
        assertEquals("Adjustment successful", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageMultipleCaseOperatorValid() {
        SalesProblem.processMessage("Adjust AdD apple 1");
        assertEquals("Adjustment successful", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageMultipleCaseMessageTypeValid() {
        SalesProblem.processMessage("aDjUst add apple 1");
        assertEquals("Adjustment successful", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageInvalidOperator() {
        SalesProblem.processMessage("Adjust bananas apple 1");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageInvalidAmount() {
        SalesProblem.processMessage("Adjust add apple seven");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

}