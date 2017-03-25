package com.example.salesproblem;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Laura on 25/03/2017.
 */
public class SalesProblem {

    private static String invalidFormatMessage = "Incorrect message format";
    private static SalesTracker salesTracker = new SalesTracker();

    public static void main(String[] args) {
        Scanner scanner = new Scanner((System.in));
        while(true) {
            System.out.println("Please enter a message:");
            String message = scanner.nextLine();
            System.out.println("Received: " + message);
            processMessage(message);
        }
    }

    /** Process the message from the input **/
    public static void processMessage(String message)
    {
        String[] messageParts = message.trim().split("\\s+");
        /* Handle sale messages */
        if(messageParts[0].equalsIgnoreCase("Sale")) {
            processSaleMessage(messageParts);
            return;
        }
        /* Handle adjustment messages */
        else if(messageParts[0].equalsIgnoreCase("Adjust"))
        {
            processAdjustMessage(messageParts);
            return;
        }
        System.out.println(invalidFormatMessage);
    }

    /** Process sales messages **/
    private static void processSaleMessage(String[] messageParts) {
        if(isValidSaleMessage(messageParts)) {
            /* valid sales message, add to tracker */
            String productType = messageParts[1];
            int cost = Integer.parseInt(messageParts[2]);
            int quantity = messageParts.length == 4 ? Integer.parseInt(messageParts[3]) : 1;

            salesTracker.AddSale(productType, cost, quantity);
            System.out.println("Sale added");
            return;
        }
        /* message is invalid */
        System.out.println(invalidFormatMessage);
    }

    /** Process adjustment messages **/
    private static void processAdjustMessage(String[] messageParts)
    {
        if(isValidAdjustMessage(messageParts))
        {
            /* Valid adjustment message, add to tracker */
            String operation = messageParts[1];
            String productType = messageParts[2];
            int amount = Integer.parseInt(messageParts[3]);

            salesTracker.AddAdjustment(operation, productType, amount);
            System.out.println("Adjustment successful");
            return;
        }
        /* message is invalid */
        System.out.println(invalidFormatMessage);
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
}
