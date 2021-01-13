package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class TwoForAmountOffer extends QuantityForAmountOffer {

    public TwoForAmountOffer(Product product, double amount) {
        super(product, 2, amount);
    }

}
