/**
 * The Main class serves as the entry point for our application.
 * Note: In Java, unlike C++, every function must belong to a class.
 */
public class Main {

    /**
     * The JVM looks for this specific signature to launch the application.
     * * @param args Command-line arguments (similar to char* argv[] in C++)
     * * Keywords breakdown:
     * - public: Allows the JVM (which is external) to access this method.
     * - static: Allows the JVM to call this method without creating an instance (object) of the class.
     * - void: Java's main method does not return an exit code to the OS (unlike int main in C++).
     */
    public static void main(String[] args) {
        // System.out refers to the "Standard Output" stream (equivalent to std::cout in C++)

        // println(): Prints the string and appends a newline character (\n) automatically.
        System.out.println("Hello World!");

        // print(): Prints the string but keeps the cursor on the same line.
        System.out.print("Ex2");
    }
}