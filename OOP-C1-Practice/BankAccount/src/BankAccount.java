/**
 * BankAccount Class
 * Represents a blueprint for creating individual bank account objects.
 * concepts: Encapsulation, Static vs. Instance, Constructors.
 */
public class BankAccount {

    // ===========================
    // 1. Instance Variables (State)
    // ===========================
    // These variables are created SEPARATELY for every new object (new BankAccount).
    // 'private' means they are Encapsulated (hidden). Only this class can touch them directly.
    private int accountNumber;
    private double balance;

    // ===========================
    // 2. Static Variable (Shared State)
    // ===========================
    // 'static' means this variable belongs to the CLASS, not any specific object.
    // There is only ONE copy of 'totalAccounts' in memory (Metaspace), shared by A1, A2, A3, etc.
    // If A1 changes it, A2 sees the change immediately.
    private static int totalAccounts = 0;

    // ===========================
    // 3. Constructor
    // ===========================
    // This runs immediately when you write 'new BankAccount(...)'.
    public BankAccount(int accountNumber) {
        // 'this' refers to the specific object being created right now (e.g., A1).
        this.accountNumber = accountNumber;
        this.balance = 0; // Default starting balance

        // LOGIC: Every time a new account is born, we increment the global counter.
        // Since 'totalAccounts' is static, it remembers the count across all objects.
        totalAccounts++;
    }

    // ===========================
    // 4. Methods (Behavior)
    // ===========================

    /**
     * Deposits money into the specific account instance.
     * @param amount The money to add.
     */
    public void deposit(double amount) {
        // Validation Logic (Part of Encapsulation)
        if (amount > 0) {
            this.balance += amount; // Modifies the 'balance' of the specific object calling this method.
            System.out.println("Deposited: " + amount + " | New Balance: " + this.balance);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    /**
     * Getter Method for Balance.
     * Since 'balance' is private, the outside world (Main class) needs this method to read it.
     * This provides "Read-Only" access.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Static Method to get the total count.
     * NOTE: This method is 'static', so it can be called WITHOUT creating an object.
     * Usage: BankAccount.getTotalAccounts();
     */
    public static int getTotalAccounts() {
        return totalAccounts;
    }
}