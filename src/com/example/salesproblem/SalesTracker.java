package com.example.salesproblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 25/03/2017.
 */
public class SalesTracker {

    /** Stores the sales records **/
    private List<ProductSale> productSales;

    /** Constructor **/
    public SalesTracker()
    {
        this.productSales = new ArrayList<>();
    }

    /** Adds a new sale **/
    public void AddSale(String productType, int cost, int quantity)
    {
        this.productSales.add(new ProductSale(productType, cost, quantity));
    }


}
