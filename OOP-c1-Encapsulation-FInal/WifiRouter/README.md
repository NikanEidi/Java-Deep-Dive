# 🔐 Java Encapsulation Demo: WifiRouter

> **A Deep Dive into OOP Principles, Data Hiding, and Static Logic.**

This repository demonstrates the core concepts of **Encapsulation** in Java through a practical `WifiRouter` simulation. It focuses on how to protect internal object state, implement validation logic, and manage shared state using static members.

---

## 📂 Project Structure

The project follows a standard Java package structure:

```text
.
├── WifiRouter.iml          # IntelliJ Module File
└── src
    ├── WifiRouter          # Package: ca.seneca.apd.models (simulated)
    │   └── WifiRouter.java # The Encapsulated Class (Logic)
    └── main.java           # Entry Point (Test Scenarios)
```

---

## 🧠 Key Concepts Covered

### 1. Encapsulation (Data Hiding)
We adhere to the strict rule: "Private Data, Public Behavior".

- **Private Fields**: Variables like `ssid`, `password`, and `bandwidth` are hidden from the outside world.
- **Controlled Access**: Access is only possible via public methods (setters/getters), allowing us to enforce rules.

### 2. Logic & Validation
Unlike simple C structures, our objects protect themselves.

- **Password Validation**: The router rejects any password shorter than 8 characters.
- **Bandwidth Logic**: Internet speed cannot be set to a negative number.

### 3. "Write-Only" Security Pattern
A security best practice demonstrated in `WifiRouter.java`:

- We have a `setPassword()` method (Write access).
- We intentionally omitted `getPassword()`.
- **Result**: Once a password is set, no external code can read it back. It is secure in memory.

### 4. Static State (Shared Memory)
- **Variable**: `private static int connectionNumber`
- **Behavior**: This variable lives in the Class memory (Metaspace), not in the object Heap. It tracks the total number of routers created across the entire application lifespan.

---

## 💻 Code Highlights

### Secure Setter Example
Notice how the logic prevents invalid data from corrupting the object state:

```java
public void setPassword(String newPassword) {
    // Validation Logic
    if (newPassword.length() >= 8) {
        this.password = newPassword;
        System.out.println("✅ Password updated successfully.");
    } else {
        System.out.println("❌ Error: Password must be at least 8 characters!");
    }
}
```

### Static Implementation
Tracking instances without manual counting in Main:

```java
// Logic inside Constructor
public WifiRouter(String ssid) {
    // ... init variables ...
    connectionNumber++; // Global counter increment
}

// Static Accessor
public static int getConnectionCount() {
    return connectionNumber;
}
```

---

## 🚀 How to Run

1. Open the project in IntelliJ IDEA.
2. Navigate to `src/main.java`.
3. Run the main method.

### Expected Output

```plaintext
--- 📡 Network System Booting ---
Initial Active Routers: 0

--- Setting up Home Router ---
Attempt 1 (Weak Pass): ❌ Error: Password must be at least 8 characters!
Attempt 2 (Strong Pass): ✅ Password updated successfully.
Setting Bandwidth (-100): ❌ Error: Speed cannot be negative!
Setting Bandwidth (1000): ✅ Bandwidth set to: 1000 Mbps
Connected to: Nikan_Home_5G

--- Setting up Office Router ---
Total Active Routers: 2
```