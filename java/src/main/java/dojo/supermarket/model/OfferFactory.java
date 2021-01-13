package dojo.supermarket.model;

public class OfferFactory {

    public static Offer create(SpecialOfferType offerType, Product product, double argument) {
        switch (offerType) {
            case FiveForAmount:
                return new FiveForAmountOffer(product, argument);
            case TwoForAmount:
                return new TwoForAmountOffer(product, argument);
            case ThreeForTwo:
                return new ThreeForTwoOffer(product);
            case TenPercentDiscount:
                return new PercentDiscountOffer(product, argument);
        }
        throw new IllegalArgumentException();
    }
}
