# Intro to JAVA

# Module 1

## What is JAVA ?

Java is not just a programming language; it is a **comprehensive ecosystem**. Unlike C++, which often compiles directly to machine code dependent on the OS, Java follows the **WORA(Write Once, Run Anywhere)** principle.

---

- **General Purpose:** It is not constrained to a specific domain; it can build anything from mobile apps to enterprise systems.
- **Object-Oriented:** It models real-world scenarios naturally using classes and objects.
- **Platform Independent:** Follows "Write Once, Run Anywhere" (WORA). Code written on Windows can run on Linux without rewriting.
- **Concurrent:** Supports multithreading, allowing multiple tasks to run simultaneously

---

## Key Applications of Java

Due to its versatility and stability, Java is used in various high-demand fields:

- **Game Development:** Powers popular games (like Minecraft) and integrates with VR/Machine Learning .
- **Cloud Computing:** Its WORA capability makes it ideal for decentralized, cloud-based applications .
- **Big Data:** Used in data processing engines to handle massive, real-time datasets.
- **Artificial Intelligence:** A powerhouse for Machine Learning libraries due to its speed and stability.
- **Internet of Things (IoT):** Programs sensors and edge devices that connect to the internet

---

## Why is Java Popular?

Java remains a top choice for developers and enterprises for several reasons:

- **Learning Resources:** Extensive documentation, books, and courses help beginners move from Core to Advanced Java .
- **Rich Ecosystem:** A vast collection of **Inbuilt Functions and Libraries (APIs)** means developers don't have to write every function from scratch .
- **Community Support:** A massive, active community helps solve coding challenges quickly.
- **Development Tools:** High-quality tools for editing, debugging, and testing make development cost-efficient .
- **Security:** Untrusted code runs in a secure environment (sandbox) and cannot harm the host system or read/write files arbitrarily .

---

## Java Architecture: How it Works

This is the most critical difference for C++ programmers. Java acts as a bridge between human language and hardware using two main components:

1. **Java Language & APIs (Front-end):** The syntax and pre-written libraries developers use.
2. **Java Virtual Machine - JVM (Back-end):** An abstraction layer that communicates with the hardware.

**The Hybrid Execution Model (Compiler + Interpreter):**

- **C++ Approach:** Typically uses a *Compiler* to translate source code directly to machine code.
- **Scripting Approach:** Uses an *Interpreter* to run code line-by-line.
- **Java Approach:** Combines both.
    1. **Compilation:** The source code (`.java`) is compiled into **Bytecode** (`.class`).
    2. **Interpretation:** The **JVM** reads this Bytecode and interprets it for the specific underlying hardware (Windows, Linux, etc.) .

---

## Java Editions

There are three main editions of Java targeting different platforms:

- **Java SE (Standard Edition):** Used for client-side and desktop application development. **(We use this in our course)**.
- **Java EE (Enterprise Edition):** Used for server-side applications (e.g., Servlets, Web Apps).
- **Java ME (Micro Edition):** Used for mobile and embedded devices.

---

## Development Workflow (Steps to Run)

To execute a Java program, the following steps occur:

1. **Coding:** Programmer writes the source code (e.g., `HelloWorld.java`).
2. **Compiling:** The `javac` command translates source code into bytecode (e.g., `HelloWorld.class`) .
3. **Loading:** The JVM loads the class file using the `java` command .
4. **Verification:** The JVM verifies the bytecode to ensure it is valid and secure (Internal Integrity Check) .
5. **Execution:** The program starts running from the `main` entry point.

---

## First Program

```java
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
```

---

# Module 2: Similarities Between Java & C ++

## General Overview

- **Heritage:** C++ is derived from C. Java is designed to be familiar to C++ programmers. Both are successful, object-oriented languages .
- **Complexity:** The learning curve and complexity level for both languages are similar.
- **Usage:** Both are used for building operating systems, browsers, and various applications.

---

## Syntactic Similarities

- *C++:* `int main() { return 0; }`
- *Java:* `public static void main(String[] args) { ... }`.
- *Difference:* In Java, everything must be inside a `class`.
- **Control Flow:** Loops (`while`, `do-while`, `for`) and Conditional Statements (`if`, `else`, `switch`) are identical in syntax and logic .
- **Operators:** Arithmetic (`+`, `-`, `/`, `*`) and Relational (`<`, `>`, `!=`, `==`) operators are the same .
- **Comments:** Both support single-line (`//`) and multi-line (`/* ... */`) comments .

---

## Object-Oriented Features (OOP)

- **Inheritance:** Sharing properties between classes.
- *Syntax:* C++ uses `: public Base`. Java uses `extends Base`.
- **Polymorphism:** Performing a single action in different ways (e.g., method overriding).
- **Abstraction:** Hiding background details (Abstract classes).
- **Encapsulation:** Combining data and functions, using `private` to hide data and `public` getters/setters to access it .

---

## Advanced Features

- *Constraint:* Static methods can only access other static members.
    - Used in both languages for class-level members (shared among all objects).
    - Static methods can be called without creating an object.
    - *Constraint:* Static methods can only access other static members.
- **For-Each Loop:** Both support iterating over containers without counters.
    - *C++:* `for (auto x : arr)`.
    - *Java:* `for (var x : arr)` (or explicit type).
- **Templates vs. Generics:**
    - Used for writing code independent of data types (reusability).
    - *C++:* Uses **Templates** (`template <class T>`).
    - *Java:* Uses **Generics** (`class GenericClass<T>`).

---