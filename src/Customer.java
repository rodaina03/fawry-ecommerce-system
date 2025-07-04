public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    Customer(String name, double balance, Cart cart) {
        this.name = name;
        this.balance = balance;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    //Adding a product to the cart

    //Deducting amount after purchase
    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }
    public void deductBalance(double amount) {
        if(hasSufficientBalance(amount)) {
            balance -= amount;
        }
        else {
            System.out.println("You do not have enough balance.");
        }

    }


}
