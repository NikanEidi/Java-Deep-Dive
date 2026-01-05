public class VariableDeepDive {

    public static void main(String[] args) {

        // ==========================================
        // PART 1: Primitive Types (Value Semantics)
        // ==========================================
        // Primitives are stored strictly on the Stack.
        // They hold the actual binary data (payload), not an address.

        int num1 = 10;
        int num2 = num1; // COPY BY VALUE: The bits of '10' are copied to a new memory slot.

        num1 = 50; // Changing num1 affects ONLY num1's memory slot.

        System.out.println("--- Primitives ---");
        System.out.println("num1: " + num1); // Output: 50
        System.out.println("num2: " + num2); // Output: 10 (Unaffected)


        // ==========================================
        // PART 2: Reference Types (Reference Semantics)
        // ==========================================
        // In Java, Arrays and Objects are ALWAYS on the Heap.
        // The variable 'arr1' is actually a pointer (stored on Stack) holding the Heap memory address.
        // Think of it as: int* arr1 = new int[]{10, 20}; in C++

        int[] arr1 = {10, 20};
        int[] arr2 = arr1; // COPY BY REFERENCE: Only the memory address (0x123...) is copied.
        // Now both arr1 and arr2 point to the SAME object on the Heap.

        arr1[0] = 999; // Modifying the object via one reference...

        System.out.println("\n--- References ---");
        System.out.println("arr1[0]: " + arr1[0]); // Output: 999
        System.out.println("arr2[0]: " + arr2[0]); // Output: 999 (Side Effect! arr2 sees the change)


        // ==========================================
        // PART 3: The "String" Gotcha (Immutable Reference)
        // ==========================================
        // String is a Reference Type, BUT it is "Immutable".
        // Even though we copy references, you cannot "change" a string in place.
        // Any modification creates a NEW string in the Heap.

        String s1 = "Hello";
        String s2 = s1; // Both point to the same "Hello" in String Pool.

        s1 = "World";   // This does NOT overwrite "Hello".
        // It creates a NEW object "World" and updates s1's pointer.

        System.out.println("\n--- Strings (Immutable) ---");
        System.out.println("s1: " + s1); // Output: World
        System.out.println("s2: " + s2); // Output: Hello (Still points to the old object)
    }
}