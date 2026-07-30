import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class WaterLocation {
    private String locationName;
    private double phValue;
    private double turbidity;
    private double dissolvedOxygen;

    public WaterLocation(String locationName, double phValue, double turbidity, double dissolvedOxygen) {
        this.locationName = locationName;
        this.phValue = phValue;
        this.turbidity = turbidity;
        this.dissolvedOxygen = dissolvedOxygen;
    }

    public String getLocationName() { return locationName; }
    public double getPhValue() { return phValue; }
    public double getTurbidity() { return turbidity; }
    public double getDissolvedOxygen() { return dissolvedOxygen; }

    // 1. Classify water as Safe or Unsafe based on typical thresholds
    public String classifyWater() {
        if (phValue >= 6.5 && phValue <= 8.5 && turbidity < 5.0 && dissolvedOxygen > 4.0) {
            return "Safe";
        }
        return "Unsafe";
    }

    @Override
    public String toString() {
        return "Location: " + locationName + " [pH: " + phValue + 
               ", Turbidity: " + turbidity + " NTU, DO: " + dissolvedOxygen + " mg/L | Status: " + classifyWater() + "]";
    }
}

public class WaterQualityMonitoring {
    public static void main(String[] args) {
        List<WaterLocation> locations = new ArrayList<>();
        locations.add(new WaterLocation("North Reservoir", 7.2, 3.1, 6.5));
        locations.add(new WaterLocation("Industrial Canal", 5.8, 6.2, 3.8));
        locations.add(new WaterLocation("East Lake", 8.1, 2.0, 7.2));
        locations.add(new WaterLocation("South Creek", 7.5, 8.5, 5.0));

        // Display Classifications
        System.out.println("--- Water Quality Classifications ---");
        for (WaterLocation loc : locations) {
            System.out.println(loc.getLocationName() + " is classified as: " + loc.classifyWater());
        }

        // 2. Display unsafe locations
        System.out.println("\n--- Unsafe Locations ---");
        locations.stream()
                .filter(loc -> loc.classifyWater().equals("Unsafe"))
                .forEach(System.out::println);

        // 3. Find the location with the highest dissolved oxygen
        WaterLocation highestDO = locations.stream()
                .max(Comparator.comparingDouble(WaterLocation::getDissolvedOxygen))
                .orElse(null);
        System.out.println("\n--- Location with Highest Dissolved Oxygen ---");
        if (highestDO != null) {
            System.out.println(highestDO.getLocationName() + " (" + highestDO.getDissolvedOxygen() + " mg/L)");
        }

        // 4. Calculate average pH
        double averagePh = locations.stream()
                .mapToDouble(WaterLocation::getPhValue)
                .average()
                .orElse(0.0);
        System.out.println("\n--- Average pH Level ---");
        System.out.println(averagePh);

        // 5. Sort locations by turbidity
        System.out.println("\n--- Locations Sorted by Turbidity ---");
        locations.sort(Comparator.comparingDouble(WaterLocation::getTurbidity));
        locations.forEach(System.out::println);
    }
}
