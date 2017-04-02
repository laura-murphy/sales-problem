package com.example.salesproblem.main.java;

/**
 * Created by Laura on 25/03/2017.
 */
public class ProductSale {

    /**
     * Type of product eg Apple
     */
    private String productType;

    /**
     * Quantity of sale
     */
    private int quantitySold;

    /**
     * Cost per unit in pence
     */
    private int costPerUnit;

    /**
     * Cost per unit adjusted by adjustment message
     */
    private int adjustedCostPerUnit;

    /**
     * Constructor that sets product type, quantity and cost per unit
     * @param productType
     * @param quantitySold
     * @param costPerUnit
     */
    public ProductSale(String productType, int quantitySold, int costPerUnit)
    {
        this.productType = productType.toLowerCase();
        this.quantitySold = quantitySold;
        this.costPerUnit = costPerUnit;
        this.adjustedCostPerUnit = costPerUnit;
    }

    /**
     * Returns the product type
     * @return
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Returns the cost per unit at time of recording
     * @return
     */
    public int getCostPerUnit() {
        return costPerUnit;
    }

    /**
     * Returns the cost per unit with any adjustments made
     * @return
     */
    public int getAdjustedCostPerUnit() {
        return adjustedCostPerUnit;
    }

    /**
     * Sets the adjusted cost per unit
     * @param adjustedCostPerUnit
     */
    public void setAdjustedCostPerUnit(int adjustedCostPerUnit) {
        this.adjustedCostPerUnit = adjustedCostPerUnit;
    }

    /**
     * Returns the quantity sold
     * @return
     */
    public int getQuantitySold() {
        return quantitySold;
    }

    /**
     * Returns the total sale value
     * @return
     */
    public int getTotalSaleValue()
    {
        return this.quantitySold * adjustedCostPerUnit;
    }
}
