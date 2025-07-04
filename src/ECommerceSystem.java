import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ECommerceSystem {
    public static void main(String[] args) {
        System.out.println("\n Test 1: Successful Checkout ");
        Product cheese = new Product("Cheese", 10, 100, LocalDate.parse("2025-08-01"), 200);
        Product biscuits = new Product("Biscuits", 5, 150, LocalDate.parse("2025-08-15"), 700);
        Product tv = new Product("TV", 3, 1500, 10000);
        Product scratchCard = new Product("Scratch Card", 50, 50);

        Cart cart = new Cart();
        Customer customer = new Customer("Alice", 3000, cart);

        cart.addItem(cheese, 2);
        cart.addItem(biscuits, 1);
        cart.addItem(tv, 1);
        cart.addItem(scratchCard, 3);

        cart.checkout(customer);

        System.out.println("\n Test 2: Empty Cart ");
        Cart cart1 = new Cart();
        Customer customer1 = new Customer("EmptyCartUser", 1000, cart1);
        cart1.checkout(customer1);


        System.out.println("\n Test 3: Expired Product ");
        Cart cart2 = new Cart();
        Customer customer2 = new Customer("ExpiredUser", 1000, cart2);
        Product expiredMilk = new Product("Milk", 2, 50, LocalDate.parse("2022-01-01"), 100);
        cart2.addItem(expiredMilk, 1);
        cart2.checkout(customer2);

        System.out.println("\n Test 4: Out of Stock ");
        Cart cart3 = new Cart();
        Customer customer3 = new Customer("StockUser", 1000, cart3);
        Product limitedTV = new Product("TV", 1, 1000, 8000);
        cart3.addItem(limitedTV, 2);

        System.out.println("\n Test 5: Insufficient Balance ");
        Cart cart4 = new Cart();
        Customer customer4 = new Customer("PoorUser", 100, cart4);
        cart4.addItem(tv, 1);
        cart4.checkout(customer4);


        System.out.println("\n Test 6: Zero Quantity Update ");
        Cart cart5 = new Cart();
        Customer customer5 = new Customer("ZeroUpdateUser", 1000, cart5);
        Product bread = new Product("Bread", 5, 20, LocalDate.parse("2025-12-01"), 100);
        cart5.addItem(bread, 2);
        cart5.updateItemQuantity(bread, 0);  // Should remove item
        cart5.checkout(customer5);          // Should say "Cart is empty"

        System.out.println("\n Test 7: Duplicate Product Merge ");
        Cart cart6 = new Cart();
        Customer customer6 = new Customer("RepeatUser", 1000, cart6);
        cart6.addItem(cheese, 2);
        cart6.addItem(cheese, 3);  // Should merge into 5
        cart6.checkout(customer6);

        System.out.println("\n Test 8: Invalid Quantity (Negative)");
        Cart cart7 = new Cart();
        Customer customer7 = new Customer("NegativeQtyUser", 1000, cart7);
        Product apple = new Product("Apple", 10, 10, LocalDate.parse("2025-09-01"), 50);
        cart7.addItem(apple, -1);

    }


}
