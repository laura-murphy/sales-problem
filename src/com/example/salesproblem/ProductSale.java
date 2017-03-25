package com.example.salesproblem;

/**
 * Created by Laura on 25/03/2017.
 */
public class ProductSale {
    /** Type of product eg Apple **/
    private String productType;

    /** Quantity of sale**/
    private int quantitySold;

    /** Cost per unit in pence **/
    private int costPerUnit;

    /** Cost per unit adjusted by adjustment message **/
    private int adjustedCostPerUnit;

    /** Constructor that sets product type, quantity and cost per unit **/
    public ProductSale(String productType, int quantitySold, int costPerUnit)
    {
        this.productType = productType.toLowerCase();
        this.quantitySold = quantitySold;
        this.costPerUnit = costPerUnit;
        this.adjustedCostPerUnit = costPerUnit;
    }

    /** Returns the product type **/
    public String getProductType() {
        return productType;
    }

    /** Returns the cost per unit at time of recording **/
    public int getCostPerUnit() {
        return costPerUnit;
    }

    /** Returns the cost per unit with any adjustments made **/
    public int getAdjustedCostPerUnit() {
        return adjustedCostPerUnit;
    }

    /** Sets the adjusted cost per unit **/
    public void setAdjustedCostPerUnit(int adjustedCostPerUnit) {
        this.adjustedCostPerUnit = adjustedCostPerUnit;
    }
}
