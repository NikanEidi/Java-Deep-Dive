public class Main {
    public static void main(String[] args) {

        // 1. Accessing Static Method BEFORE creating objects
        // We can ask the class "How many accounts exist?" even if no accounts exist yet.
        // Why? Because static members live in the class memory, not heap object memory.
        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts()); // Output: 0

        // 2. Creating Objects (The 'new' keyword)
        // 'new' allocates memory on the Heap for the instance variables (accountNumber, balance).
        // The Constructor runs and increments the static 'totalAccounts'.
        BankAccount A1 = new BankAccount(22112233);
        // Now totalAccounts is 1.

        BankAccount A2 = new BankAccount(11223344);
        // Now totalAccounts is 2.

        // 3. Modifying State
        // calling deposit on A1 only affects A1's memory space. A2 is untouched.
        A1.deposit(250);

        // 4. Reading State (Getters)
        // Accessing the private data via public methods.
        System.out.println("Nikan Account: " + A1.getBalance()); // Output: 250.0
        System.out.println("Other Account: " + A2.getBalance()); // Output: 0.0 (Default)

        // 5. Checking Static State again
        // Both A1 and A2 contributed to this single shared number.
        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts()); // Output: 2
    }
}