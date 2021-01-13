package dojo.supermarket.model;

public class QuantityForAmountOffer extends Offer {

    private final int quantityLot;
    private final double amount;

    public QuantityForAmountOffer(Product product, int quantityLot, double amount) {
        super(product);
        this.quantityLot = quantityLot;
        this.amount = amount;
    }

    public Discount apply(double quantity, double unitPrice) {
        int quantityAsInt = (int) quantity;
        if (quantityAsInt >= quantityLot) {
            double totalPriceWithoutOffer = unitPrice * quantity;
            double totalPrice = amount * (quantityAsInt / quantityLot) + quantityAsInt % quantityLot * unitPrice;
            double discountTotal = totalPriceWithoutOffer - totalPrice;
            return new Discount(product, quantity + " for " + amount, discountTotal);
        }
        return null;
    }

}
