package com.example.greentreeonline.Class.New;

import java.util.ArrayList;
import java.util.Collections;

public class SortProduct {
    ArrayList<Product> jobCandidate = new ArrayList<>();

    public SortProduct(ArrayList<Product> jobCandidate) {
        this.jobCandidate = jobCandidate;
    }

    public ArrayList<Product> getSortedProductByPrice() {
        Collections.sort(jobCandidate, Product.priceComparator);
        return jobCandidate;
    }

    public ArrayList<Product> getSortedProductByPriceDes() {
        Collections.sort(jobCandidate, Product.priceDesComparator);
        return jobCandidate;
    }
}
