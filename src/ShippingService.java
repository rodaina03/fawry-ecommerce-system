import java.util.List;

public class ShippingService {
    public void processShipment(List<Shippable> items) {
        double totalWeight = 0;
        for (Shippable item : items) {
            System.out.println(item.getName() + " " + item.getWeight() + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + (totalWeight / 1000.0) + "kg");
    }
}
