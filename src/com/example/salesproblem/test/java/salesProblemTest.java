package com.example.salesproblem.test.java;

import com.example.salesproblem.main.java.SalesProblem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Laura on 25/03/2017.
 */
class salesProblemTest {
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        SalesProblem.resetMessageCounter();
        SalesProblem.resetSalesTracker();
        System.setOut(null);
    }

    @Test
    void processWellFormedSaleMessageWithQuantity() {
        boolean result = SalesProblem.processMessage("Sale apple 1 1");
        assertEquals(true, result);
    }

    @Test
    void processWellFormedSaleMessageWithoutQuantity() {
        boolean result = SalesProblem.processMessage("Sale apple 1");
        assertEquals(true, result);
    }

    @Test
    void processSaleMessageInvalidCostNoQuantity() {
        boolean result = SalesProblem.processMessage("Sale apple purple");
        assertEquals(false, result);
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidCostWithQuantity() {
        boolean result = SalesProblem.processMessage("Sale apple purple 1");
        assertEquals(false, result);
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageInvalidQuantity() {
        boolean result = SalesProblem.processMessage("Sale apple 1 purple");
        assertEquals(false, result);
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processSaleMessageTypeMixedCaseValid() {
        boolean result = SalesProblem.processMessage("sALe apple 1 1");
        assertEquals(true, result);
    }

    @Test
    void processAdjustMessageAddValid() {
        boolean result = SalesProblem.processMessage("Adjust add apple 1");
        assertEquals(true, result);
    }

    @Test
    void processAdjustMessageSubtractValid() {
        boolean result = SalesProblem.processMessage("Adjust subtract apple 1");
        assertEquals(true, result);
    }

    @Test
    void processAdjustMessageMultiplyValid() {
        boolean result = SalesProblem.processMessage("Adjust multiply apple 1");
        assertEquals(true, result);
    }

    @Test
    void processAdjustMessageMultipleCaseOperatorValid() {
        boolean result = SalesProblem.processMessage("Adjust AdD apple 1");
        assertEquals(true, result);
    }

    @Test
    void processAdjustMessageMultipleCaseMessageTypeValid() {
        boolean result = SalesProblem.processMessage("aDjUst add apple 1");
        assertEquals(true, result);
    }

    @Test
    void processAdjustMessageInvalidOperator() {
        boolean result = SalesProblem.processMessage("Adjust bananas apple 1");
        assertEquals(false, result);
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processAdjustMessageInvalidAmount() {
        boolean result = SalesProblem.processMessage("Adjust add apple seven");
        assertEquals(false, result);
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void processLoadMessageValid()
    {
        String file = getClass().getResource("test_messages2.txt").getPath();
        boolean result = SalesProblem.processMessage("Load " + file);
        assertEquals(true, result);
        assertEquals("", outContent.toString().trim());
    }

    @Test
    void processLoadMessageFileNotExists()
    {
        boolean result = SalesProblem.processMessage("Load blah.txt");
        assertEquals(false, result);
        assertEquals("Could not load file: blah.txt (The system cannot find the file specified)", outContent.toString().trim());
    }

    @Test
    void processLoadMessageInvalidMessageFormat()
    {
        boolean result = SalesProblem.processMessage("Load file.txt blah");
        assertEquals(false, result);
        assertEquals("Incorrect message format", outContent.toString().trim());
    }

    @Test
    void handleMessageReport()
    {
        String file = getClass().getResource("test_messages3.txt").getPath();
        Scanner scanResult = new Scanner(getClass().getResourceAsStream("test_messages3_result.txt"));
        String resultFile = scanResult.useDelimiter("\\Z").next();
        boolean result = SalesProblem.processMessage("Load " + file);
        assertEquals(true, result);
        assertEquals(resultFile, outContent.toString().trim());
    }

}