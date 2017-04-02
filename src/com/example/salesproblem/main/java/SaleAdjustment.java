package com.example.salesproblem.main.java;

/**
 * Created by Laura on 25/03/2017.
 */
public class SaleAdjustment {
    /** The maths operation to perform **/
    private String operation;

    /** The product type **/
    private String product;

    /**
     * The amount to apply to the cost
     * **/
    private int amount;

    /** Constructor **/
    public SaleAdjustment(String operation, String product, int amount)
    {
        this.operation = operation.toLowerCase();
        this.product = product.toLowerCase();
        this.amount = amount;
    }

    /** Returns the product type **/
    public String getProduct() {
        return product;
    }

    /** Applies the adjustment to the given product cost **/
    public int ApplyAdjustment(int cost)
    {
        int adjustedCost = cost;
        switch (this.operation)
        {
            case "add":
                adjustedCost = cost + this.amount;
                break;
            case "subtract":
                adjustedCost = cost - this.amount;
                break;
            case "multiply":
                adjustedCost = cost * this.amount;
                break;
        }
        return adjustedCost;
    }

    /**
     * Returns the amount
     * @return
     */
    public int getAmount()
    {
        return this.amount;
    }
}
