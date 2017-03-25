package com.example.salesproblem;
import java.util.Scanner;
/**
 * Created by Laura on 25/03/2017.
 */
public class salesproblem {

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
        System.out.println("Incorrect message format");
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
        System.out.println("Incorrect message format");
    }

    /** Checks that the sales message is the correct format **/
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
                return false;
            }
            return true;
        }
        return false;
    }
}
