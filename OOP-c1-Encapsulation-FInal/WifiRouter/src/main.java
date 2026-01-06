import WifiRouter.WifiRouter; // 1. Import is required because WifiRouter is in a package

public class main {
    public static void main(String[] args) {

        System.out.println("--- 📡 Network System Booting ---");

        // 1. Static Check (Before creating objects)
        // We check the class variable directly. Should be 0.
        System.out.println("Initial Active Routers: " + WifiRouter.getConnectionCount());

        // 2. Object Creation
        System.out.println("\n--- Setting up Home Router ---");
        WifiRouter homeRouter = new WifiRouter("Nikan_Home_5G");

        // 3. Testing Encapsulation (Setters with Logic)

        // A. Testing Password Validation
        System.out.print("Attempt 1 (Weak Pass): ");
        homeRouter.setPassword("123"); // Should fail

        System.out.print("Attempt 2 (Strong Pass): ");
        homeRouter.setPassword("DarkJava2026"); // Should succeed

        // B. Testing Bandwidth Validation
        System.out.print("Setting Bandwidth (-100): ");
        homeRouter.setBandwidth(-100); // Should fail

        System.out.print("Setting Bandwidth (1000): ");
        homeRouter.setBandwidth(1000); // Should succeed

        // 4. Testing Getters
        System.out.println("Connected to: " + homeRouter.getSSID());
        // Note: We cannot get the password! (Write-Only security)

        // 5. Creating a Second Object (To test Static increment)
        System.out.println("\n--- Setting up Office Router ---");
        WifiRouter officeRouter = new WifiRouter("Seneca_Lab_WiFi");

        // 6. Final Static Check
        // Should be 2 now (Home + Office)
        System.out.println("Total Active Routers: " + WifiRouter.getConnectionCount());
    }
}