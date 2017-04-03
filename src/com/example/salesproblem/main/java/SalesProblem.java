package com.example.salesproblem.main.java;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Laura on 25/03/2017.
 */
public class SalesProblem {

    /**
     * Display text for an incorrect message format
     */
    private static String invalidFormatMessage = "Incorrect message format";

    /**
     * SalesTracker
     */
    private static SalesTracker salesTracker = new SalesTracker();

    /**
     * Message counter, tracking for reports
     */
    private static int messageCounter;

    /**
     * The interval to display the sales report
     */
    private static final int intervalToSalesReport = 10;

    /**
     * Main method for application
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner((System.in));
        messageCounter = 0;
        while(true) {
            System.out.println("Please enter a message:");
            String message = scanner.nextLine();
            System.out.println("Received: " + message);
            processMessage(message);
        }
    }

    /** ProcessSale the message from the input **/
    public static boolean processMessage(String message)
    {
        String[] messageParts = message.trim().split("\\s+");
        /* Handle sale messages */
        if(messageParts[0].equalsIgnoreCase("Sale")) {
            boolean success = processSaleMessage(messageParts);
            if(!success) {
                return false;
            }
            // message succeeded
            messageCounter++;
            handleReporting();
            return true;
        }
        /* Handle adjustment messages */
        else if(messageParts[0].equalsIgnoreCase("Adjust"))
        {
            boolean success = processAdjustMessage(messageParts);
            if(!success) {
                return false;
            }
            // message succeeded
            messageCounter++;
            handleReporting();
            return true;
        }
        /* Handle load messages */
        else if(messageParts[0].equalsIgnoreCase("Load"))
        {
            boolean success = processLoadMessage(messageParts);
            if(!success) {
                return false;
            }
            return true;
        }
        System.out.println(invalidFormatMessage);
        return false;
    }

    /**
     *  Resets the message counter to 0
     */
    public static void resetMessageCounter()
    {
        messageCounter = 0;
    }

    /**
     * Resets the salesTracker
     */
    public static void resetSalesTracker() { salesTracker = new SalesTracker();}

    /** ProcessSale sales messages **/
    public static boolean processSaleMessage(String[] messageParts) {
        if(isValidSaleMessage(messageParts)) {
            /* valid sales message, add to tracker */
            String productType = messageParts[1];
            int cost = Integer.parseInt(messageParts[2]);
            int quantity = messageParts.length == 4 ? Integer.parseInt(messageParts[3]) : 1;

            salesTracker.AddSale(productType, cost, quantity);
            //System.out.println("Sale added");
            return true;
        }
        /* message is invalid */
        System.out.println(invalidFormatMessage);
        return false;
    }

    /**
     * Writes out the sales report
     */
    public static void doSalesReport() {
        List<ProductTotals> totals = salesTracker.GetTotalSales();
        System.out.println("Sales Report:");

        // Zero sales
        if(totals.size()==0)
        {
            System.out.println("0 Sales");
            return;
        }

        // There were some sales
        System.out.println("Product Type || Number of Sales || Total Value");
        for(ProductTotals total : totals)
        {
            System.out.println(total.getProductType() + " || " +
                    total.getNumberOfSales() + " || " +
                    total.getTotalCost());
        }
    }

    /**
     *  Writes out the adjustment report
     */
    public static void doAdjustmentReport() {
        List<SaleAdjustment> adjustments = salesTracker.GetTotalAdjustments();
        System.out.println("Sale Adjustment Report:");

        // Zero adjustments
        if(adjustments.size()==0)
        {
            System.out.println("0 adjustments");
            return;
        }

        // There are adjustments
        System.out.println("Product Type || Adjustment Type || Amount");
        for(SaleAdjustment adjustment : adjustments)
        {
            System.out.println(adjustment.getProduct() + " || " +
                    adjustment.getOperation() + " || " +
                    adjustment.getAmount());
        }
    }

    /** ProcessSale adjustment messages **/
    private static boolean processAdjustMessage(String[] messageParts)
    {
        if(isValidAdjustMessage(messageParts))
        {
            /* Valid adjustment message, add to tracker */
            String operation = messageParts[1];
            String productType = messageParts[2];
            int amount = Integer.parseInt(messageParts[3]);

            salesTracker.AddAdjustment(operation, productType, amount);
            //System.out.println("Adjustment successful");
            return true;
        }
        /* message is invalid */
        System.out.println(invalidFormatMessage);
        return false;
    }

    /**  Loads a flat file of messages to process
     *   Expects one message per line
     * @param messageParts
     */
    private static boolean processLoadMessage(String[] messageParts)
    {
        if(isValidLoadMessage(messageParts))
        {
            String filename = messageParts[1];
            try
            {
                // Attempt to load in the file and process line by line
                Scanner file = new Scanner(new FileReader(filename));
                while(file.hasNextLine())
                {
                    processMessage(file.nextLine());
                }
                return true;
            }
            catch(Exception e)
            {
                /* message is invalid */
                System.out.println("Could not load file: " + e.getMessage());
                return false;
            }
        }
         /* message is invalid */
        System.out.println(invalidFormatMessage);
        return false;
    }

    /** Checks that the sales message is the correct format:
     * [MessageType] [ProductType] [Cost] *[Quantity] (* Optional) **/
    private static boolean isValidSaleMessage(String[] messageParts)
    {
        if(messageParts.length == 3 || messageParts.length == 4) {
            try{
                Integer.parseInt(messageParts[2]);
                if(messageParts.length==4)
                {
                    Integer.parseInt(messageParts[3]);
                }
            }
            catch(Exception e)
            {
                // cost or quantity inputs not an integer
                return false;
            }
            /* looks ok */
            return true;
        }
        // wrong number of arguments
        return false;
    }

    /** Checks that the adjust message is the correct format:
     * [MessageType] [OperationType] [ProductType] [Cost] **/
    private static boolean isValidAdjustMessage(String[] messageParts)
    {
        if(messageParts.length==4)
        {
            List<String> validOperators = Arrays.asList("add", "subtract", "multiply");
            if(!validOperators.contains(messageParts[1].toLowerCase()))
            {
                /* Not the list of valid operators */
                return false;
            }
            try {
                Integer.parseInt(messageParts[3]);
            }
            catch(Exception e)
            {
                // cost argument is not an integer
                return false;
            }
            /* looks ok */
            return true;
        }
        // Wrong number of arguments
        return false;
    }

    /** Checks that the load message is the correct format:
     * [MessageType] [filename]
     * @param messageParts
     * @return
     */
    private static boolean isValidLoadMessage(String[] messageParts)
    {
        if(messageParts.length==2)
            return true;
        return false;
    }

    private static void handleReporting()
    {
        if(messageCounter%50==0)
        {
            doAdjustmentReport();
        }
        else if(messageCounter%intervalToSalesReport==0)
        {
            doSalesReport();
        }
    }


}
