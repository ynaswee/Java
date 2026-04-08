import java.util.ArrayList;
import java.util.Scanner;

class Package {
    private String id;
    private String recipientName;
    private double weight;

    public Package(String id, String recipientName, double weight) {
        this.id = id;
        this.recipientName = recipientName;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10.2f", id, recipientName, weight);
    }
}

public class DeliveryManager {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Package> packages = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n==== DELIVERY PACKAGE MANAGER ====");
            System.out.println("1. Add Package");
            System.out.println("2. Sort Packages");
            System.out.println("3. Display Packages");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(sc.nextLine()); // parse from line

            switch (choice) {
                case 1:
                    addPackage();
                    break;
                case 2:
                    sortPackages();
                    break;
                case 3:
                    displayPackages();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    // ================= ADD PACKAGE =================
    static void addPackage() {
        System.out.print("Enter ID: ");
        String id = sc.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("ID cannot be empty!");
            return;
        }

        // Prevent duplicate ID
        for (Package p : packages) {
            if (p.getId().equalsIgnoreCase(id)) {
                System.out.println("Duplicate ID not allowed!");
                return;
            }
        }

        System.out.print("Enter Recipient Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter Weight (kg): ");
        double weight = Double.parseDouble(sc.nextLine()); // parse from line

        if (weight <= 0) {
            System.out.println("Weight must be positive!");
            return;
        }

        packages.add(new Package(id, name, weight));
        System.out.println("Package added successfully!");
    }

    // ================= SORT (BUBBLE SORT) =================
    static void sortPackages() {
        if (packages.size() == 0) {
            System.out.println("No packages to sort!");
            return;
        }

        System.out.println("\nSort by:");
System.out.println("1. ID");
System.out.println("2. Recipient Name");
System.out.println("3. Weight");
System.out.print("Enter choice: ");  // ✅ prompt added
int type = Integer.parseInt(sc.nextLine());

System.out.println("Order:");
System.out.println("1. Ascending");
System.out.println("2. Descending");
System.out.print("Enter choice: ");  // ✅ prompt added
int order = Integer.parseInt(sc.nextLine());

        boolean ascending = (order == 1);

        // BUBBLE SORT
        for (int i = 0; i < packages.size() - 1; i++) {
            for (int j = 0; j < packages.size() - i - 1; j++) {

                Package p1 = packages.get(j);
                Package p2 = packages.get(j + 1);

                boolean swap = false;

                switch (type) {
                    case 1: // ID
                        swap = compareString(p1.getId(), p2.getId(), ascending);
                        break;
                    case 2: // Name (case-insensitive)
                        swap = compareString(
                                p1.getRecipientName().toLowerCase(),
                                p2.getRecipientName().toLowerCase(),
                                ascending
                        );
                        break;
                    case 3: // Weight
                        swap = compareDouble(p1.getWeight(), p2.getWeight(), ascending);
                        break;
                    default:
                        System.out.println("Invalid sort type!");
                        return;
                }

                if (swap) {
                    packages.set(j, p2);
                    packages.set(j + 1, p1);
                }
            }
        }

        System.out.println("Packages sorted successfully!");
    }

    // ================= DISPLAY =================
    static void displayPackages() {
        if (packages.size() == 0) {
            System.out.println("No packages available!");
            return;
        }

        System.out.println("\n==== PACKAGE LIST ====");
        System.out.printf("%-10s %-20s %-10s\n", "ID", "Recipient", "Weight");
        System.out.println("---------------------------------------------");

        for (Package p : packages) {
            System.out.println(p); // tatawag automatic ng toString()
        }
    }

    // ================= HELPERS =================
    static boolean compareString(String a, String b, boolean asc) {
        return asc ? a.compareTo(b) > 0 : a.compareTo(b) < 0;
    }

    static boolean compareDouble(double a, double b, boolean asc) {
        return asc ? a > b : a < b;
    }
}