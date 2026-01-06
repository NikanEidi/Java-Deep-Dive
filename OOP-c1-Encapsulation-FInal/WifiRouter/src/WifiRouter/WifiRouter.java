package WifiRouter;

/**
 * WifiRouter Class
 * Represents a secure router device.
 * Demonstrates: Encapsulation (Private fields + Public methods) and Static State.
 */
public class WifiRouter {

    // ===========================
    // 1. Instance Variables (Private State)
    // ===========================
    // 'private' ensures these cannot be modified directly from outside.
    private String ssid;
    private String password;
    private int bandwidth;

    // ===========================
    // 2. Static Variable (Shared State)
    // ===========================
    // Keeps track of the total number of routers created in the system.
    private static int connectionNumber = 0;

    // ===========================
    // 3. Constructor
    // ===========================
    public WifiRouter(String ssid) {
        // Initialize the object state
        this.ssid = ssid;
        this.password = "";   // Default empty password
        this.bandwidth = 0;   // Default 0 bandwidth

        // Increment the global counter every time a NEW router is created
        connectionNumber++;
    }

    // ===========================
    // 4. Methods (Behavior)
    // ===========================

    /**
     * Updates the router password safely.
     * Logic: Password must be at least 8 characters long.
     * @param newPassword The password to set.
     */
    public void setPassword(String newPassword) {
        if (newPassword.length() >= 8) {
            this.password = newPassword;
            System.out.println("✅ Password updated successfully.");
        } else {
            System.out.println("❌ Error: Password must be at least 8 characters!");
        }
    }

    /**
     * Sets the internet speed.
     * Logic: Speed cannot be negative.
     * @param speed The speed in Mbps.
     */
    public void setBandwidth(int speed) {
        if (speed > 0) {
            this.bandwidth = speed;
            System.out.println("✅ Bandwidth set to: " + speed + " Mbps");
        } else {
            System.out.println("❌ Error: Speed cannot be negative!");
        }
    }

    /**
     * Getter for SSID.
     * Logic: Allows read-only access to the network name.
     */
    public String getSSID() {
        return this.ssid;
    }

    /**
     * Static Getter.
     * Allows checking the total router count without creating an object.
     */
    public static int getConnectionCount() {
        return connectionNumber;
    }

}