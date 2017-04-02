package com.example.salesproblem.main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Laura on 25/03/2017.
 */
public class SalesTracker {

    /**
     * Stores the product sales
     */
    private List<ProductSale> productSales;

    /**
     * Stores the sale adjustments
     */
    private List<SaleAdjustment> saleAdjustments;

    /**
     * Stores the product sale totals
     */
    private List<ProductTotals> productTotals;

    /**
     * Constructor
     */
    public SalesTracker()
    {
        this.productSales = new ArrayList<>();
        this.saleAdjustments = new ArrayList<>();
        this.productTotals = new ArrayList<>();
    }

    /**
     * Adds a new sale
     * @param productType
     * @param cost
     * @param quantity
     */
    public void AddSale(String productType, int cost, int quantity)
    {
        ProductSale ps = new ProductSale(productType, quantity, cost);
        this.productSales.add(ps);

        for(ProductTotals pt : this.productTotals)
        {
            if(pt.getProductType().equalsIgnoreCase(productType))
            {
                pt.ProcessSale(ps);
                return;
            }
        }
        this.productTotals.add(new ProductTotals(productType, quantity, ps.getTotalSaleValue()));
    }

    /**
     * Adds a new adjustment to the tracker and updates the cost of recorded sales for the
     * relevant product
     * @param operation
     * @param productType
     * @param amount
     */
    public void AddAdjustment(String operation, String productType, int amount)
    {
        SaleAdjustment adjustment = new SaleAdjustment(operation, productType, amount);
        this.saleAdjustments.add(adjustment);

        // update the adjusted cost per unit
        int totalSaleValue = 0;
        for(ProductSale sale : this.productSales)
        {
            if(sale.getProductType().equalsIgnoreCase(adjustment.getProduct()))
            {
                sale.setAdjustedCostPerUnit(adjustment.ApplyAdjustment(sale.getAdjustedCostPerUnit()));
                totalSaleValue += sale.getTotalSaleValue();
            }
        }

        // Update the adjusted total cost
        for(ProductTotals total : this.productTotals)
        {
            if(total.getProductType().equalsIgnoreCase(adjustment.getProduct()))
            {
                total.setTotalCost(totalSaleValue);
            }
        }
    }

    /**
     * Gets all recorded sales for given product
     * @param productType
     * @return
     */
    public ProductTotals GetSalesForProduct(String productType)
    {
        return this.productTotals.stream()
                .filter(p -> p.getProductType().equalsIgnoreCase(productType))
                .collect(Collectors.toList()).get(0);
    }

    /**
     * Gets all sales totals
     * @return
     */
    public List<ProductTotals> GetTotalSales()
    {
        return this.productTotals;
    }
}
