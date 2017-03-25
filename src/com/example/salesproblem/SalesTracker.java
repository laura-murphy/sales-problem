package com.example.salesproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Laura on 25/03/2017.
 */
public class SalesTracker {

    /** Stores the sales records **/
    private List<ProductSale> productSales;

    /** Stores the sale adjustments **/
    private List<SaleAdjustment> saleAdjustments;

    /** Constructor **/
    public SalesTracker()
    {
        this.productSales = new ArrayList<>();
        this.saleAdjustments = new ArrayList<>();
    }

    /** Adds a new sale **/
    public void AddSale(String productType, int cost, int quantity)
    {
        this.productSales.add(new ProductSale(productType, cost, quantity));
    }

    /** Adds a new adjustment to the tracker and updates the cost of recorded sales for the
     * relevant product **/
    public void AddAdjustment(String operation, String productType, int amount)
    {
        SaleAdjustment adjustment = new SaleAdjustment(operation, productType, amount);
        this.saleAdjustments.add(adjustment);
        for(ProductSale sale : this.productSales) {
            if (sale.getProductType().equalsIgnoreCase(adjustment.getProduct()))
            {
                int newCost = adjustment.ApplyAdjustment(sale.getCostPerUnit());
                sale.setAdjustedCostPerUnit(newCost);
            }
        }
    }

    /** Gets all recorded sales for given product **/
    public List<ProductSale> GetSalesForProduct(String productType)
    {
        return this.productSales.stream()
                .filter(p -> p.getProductType().equalsIgnoreCase(productType))
                .collect(Collectors.toList());
    }
}
