package dojo.supermarket.model;

public class ThreeForTwoOffer extends Offer {

    public ThreeForTwoOffer(Product product) {
        super(product);
    }

    public Discount apply(double quantity, double unitPrice) {
        int quantityAsInt = (int) quantity;
        if (quantityAsInt > 2) {
            double discountAmount = quantity * unitPrice - ((quantityAsInt / 3 * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            return new Discount(product, "3 for 2", discountAmount);
        }
        return null;
    }

}
