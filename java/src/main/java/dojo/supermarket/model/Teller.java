package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.OfferFactory;
import dojo.supermarket.model.offer.SpecialOfferType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        this.offers.put(product, OfferFactory.create(offerType, product, argument));
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }

        return handleOffers(theCart, receipt);
    }

    private Receipt handleOffers(ShoppingCart cart, Receipt receipt) {
        Map<Product, Double> productQuantities = cart.getProductQuantities();
        for (Product product : productQuantities.keySet()) {
            double quantity = productQuantities.get(product);
            if (offers.containsKey(product)) {
                Offer offer = offers.get(product);
                double unitPrice = catalog.getUnitPrice(product);
                Discount discount = offer.apply(quantity, unitPrice);
                if (discount != null)
                    receipt.addDiscount(discount);
            }
        }
        return receipt;
    }

}
