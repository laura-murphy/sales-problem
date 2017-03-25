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
class salesproblemTest {
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
        salesproblem.processMessage("Sale apple 1 1");
        assertEquals("Sale added", outContent.toString().trim());
    }

    @Test
    void processWellFormedSaleMessageWithoutQuantity() {
        salesproblem.processMessage("Sale apple 1");
        assertEquals("Sale added", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidCostNoQuantity() {
        salesproblem.processMessage("Sale apple purple");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidCostWithQuantity() {
        salesproblem.processMessage("Sale apple purple 1");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidQuantity() {
        salesproblem.processMessage("Sale apple 1 purple");
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

}