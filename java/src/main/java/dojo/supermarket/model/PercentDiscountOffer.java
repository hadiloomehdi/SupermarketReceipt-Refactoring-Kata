package dojo.supermarket.model;

public class PercentDiscountOffer extends Offer {

    private final double discountPercentage;

    public PercentDiscountOffer(Product product, double discountPercentage) {
        super(product);
        this.discountPercentage = discountPercentage;
    }

    public Discount apply(double quantity, double unitPrice) {
        return new Discount(product, discountPercentage + "% off", quantity * unitPrice * discountPercentage / 100.0);
    }

}
