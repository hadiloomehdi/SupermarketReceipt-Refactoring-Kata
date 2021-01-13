package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();

    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    Map<Product, Double> getProductQuantities() {
        Map<Product, Double> productQuantities = new HashMap<>();
        for (ProductQuantity productQuantity: items) {
            Product product = productQuantity.getProduct();
            double quantity = productQuantity.getQuantity();
            if (productQuantities.containsKey(product)) {
                productQuantities.put(product, productQuantities.get(product) + quantity);
            } else {
                productQuantities.put(product, quantity);
            }
        }
        return productQuantities;
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
    }

}
