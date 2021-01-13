package dojo.supermarket.model;

public abstract class Offer {
    protected final Product product;

    public Offer(Product product) {
        this.product = product;
    }

    public abstract Discount apply(double quantity, double unitPrice);

}
