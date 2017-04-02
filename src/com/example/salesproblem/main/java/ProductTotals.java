package com.example.salesproblem.main.java;

/**
 * Created by Laura on 27/03/2017.
 */
public class ProductTotals {

    /**
     *  The Product Type
     */
    private String productType;

    /**
     * The number of sales
     */
    private int numberOfSales;

    /**
     *  The total cost
     */
    private int totalCost;

    /**
     * Constructor
     * @param productType
     * @param numberOfSales
     * @param totalCost
     */
    public ProductTotals(String productType, int numberOfSales, int totalCost)
    {
        this.productType = productType;
        this.numberOfSales = numberOfSales;
        this.totalCost = totalCost;
    }

    /**
     * Adds a new product sale
     * @param sale
     */
    public void ProcessSale(ProductSale sale)
    {
        this.numberOfSales += sale.getQuantitySold();
        this.totalCost += sale.getTotalSaleValue();
    }

    /**
     * Returns the number of sales
     * @return
     */
    public int getNumberOfSales() {
        return numberOfSales;
    }

    /**
     * Returns the total cost of the sales
     * @return
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the product type
     * @return
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the product type
     * @param productType
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Sets the total cost
     * @param totalCost
     */
    public void setTotalCost(int totalCost)
    {
        this.totalCost = totalCost;
    }
}
