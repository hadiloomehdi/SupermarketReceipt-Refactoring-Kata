package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class FiveForAmountOffer extends QuantityForAmountOffer {

    public FiveForAmountOffer(Product product, double amount) {
        super(product, 5, amount);
    }

}
