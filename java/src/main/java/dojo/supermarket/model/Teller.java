package dojo.supermarket.model;

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

        return handleOffers(productQuantities, receipt);
    }

    private Receipt handleOffers(List<ProductQuantity>productQuantities, Receipt receipt) {
        for (ProductQuantity productQuantity: productQuantities) {
            double quantity = productQuantity.getQuantity();
            Product product = productQuantity.getProduct();
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
