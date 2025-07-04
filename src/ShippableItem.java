public class ShippableItem implements Shippable{
    Product product;

    public ShippableItem(Product product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getWeight() {
        return product.getWeight();
    }
}
