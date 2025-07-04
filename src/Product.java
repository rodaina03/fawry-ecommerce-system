import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Product {
    private String name;
    private int quantity;
    private int price;
    private LocalDate expiryDate;
    private boolean requiresShipping;
    private int weight;

    //For expiring, shippable product
    Product(String name, int quantity, int price, LocalDate expiryDate, int weight) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.requiresShipping = true;
        this.weight = weight;
    }

    // For non-expiring, non-shippable
    Product(String name, int quantity, int price) {
        this(name, quantity, price, null, 0);
        this.requiresShipping = false;
    }
    // For non-expiring, shippable
    Product(String name, int quantity, int price, int weight){
        this(name, quantity, price, null, weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public boolean isExpired() {
        return expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }
    public boolean outOfStock() {
        return quantity <= 0;
    }

}