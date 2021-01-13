package dojo.supermarket.model;

public class Offer {
    SpecialOfferType offerType;
    private final Product product;
    double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

    Discount apply(double quantity, double unitPrice) {
        int quantityAsInt = (int) quantity;
        Discount discount = null;
        int x = 1;
        if (this.offerType == SpecialOfferType.ThreeForTwo) {
            x = 3;
        } else if (this.offerType == SpecialOfferType.TwoForAmount) {
            x = 2;
            if (quantityAsInt >= 2) {
                double total = this.argument * (quantityAsInt / x) + quantityAsInt % 2 * unitPrice;
                double discountN = unitPrice * quantity - total;
                discount = new Discount(product, "2 for " + this.argument, discountN);
            }

        } else if (this.offerType == SpecialOfferType.FiveForAmount) {
            x = 5;
        }

        int numberOfXs = quantityAsInt / x;
        if (this.offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            discount = new Discount(product, "3 for 2", discountAmount);
        }
        if (this.offerType == SpecialOfferType.TenPercentDiscount) {
            discount = new Discount(product, this.argument + "% off", quantity * unitPrice * this.argument / 100.0);
        }
        if (this.offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
            double discountTotal = unitPrice * quantity - (this.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
            discount = new Discount(product, x + " for " + this.argument, discountTotal);
        }
        return discount;
    }

}
