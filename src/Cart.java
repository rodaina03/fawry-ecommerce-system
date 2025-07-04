import java.util.*;

public class Cart {
    private List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    //Add CartItem to Cart
    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than 0.");
            return;
        }
        if (product.isExpired()) {
            System.out.println("Error: Product " + product.getName() + " is expired.");
            return;
        }
        if(product.getQuantity() < quantity) {
            System.out.println("Error: Product " + product.getName() + " is out of stock.");
            return;
        }
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                product.reduceQuantity(quantity);
                System.out.println("Updated quantity of " + product.getName() + " in cart.");
                return;
            }
        }


        product.reduceQuantity(quantity);
        cartItems.add(new CartItem(product, quantity));

    }
    //Remove product from Cart
    public void removeItemByProduct(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                cartItems.remove(item);
                System.out.println("Product removed from cart successfully.");
                return;
            }
        }
        System.out.println("Error: Product not found in cart.");


    }
    //update product quantity
    public void updateItemQuantity(Product product, int newQuantity) {
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);

            if (item.getProduct().equals(product)) {
                int currentCartQuantity = item.getQuantity();
                int availableStock = product.getQuantity() + currentCartQuantity;

                if (newQuantity <= 0) {
                    cartItems.remove(i);  // ✅ safe with index-based loop
                    product.setQuantity(product.getQuantity() + currentCartQuantity);
                    System.out.println("Removed product from cart due to zero quantity.");
                    return;
                }

                if (newQuantity > availableStock) {
                    System.out.println("Error: Not enough stock to update quantity.");
                    return;
                }

                product.setQuantity(availableStock - newQuantity);
                item.setQuantity(newQuantity);
                System.out.println("Updated quantity to " + newQuantity + " for " + product.getName());
                return;
            }
        }
        System.out.println("Error: Product not found in cart.");
    }
    //increment & decrement quantity
    public void incrementItem(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                if (product.getQuantity() >= 1) {
                    item.setQuantity(item.getQuantity() + 1);
                    product.setQuantity(product.getQuantity() - 1);
                    System.out.println("Successfully incremented");

                } else {
                    System.out.println("Error: No more stock available for " + product.getName());

                }
            }
        }
        System.out.println("Error: Product not found in cart.");

    }
    public void decrementItem(Product product) {
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            if (item.getProduct().equals(product)) {
                int currentQuantity = item.getQuantity();
                if (currentQuantity > 1) {
                    item.setQuantity(currentQuantity - 1);
                    product.setQuantity(product.getQuantity() + 1);
                } else {
                    cartItems.remove(i);
                    product.setQuantity(product.getQuantity() + 1);
                }
            }
        }
        System.out.println("Error: Product not found in cart.");
    }
    public void clearCart() {
        if (!isEmpty()) {
            cartItems.clear();
            System.out.println("Cart has been cleared successfully.");
        } else {
            System.out.println("Error: Cart is empty.");
        }
    }
    public double calculateSubtotal(){
        double subtotal = 0;
        if(isEmpty()){
            System.out.println("Error: Cart is empty.");
        }
        for (CartItem item : cartItems) {
            subtotal += item.getQuantity() * item.getProduct().getPrice();
        }
        return subtotal;
    }
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    //checkout
    public void checkout(Customer customer) {
        if(isEmpty()){
            System.out.println("Error: Cart is empty.");
            return;
        }
        //Check for expired or out-of-stock products
        for (CartItem item : cartItems) {
            //expired
            if (item.getProduct().isExpired()) {
                System.out.println("Error: Product " + item.getProduct().getName() + " is expired.");
                return;
            }
            //out-of-stock
            if(item.getProduct().outOfStock()) {
                System.out.println("Error: Product " + item.getProduct().getName() + " is out of stock.");
                return;
            }
        }
        double subtotal = calculateSubtotal();
        int shippingFee = 30;
        double total = subtotal + shippingFee;
        if (!customer.hasSufficientBalance(total)) {
            System.out.println("Error: Insufficient balance.");
            return;
        }
        customer.deductBalance(total);

        List<Shippable> toShip = new ArrayList<>();
        for (CartItem item : cartItems) {
            if (item.getProduct().isRequiresShipping()) {
                toShip.add(new ShippableItem(item.getProduct()));
            }
        }
        if (!toShip.isEmpty()) {
            System.out.println("** Shipment notice **");
            new ShippingService().processShipment(toShip);
        }
        // Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cartItems) {
            int itemTotal = item.getQuantity() * item.getProduct().getPrice();
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + itemTotal);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + total);
        System.out.println("Customer balance after payment: " + customer.getBalance());

        // Clear cart
        cartItems.clear();

    }

}
