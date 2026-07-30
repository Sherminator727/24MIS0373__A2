import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Package {
    private String trackingId;
    private String destinationCity;
    private String deliveryStatus; // "Delivered" or "Pending"
    private int deliveryTime; // in days

    public Package(String trackingId, String destinationCity, String deliveryStatus, int deliveryTime) {
        this.trackingId = trackingId;
        this.destinationCity = destinationCity;
        this.deliveryStatus = deliveryStatus;
        this.deliveryTime = deliveryTime;
    }

    public String getTrackingId() { return trackingId; }
    public String getDestinationCity() { return destinationCity; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public int getDeliveryTime() { return deliveryTime; }

    @Override
    public String toString() {
        return "Tracking ID: " + trackingId + ", City: " + destinationCity + 
               ", Status: " + deliveryStatus + ", Time: " + deliveryTime + " days";
    }
}

public class CourierManagement {
    public static void main(String[] args) {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("P101", "New York", "Delivered", 3));
        packages.add(new Package("P102", "Los Angeles", "Pending", 0));
        packages.add(new Package("P103", "Chicago", "Delivered", 6));
        packages.add(new Package("P104", "Houston", "Delivered", 2));
        packages.add(new Package("P105", "Phoenix", "Pending", 0));
        packages.add(new Package("P106", "Philadelphia", "Delivered", 7));

        
        long deliveredCount = packages.stream().filter(p -> p.getDeliveryStatus().equalsIgnoreCase("Delivered")).count();
        long pendingCount = packages.stream().filter(p -> p.getDeliveryStatus().equalsIgnoreCase("Pending")).count();
        System.out.println("--- Package Counts ---");
        System.out.println("Delivered Packages: " + deliveredCount);
        System.out.println("Pending Packages: " + pendingCount);

        
        Package fastest = packages.stream()
                .filter(p -> p.getDeliveryStatus().equalsIgnoreCase("Delivered"))
                .min(Comparator.comparingInt(Package::getDeliveryTime))
                .orElse(null);
        System.out.println("\n--- Fastest Delivery ---");
        System.out.println(fastest);

       
        double averageTime = packages.stream()
                .filter(p -> p.getDeliveryStatus().equalsIgnoreCase("Delivered"))
                .mapToInt(Package::getDeliveryTime)
                .average()
                .orElse(0.0);
        System.out.println("\n--- Average Delivery Time ---");
        System.out.println(averageTime + " days");

        
        System.out.println("\n--- Packages Delivered in More than 5 Days ---");
        packages.stream()
                .filter(p -> p.getDeliveryStatus().equalsIgnoreCase("Delivered") && p.getDeliveryTime() > 5)
                .forEach(System.out::println);

        
        System.out.println("\n--- Packages Sorted by Delivery Time ---");
        packages.sort(Comparator.comparingInt(Package::getDeliveryTime));
        packages.forEach(System.out::println);
    }
}
