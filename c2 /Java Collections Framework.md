# Java Collections Framework - Complete Study Notes

```
╔═══════════════════════════════════════════════════════════════════════╗
║                  JAVA COLLECTIONS FRAMEWORK                           ║
║                     Complete Reference Guide                          ║
╚═══════════════════════════════════════════════════════════════════════╝
```

---

## Table of Contents

1. [Introduction to Collections](#1-introduction-to-collections)
2. [Collection Interface](#2-collection-interface)
3. [Lists](#3-lists)
4. [Sets](#4-sets)
5. [Maps](#5-maps)
6. [Iterators](#6-iterators)
7. [Generics](#7-generics)
8. [Method Cheat Sheet](#8-method-cheat-sheet)

---

## 1. Introduction to Collections

### What is a Data Structure?
A **data structure** is an organized collection of data that supports:
- **Storage** of data
- **Access** operations
- **Manipulation** operations

### What is a Collection?
A **collection** is a container object representing a group of objects (elements).

**Real-world Examples:**
- Shopping cart → collection of products
- Playlist → collection of songs
- Contact list → collection of phone numbers

### Collection Framework Hierarchy

```
                    ┌─────────────┐
                    │   Iterable  │
                    └──────┬──────┘
                           │
                    ┌──────▼──────┐
                    │  Collection │
                    └──────┬──────┘
                           │
         ┌─────────────────┼─────────────────┐
         │                 │                 │
    ┌────▼────┐      ┌─────▼─────┐     ┌────▼────┐
    │   List  │      │    Set    │     │  Queue  │
    └────┬────┘      └─────┬─────┘     └─────────┘
         │                 │
    ┌────┴────┐      ┌─────┴─────┐
    │         │      │           │
ArrayList  LinkedList  HashSet  TreeSet
Vector                LinkedHashSet
Stack
```

### Generic vs Non-Generic Collections

**❌ Non-Generic (OLD - Avoid):**
```java
ArrayList list = new ArrayList();  // Can hold ANY type
list.add("Hello");
list.add(123);  // Mixed types - dangerous!
String str = (String) list.get(0);  // Manual casting required
```

**✅ Generic (MODERN - Use This):**
```java
ArrayList<String> list = new ArrayList<String>();
list.add("Hello");
// list.add(123);  // Compile-time error - type safe!
String str = list.get(0);  // No casting needed
```

---

## 2. Collection Interface

The **Collection interface** is the root interface for all collections (except Map).

### Key Methods

```
╔══════════════════════════════════════════════════════════════╗
║              COLLECTION INTERFACE METHODS                    ║
╠══════════════════════════════════════════════════════════════╣
║  add(E element)           → Add element                      ║
║  addAll(Collection c)     → Add all elements from c          ║
║  remove(Object o)         → Remove element                   ║
║  removeAll(Collection c)  → Remove all from c                ║
║  retainAll(Collection c)  → Keep only elements in c          ║
║  clear()                  → Remove all elements              ║
║  contains(Object o)       → Check if element exists          ║
║  containsAll(Collection)  → Check if all exist               ║
║  size()                   → Get number of elements           ║
║  isEmpty()                → Check if empty                   ║
║  toArray()                → Convert to array                 ║
║  iterator()               → Get iterator object              ║
╚══════════════════════════════════════════════════════════════╝
```

### Example: Basic Collection Operations

```java
import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        // Create two collections
        Collection<String> fruits1 = new ArrayList<>();
        Collection<String> fruits2 = new ArrayList<>();
        
        // Add elements to first collection
        fruits1.add("Apple");
        fruits1.add("Banana");
        fruits1.add("Cherry");
        
        // Add elements to second collection
        fruits2.add("Banana");
        fruits2.add("Date");
        fruits2.add("Elderberry");
        
        System.out.println("Fruits1: " + fruits1);
        System.out.println("Fruits2: " + fruits2);
        
        // Check if element exists
        System.out.println("Contains Apple? " + fruits1.contains("Apple"));
        
        // Union: all elements from both
        Collection<String> union = new ArrayList<>(fruits1);
        union.addAll(fruits2);
        System.out.println("Union: " + union);
        
        // Intersection: common elements
        Collection<String> intersection = new ArrayList<>(fruits1);
        intersection.retainAll(fruits2);
        System.out.println("Intersection: " + intersection);
        
        // Difference: elements in fruits1 but not in fruits2
        Collection<String> difference = new ArrayList<>(fruits1);
        difference.removeAll(fruits2);
        System.out.println("Difference: " + difference);
    }
}
```

**Output:**
```
Fruits1: [Apple, Banana, Cherry]
Fruits2: [Banana, Date, Elderberry]
Contains Apple? true
Union: [Apple, Banana, Cherry, Banana, Date, Elderberry]
Intersection: [Banana]
Difference: [Apple, Cherry]
```

---

## 3. Lists

### What is a List?
A **List** is an **ordered collection** that:
- **Allows duplicates**
- **Maintains insertion order**
- **Provides index-based access**

### List Hierarchy

```
        ┌──────────┐
        │   List   │
        └─────┬────┘
              │
    ┌─────────┼─────────┬────────┐
    │         │         │        │
ArrayList LinkedList  Vector  Stack
```

---

### 3.1 ArrayList

**Implementation:** Dynamic resizable array  
**Default Capacity:** 10  
**Growth:** Increases by 50% when full

#### When to Use ArrayList?
✅ Fast random access (get by index)  
✅ Iteration  
✅ Adding/removing at the end  
❌ Frequent insertions/deletions in middle

#### Time Complexity

```
┌──────────────────────┬──────────────┐
│ Operation            │ Complexity   │
├──────────────────────┼──────────────┤
│ get(index)           │ O(1)         │
│ set(index, element)  │ O(1)         │
│ add(element)         │ O(1) *       │
│ add(index, element)  │ O(n)         │
│ remove(index)        │ O(n)         │
│ contains(element)    │ O(n)         │
│ indexOf(element)     │ O(n)         │
└──────────────────────┴──────────────┘
* Amortized constant time
```

#### ArrayList Methods

```java
import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Create ArrayList
        ArrayList<Integer> numbers = new ArrayList<>();
        
        // Adding elements
        numbers.add(10);        // [10]
        numbers.add(20);        // [10, 20]
        numbers.add(30);        // [10, 20, 30]
        numbers.add(1, 15);     // Insert at index 1: [10, 15, 20, 30]
        
        System.out.println("List: " + numbers);
        
        // Accessing elements
        int first = numbers.get(0);           // 10
        int second = numbers.get(1);          // 15
        System.out.println("First: " + first);
        System.out.println("Second: " + second);
        
        // Modifying elements
        numbers.set(2, 25);                   // [10, 15, 25, 30]
        System.out.println("After set: " + numbers);
        
        // Searching
        int index = numbers.indexOf(25);      // 2
        boolean exists = numbers.contains(40); // false
        System.out.println("Index of 25: " + index);
        System.out.println("Contains 40? " + exists);
        
        // Removing elements
        numbers.remove(Integer.valueOf(15));  // Remove by value
        System.out.println("After remove(15): " + numbers);
        
        numbers.remove(0);                     // Remove by index
        System.out.println("After remove(0): " + numbers);
        
        // Size operations
        System.out.println("Size: " + numbers.size());
        System.out.println("Is empty? " + numbers.isEmpty());
        
        // Clear all
        numbers.clear();
        System.out.println("After clear: " + numbers);
    }
}
```

**Output:**
```
List: [10, 15, 20, 30]
First: 10
Second: 15
After set: [10, 15, 25, 30]
Index of 25: 2
Contains 40? false
After remove(15): [10, 25, 30]
After remove(0): [25, 30]
Size: 2
Is empty? false
After clear: []
```

---

### 3.2 LinkedList

**Implementation:** Doubly-linked list  
**Growth:** No capacity concept (nodes created as needed)

#### When to Use LinkedList?
✅ Frequent insertions/deletions at beginning or middle  
✅ Implementation of Queue/Deque  
❌ Random access (get by index is slow)

#### Time Complexity

```
┌──────────────────────┬──────────────┐
│ Operation            │ Complexity   │
├──────────────────────┼──────────────┤
│ get(index)           │ O(n)         │
│ addFirst()           │ O(1)         │
│ addLast()            │ O(1)         │
│ removeFirst()        │ O(1)         │
│ removeLast()         │ O(1)         │
│ add(index, element)  │ O(n)         │
└──────────────────────┴──────────────┘
```

#### LinkedList Structure

```
┌────────────────────────────────────────────────────────┐
│  LinkedList Structure (Doubly-Linked)                  │
└────────────────────────────────────────────────────────┘

head → [null|10|next] ⇄ [prev|20|next] ⇄ [prev|30|null] ← tail
```

#### LinkedList Example

```java
import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> tasks = new LinkedList<>();
        
        // Adding at different positions
        tasks.add("Task C");                    // [Task C]
        tasks.addFirst("Task A");               // [Task A, Task C]
        tasks.addLast("Task D");                // [Task A, Task C, Task D]
        tasks.add(1, "Task B");                 // [Task A, Task B, Task C, Task D]
        
        System.out.println("Tasks: " + tasks);
        
        // Accessing first and last
        System.out.println("First: " + tasks.getFirst());
        System.out.println("Last: " + tasks.getLast());
        
        // Peek without removing
        System.out.println("Peek: " + tasks.peek());
        
        // Queue operations
        tasks.offer("Task E");                  // Add to end
        System.out.println("After offer: " + tasks);
        
        String removed = tasks.poll();          // Remove from front
        System.out.println("Polled: " + removed);
        System.out.println("After poll: " + tasks);
        
        // Stack operations
        tasks.push("Urgent Task");              // Add to front
        System.out.println("After push: " + tasks);
        
        String popped = tasks.pop();            // Remove from front
        System.out.println("Popped: " + popped);
        System.out.println("After pop: " + tasks);
    }
}
```

**Output:**
```
Tasks: [Task A, Task B, Task C, Task D]
First: Task A
Last: Task D
Peek: Task A
After offer: [Task A, Task B, Task C, Task D, Task E]
Polled: Task A
After poll: [Task B, Task C, Task D, Task E]
After push: [Urgent Task, Task B, Task C, Task D, Task E]
Popped: Urgent Task
After pop: [Task B, Task C, Task D, Task E]
```

---

### 3.3 Vector

**Thread-Safe Version** of ArrayList (synchronized)  
**Default Capacity:** 10  
**Growth:** Doubles (100%) when full

#### ArrayList vs Vector

```
┌─────────────────────┬──────────────┬─────────────┐
│ Feature             │ ArrayList    │ Vector      │
├─────────────────────┼──────────────┼─────────────┤
│ Synchronized        │ No           │ Yes         │
│ Thread-Safe         │ No           │ Yes         │
│ Performance         │ Faster       │ Slower      │
│ Growth Rate         │ 50%          │ 100%        │
│ Legacy              │ No (JDK 1.2) │Yes (JDK 1.0)│
└─────────────────────┴──────────────┴─────────────┘
```

**⚠️ Recommendation:** Use ArrayList for single-threaded, Collections.synchronizedList() for multi-threaded.

#### Vector Example

```java
import java.util.*;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<Double> prices = new Vector<>();
        
        // Vector-specific methods
        prices.addElement(19.99);    // Legacy method
        prices.addElement(29.99);
        prices.addElement(39.99);
        
        System.out.println("Prices: " + prices);
        System.out.println("Capacity: " + prices.capacity());
        
        // Accessing
        System.out.println("First element: " + prices.firstElement());
        System.out.println("Last element: " + prices.lastElement());
        System.out.println("Element at 1: " + prices.elementAt(1));
    }
}
```

---

### 3.4 ArrayList vs LinkedList - When to Use What?

```
┌────────────────────────┬──────────────┬──────────────┐
│ Scenario               │ ArrayList    │ LinkedList   │
├────────────────────────┼──────────────┼──────────────┤
│ Random access          │ ✅ Use       │ ❌ Avoid      
│ Sequential access      │ ✅ Good      │ ✅ Good      
│ Insert at beginning    │ ❌ Slow      │ ✅ Fast      
│ Insert at end          │ ✅ Fast      │ ✅ Fast      
│ Insert in middle       │ ❌ Slow      │ ❌ Slow      
│ Delete at beginning    │ ❌ Slow      │ ✅ Fast      
│ Delete at end          │ ✅ Fast      │ ✅ Fast      
│ Memory usage           │ ✅ Less      │ ❌ More      
│ Queue/Deque impl       │ ❌ Not ideal │ ✅ Perfect   
└────────────────────────┴──────────────┴──────────────┘
```

---

## 4. Sets

### What is a Set?
A **Set** is a collection that:
- **Does NOT allow duplicates**
- **No guaranteed order** (depends on implementation)
- **Fast lookup, insertion, and deletion**

### Set Hierarchy

```
        ┌──────────┐
        │   Set    │
        └─────┬────┘
              │
    ┌─────────┼──────────┬────────────┐
    │         │          │            │
HashSet  LinkedHashSet TreeSet  SortedSet
```

---

### 4.1 HashSet

**Implementation:** Hash table (internally uses HashMap)  
**Order:** No guaranteed order  
**Default Capacity:** 16  
**Load Factor:** 0.75

#### When to Use HashSet?
✅ Need unique elements  
✅ Fast lookup/insertion/deletion  
✅ Order doesn't matter  
❌ Need sorted elements

#### Time Complexity

```
┌──────────────────────┬──────────────┐
│ Operation            │ Complexity   │
├──────────────────────┼──────────────┤
│ add(element)         │ O(1)         │
│ remove(element)      │ O(1)         │
│ contains(element)    │ O(1)         │
│ size()               │ O(1)         │
└──────────────────────┴──────────────┘
```

#### HashSet Example

```java
import java.util.*;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> cities = new HashSet<>();
        
        // Adding elements
        cities.add("Tokyo");
        cities.add("Paris");
        cities.add("London");
        cities.add("New York");
        cities.add("Tokyo");        // Duplicate - ignored
        
        System.out.println("Cities: " + cities);
        System.out.println("Size: " + cities.size());
        
        // Checking existence
        System.out.println("Contains Paris? " + cities.contains("Paris"));
        
        // Removing
        cities.remove("London");
        System.out.println("After removing London: " + cities);
        
        // Iterating (order not guaranteed)
        System.out.println("\nIterating:");
        for (String city : cities) {
            System.out.println("- " + city);
        }
        
        // Set operations
        HashSet<String> europeanCities = new HashSet<>();
        europeanCities.add("Paris");
        europeanCities.add("Berlin");
        europeanCities.add("Rome");
        
        // Union
        HashSet<String> allCities = new HashSet<>(cities);
        allCities.addAll(europeanCities);
        System.out.println("\nUnion: " + allCities);
        
        // Intersection
        HashSet<String> common = new HashSet<>(cities);
        common.retainAll(europeanCities);
        System.out.println("Intersection: " + common);
    }
}
```

**Output:**
```
Cities: [Tokyo, New York, Paris]
Size: 3
Contains Paris? true
After removing London: [Tokyo, New York, Paris]

Iterating:
- Tokyo
- New York
- Paris

Union: [Tokyo, New York, Paris, Berlin, Rome]
Intersection: [Paris]
```

---

### 4.2 LinkedHashSet

**Implementation:** Hash table + Linked list  
**Order:** **Insertion order maintained**  
**Performance:** Slightly slower than HashSet

#### When to Use LinkedHashSet?
✅ Need unique elements  
✅ Need to maintain insertion order  
✅ Fast operations (almost as fast as HashSet)

#### LinkedHashSet Example

```java
import java.util.*;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        LinkedHashSet<Integer> scores = new LinkedHashSet<>();
        
        // Adding in specific order
        scores.add(85);
        scores.add(92);
        scores.add(78);
        scores.add(95);
        scores.add(85);  // Duplicate ignored
        
        System.out.println("Scores (insertion order): " + scores);
        
        // Order is preserved during iteration
        System.out.println("\nScores in order:");
        for (int score : scores) {
            System.out.println(score);
        }
    }
}
```

**Output:**
```
Scores (insertion order): [85, 92, 78, 95]

Scores in order:
85
92
78
95
```

---

### 4.3 TreeSet

**Implementation:** Red-Black Tree (self-balancing BST)  
**Order:** **Sorted order** (natural or custom)  
**Null:** Not allowed (NullPointerException)

#### When to Use TreeSet?
✅ Need sorted elements  
✅ Need range operations (subSet, headSet, tailSet)  
✅ Need first/last element access  
❌ Need fastest performance (use HashSet)

#### Time Complexity

```
┌──────────────────────┬──────────────┐
│ Operation            │ Complexity   │
├──────────────────────┼──────────────┤
│ add(element)         │ O(log n)     │
│ remove(element)      │ O(log n)     │
│ contains(element)    │ O(log n)     │
│ first()              │ O(log n)     │
│ last()               │ O(log n)     │
└──────────────────────┴──────────────┘
```

#### TreeSet Example

```java
import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        
        // Adding elements (automatically sorted)
        numbers.add(50);
        numbers.add(20);
        numbers.add(80);
        numbers.add(10);
        numbers.add(40);
        
        System.out.println("Sorted numbers: " + numbers);
        
        // Navigation methods
        System.out.println("First: " + numbers.first());
        System.out.println("Last: " + numbers.last());
        System.out.println("Lower than 50: " + numbers.lower(50));
        System.out.println("Higher than 50: " + numbers.higher(50));
        System.out.println("Floor of 45: " + numbers.floor(45));
        System.out.println("Ceiling of 45: " + numbers.ceiling(45));
        
        // Range operations
        System.out.println("HeadSet (<40): " + numbers.headSet(40));
        System.out.println("TailSet (>=40): " + numbers.tailSet(40));
        System.out.println("SubSet [20,80): " + numbers.subSet(20, 80));
        
        // Poll operations (remove and return)
        System.out.println("\nPoll first: " + numbers.pollFirst());
        System.out.println("After pollFirst: " + numbers);
        
        System.out.println("Poll last: " + numbers.pollLast());
        System.out.println("After pollLast: " + numbers);
    }
}
```

**Output:**
```
Sorted numbers: [10, 20, 40, 50, 80]
First: 10
Last: 80
Lower than 50: 40
Higher than 50: 80
Floor of 45: 40
Ceiling of 45: 50
HeadSet (<40): [10, 20]
TailSet (>=40): [40, 50, 80]
SubSet [20,80): [20, 40, 50]

Poll first: 10
After pollFirst: [20, 40, 50, 80]
Poll last: 80
After pollLast: [20, 40, 50]
```

---

### 4.4 Set Comparison

```
┌────────────────┬─────────────┬──────────────────┬────────────┐
│ Feature        │ HashSet     │ LinkedHashSet    │ TreeSet    │
├────────────────┼─────────────┼──────────────────┼────────────┤
│ Order          │ No order    │ Insertion order  │ Sorted     │
│ Performance    │ O(1)        │ O(1)             │ O(log n)   │
│ Null allowed   │ Yes (1)     │ Yes (1)          │ No         │
│ Memory         │ Less        │ More             │ More       │
│ Use case       │ Fast lookup │ Ordered uniqueness│ Sorted set│
└────────────────┴─────────────┴──────────────────┴────────────┘
```

---

## 5. Maps

### What is a Map?
A **Map** stores **key-value pairs** where:
- Each **key** is unique
- Each key maps to **one value**
- Keys can be any object type
- Fast lookup by key

```
┌─────────────────────────────────────┐
│        Map Structure                │
├─────────────────────────────────────┤
│  Key       →      Value             │
├─────────────────────────────────────┤
│  "Alice"   →      95                │
│  "Bob"     →      87                │
│  "Charlie" →      92                │
└─────────────────────────────────────┘
```

### Map Hierarchy

```
        ┌──────────┐
        │   Map    │
        └─────┬────┘
              │
    ┌─────────┼──────────┬────────────┐
    │         │          │            │
HashMap  LinkedHashMap TreeMap  SortedMap
```

---

### 5.1 HashMap

**Implementation:** Hash table  
**Order:** No guaranteed order  
**Null:** Allows 1 null key, multiple null values  
**Default Capacity:** 16  
**Load Factor:** 0.75

#### When to Use HashMap?
✅ Fast key-value lookup  
✅ Order doesn't matter  
✅ Most common Map choice  
❌ Need sorted keys

#### Time Complexity

```
┌──────────────────────┬──────────────┐
│ Operation            │ Complexity   │
├──────────────────────┼──────────────┤
│ put(key, value)      │ O(1)         │
│ get(key)             │ O(1)         │
│ remove(key)          │ O(1)         │
│ containsKey(key)     │ O(1)         │
└──────────────────────┴──────────────┘
```

#### HashMap Example

```java
import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> studentGrades = new HashMap<>();
        
        // Adding key-value pairs
        studentGrades.put("Alice", 95);
        studentGrades.put("Bob", 87);
        studentGrades.put("Charlie", 92);
        studentGrades.put("Diana", 88);
        
        System.out.println("Student Grades: " + studentGrades);
        
        // Accessing values
        int aliceGrade = studentGrades.get("Alice");
        System.out.println("Alice's grade: " + aliceGrade);
        
        // Check if key exists
        System.out.println("Contains Bob? " + studentGrades.containsKey("Bob"));
        System.out.println("Contains Eve? " + studentGrades.containsKey("Eve"));
        
        // Check if value exists
        System.out.println("Contains grade 92? " + studentGrades.containsValue(92));
        
        // Update value
        studentGrades.put("Alice", 98);  // Updates existing key
        System.out.println("After update: " + studentGrades.get("Alice"));
        
        // Remove entry
        studentGrades.remove("Diana");
        System.out.println("After remove: " + studentGrades);
        
        // Get or default
        int eveGrade = studentGrades.getOrDefault("Eve", 0);
        System.out.println("Eve's grade (default): " + eveGrade);
        
        // Iterating through keys
        System.out.println("\nAll students:");
        for (String student : studentGrades.keySet()) {
            System.out.println(student + ": " + studentGrades.get(student));
        }
        
        // Iterating through entries
        System.out.println("\nUsing entrySet:");
        for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
            System.out.println(entry.getKey() + " scored " + entry.getValue());
        }
        
        // Size operations
        System.out.println("\nTotal students: " + studentGrades.size());
        System.out.println("Is empty? " + studentGrades.isEmpty());
    }
}
```

**Output:**
```
Student Grades: {Alice=95, Bob=87, Charlie=92, Diana=88}
Alice's grade: 95
Contains Bob? true
Contains Eve? false
Contains grade 92? true
After update: 98
After remove: {Alice=98, Bob=87, Charlie=92}
Eve's grade (default): 0

All students:
Alice: 98
Bob: 87
Charlie: 92

Using entrySet:
Alice scored 98
Bob scored 87
Charlie scored 92

Total students: 3
Is empty? false
```

---

### 5.2 LinkedHashMap

**Implementation:** Hash table + Doubly linked list  
**Order:** **Insertion order maintained**  
**Performance:** Slightly slower than HashMap

#### LinkedHashMap Example

```java
import java.util.*;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<String, String> capitals = new LinkedHashMap<>();
        
        // Insertion order is maintained
        capitals.put("USA", "Washington DC");
        capitals.put("UK", "London");
        capitals.put("France", "Paris");
        capitals.put("Japan", "Tokyo");
        
        System.out.println("Capitals (insertion order):");
        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
```

**Output:**
```
Capitals (insertion order):
USA -> Washington DC
UK -> London
France -> Paris
Japan -> Tokyo
```

---

### 5.3 TreeMap

**Implementation:** Red-Black Tree  
**Order:** **Sorted by keys** (natural or custom)  
**Null:** No null keys (NullPointerException)

#### When to Use TreeMap?
✅ Need sorted keys  
✅ Need range operations  
✅ Need first/last key access  
❌ Need fastest performance

#### TreeMap Example

```java
import java.util.*;

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> employees = new TreeMap<>();
        
        // Adding entries (automatically sorted by key)
        employees.put(105, "Eve");
        employees.put(101, "Alice");
        employees.put(103, "Charlie");
        employees.put(102, "Bob");
        employees.put(104, "Diana");
        
        System.out.println("Employees (sorted by ID): " + employees);
        
        // Navigation methods
        System.out.println("First entry: " + employees.firstEntry());
        System.out.println("Last entry: " + employees.lastEntry());
        System.out.println("Lower key than 103: " + employees.lowerKey(103));
        System.out.println("Higher key than 103: " + employees.higherKey(103));
        
        // Range views
        System.out.println("HeadMap (<103): " + employees.headMap(103));
        System.out.println("TailMap (>=103): " + employees.tailMap(103));
        System.out.println("SubMap [102,105): " + employees.subMap(102, 105));
        
        // Descending order
        System.out.println("\nDescending order:");
        NavigableMap<Integer, String> reverse = employees.descendingMap();
        System.out.println(reverse);
    }
}
```

**Output:**
```
Employees (sorted by ID): {101=Alice, 102=Bob, 103=Charlie, 104=Diana, 105=Eve}
First entry: 101=Alice
Last entry: 105=Eve
Lower key than 103: 102
Higher key than 103: 104
HeadMap (<103): {101=Alice, 102=Bob}
TailMap (>=103): {103=Charlie, 104=Diana, 105=Eve}
SubMap [102,105): {102=Bob, 103=Charlie, 104=Diana}

Descending order:
{105=Eve, 104=Diana, 103=Charlie, 102=Bob, 101=Alice}
```

---

### 5.4 Map Comparison

```
┌────────────────┬─────────────┬──────────────────┬────────────┐
│ Feature        │ HashMap     │ LinkedHashMap    │ TreeMap    │
├────────────────┼─────────────┼──────────────────┼────────────┤
│ Order          │ No order    │ Insertion order  │ Sorted     │
│ Performance    │ O(1)        │ O(1)             │ O(log n)   │
│ Null key       │ 1 allowed   │ 1 allowed        │ Not allowed│
│ Null values    │ Multiple    │ Multiple         │ Multiple   │
│ Memory         │ Less        │ More             │ More       │
│ Use case       │ General use │ Cache/LRU        │ Sorted map │
└────────────────┴─────────────┴──────────────────┴────────────┘
```

---

### 5.5 Advanced Map Operations

```java
import java.util.*;

public class AdvancedMapDemo {
    public static void main(String[] args) {
        HashMap<String, List<String>> courseStudents = new HashMap<>();
        
        // Adding lists as values
        courseStudents.put("Math", new ArrayList<>());
        courseStudents.get("Math").add("Alice");
        courseStudents.get("Math").add("Bob");
        
        courseStudents.put("Science", new ArrayList<>());
        courseStudents.get("Science").add("Charlie");
        courseStudents.get("Science").add("Alice");
        
        System.out.println("Course enrollments:");
        for (Map.Entry<String, List<String>> entry : courseStudents.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Using computeIfAbsent (Java 8+)
        HashMap<String, Integer> wordCount = new HashMap<>();
        String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};
        
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("\nWord count: " + wordCount);
        
        // Merge operation
        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        
        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("B", 3);
        map2.put("C", 4);
        
        // Merge map2 into map1
        map2.forEach((key, value) -> 
            map1.merge(key, value, Integer::sum)
        );
        
        System.out.println("\nAfter merge: " + map1);
    }
}
```

**Output:**
```
Course enrollments:
Math: [Alice, Bob]
Science: [Charlie, Alice]

Word count: {apple=3, banana=2, cherry=1}

After merge: {A=1, B=5, C=4}
```

---

## 6. Iterators

### What is an Iterator?
An **Iterator** is an object that allows you to traverse a collection sequentially.

```
┌──────────────────────────────────────────┐
│           Iterator Methods               │
├──────────────────────────────────────────┤
│  hasNext()  → Check if more elements     │
│  next()     → Get next element           │
│  remove()   → Remove current element     │
└──────────────────────────────────────────┘
```

### Iterator Types

```
        ┌─────────────┐
        │  Iterator   │
        └──────┬──────┘
               │
    ┌──────────┴──────────┐
    │                     │
Iterator              ListIterator
(forward only)     (bidirectional)
```

---

### 6.1 Iterator Example

```java
import java.util.*;

public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        
        // Using Iterator
        System.out.println("Using Iterator:");
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            String color = iterator.next();
            System.out.println(color);
            
            // Remove elements while iterating
            if (color.equals("Green")) {
                iterator.remove();  // Safe removal
            }
        }
        
        System.out.println("\nAfter removal: " + colors);
        
        // ❌ WRONG - ConcurrentModificationException
        // for (String color : colors) {
        //     if (color.equals("Blue")) {
        //         colors.remove(color);  // Throws exception!
        //     }
        // }
    }
}
```

**Output:**
```
Using Iterator:
Red
Green
Blue
Yellow

After removal: [Red, Blue, Yellow]
```

---

### 6.2 ListIterator Example

```java
import java.util.*;

public class ListIteratorDemo {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        
        // ListIterator can traverse in both directions
        ListIterator<Integer> listIterator = numbers.listIterator();
        
        System.out.println("Forward traversal:");
        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            int value = listIterator.next();
            System.out.println("Index " + index + ": " + value);
        }
        
        System.out.println("\nBackward traversal:");
        while (listIterator.hasPrevious()) {
            int index = listIterator.previousIndex();
            int value = listIterator.previous();
            System.out.println("Index " + index + ": " + value);
        }
        
        // Adding and setting elements
        listIterator = numbers.listIterator();
        while (listIterator.hasNext()) {
            int value = listIterator.next();
            if (value == 20) {
                listIterator.set(25);  // Replace 20 with 25
            }
            if (value == 30) {
                listIterator.add(35);  // Add 35 after 30
            }
        }
        
        System.out.println("\nAfter modifications: " + numbers);
    }
}
```

**Output:**
```
Forward traversal:
Index 0: 10
Index 1: 20
Index 2: 30
Index 3: 40

Backward traversal:
Index 3: 40
Index 2: 30
Index 1: 20
Index 0: 10

After modifications: [10, 25, 30, 35, 40]
```

---

### 6.3 Enhanced For Loop vs Iterator

```java
import java.util.*;

public class IterationComparison {
    public static void main(String[] args) {
        ArrayList<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("C++");
        
        // Method 1: Enhanced for loop (read-only)
        System.out.println("Enhanced for loop:");
        for (String lang : languages) {
            System.out.println(lang);
        }
        
        // Method 2: Iterator (can remove during iteration)
        System.out.println("\nIterator:");
        Iterator<String> it = languages.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
        // Method 3: forEach with lambda (Java 8+)
        System.out.println("\nforEach with lambda:");
        languages.forEach(lang -> System.out.println(lang));
        
        // Method 4: Traditional for loop (with index)
        System.out.println("\nTraditional for loop:");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println(languages.get(i));
        }
    }
}
```

---

## 7. Generics

### What are Generics?
**Generics** enable types to be parameters when defining classes, interfaces, and methods.

**Benefits:**
- ✅ Type safety at compile time
- ✅ No casting required
- ✅ Code reusability
- ✅ Cleaner and more expressive code

### Generic Class Example

```java
// Generic class with type parameter T
public class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
    
    public void display() {
        System.out.println("Content: " + content + 
                         " (Type: " + content.getClass().getSimpleName() + ")");
    }
}

// Usage
public class GenericDemo {
    public static void main(String[] args) {
        // Box for String
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Generics");
        String str = stringBox.get();  // No casting needed!
        stringBox.display();
        
        // Box for Integer
        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        int num = intBox.get();  // Auto-unboxing
        intBox.display();
        
        // Box for custom object
        Box<List<String>> listBox = new Box<>();
        listBox.set(Arrays.asList("A", "B", "C"));
        listBox.display();
    }
}
```

**Output:**
```
Content: Hello Generics (Type: String)
Content: 42 (Type: Integer)
Content: [A, B, C] (Type: ArrayList)
```

---

### Generic Method Example

```java
public class GenericMethodDemo {
    
    // Generic method with type parameter T
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Generic method to find max
    public static <T extends Comparable<T>> T findMax(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0) max = b;
        if (c.compareTo(max) > 0) max = c;
        return max;
    }
    
    public static void main(String[] args) {
        // Integer array
        Integer[] intArray = {1, 2, 3, 4, 5};
        System.out.print("Integer Array: ");
        printArray(intArray);
        
        // String array
        String[] strArray = {"Java", "Python", "C++"};
        System.out.print("String Array: ");
        printArray(strArray);
        
        // Find max
        System.out.println("Max integer: " + findMax(3, 7, 5));
        System.out.println("Max string: " + findMax("apple", "zebra", "banana"));
    }
}
```

**Output:**
```
Integer Array: 1 2 3 4 5 
String Array: Java Python C++ 
Max integer: 7
Max string: zebra
```

---

### Type Parameter Naming Conventions

```
┌─────────┬──────────────────────────────────┐
│ Symbol  │ Meaning                          │
├─────────┼──────────────────────────────────┤
│ E       │ Element (Collections)            │
│ K       │ Key (Maps)                       │
│ V       │ Value (Maps)                     │
│ N       │ Number                           │
│ T       │ Type (general)                   │
│ S,U,V   │ 2nd, 3rd, 4th types              │
└─────────┴──────────────────────────────────┘
```

---

### Multiple Type Parameters

```java
public class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
    
    @Override
    public String toString() {
        return key + " = " + value;
    }
}

// Usage
public class PairDemo {
    public static void main(String[] args) {
        Pair<String, Integer> agePair = new Pair<>("Alice", 25);
        System.out.println(agePair);
        
        Pair<Integer, String> idPair = new Pair<>(101, "Employee");
        System.out.println(idPair);
        
        Pair<String, List<Integer>> scoresPair = 
            new Pair<>("Scores", Arrays.asList(85, 90, 95));
        System.out.println(scoresPair);
    }
}
```

**Output:**
```
Alice = 25
101 = Employee
Scores = [85, 90, 95]
```

---

### Bounded Type Parameters

```java
public class BoundedGenericDemo {
    
    // T must be Number or its subclass
    public static <T extends Number> double sum(T num1, T num2) {
        return num1.doubleValue() + num2.doubleValue();
    }
    
    // T must implement Comparable
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) max = y;
        if (z.compareTo(max) > 0) max = z;
        return max;
    }
    
    public static void main(String[] args) {
        System.out.println("Sum: " + sum(10, 20.5));
        System.out.println("Sum: " + sum(5.5, 4.5));
        
        System.out.println("Max: " + maximum(3, 7, 5));
        System.out.println("Max: " + maximum("apple", "orange", "banana"));
    }
}
```

**Output:**
```
Sum: 30.5
Sum: 10.0
Max: 7
Max: orange
```

---

### Wildcards

```java
import java.util.*;

public class WildcardDemo {
    
    // Upper bounded wildcard (? extends Type)
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
    
    // Lower bounded wildcard (? super Type)
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }
    
    // Unbounded wildcard (?)
    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Upper bounded
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("Sum of integers: " + sumOfList(intList));
        System.out.println("Sum of doubles: " + sumOfList(doubleList));
        
        // Lower bounded
        List<Number> numList = new ArrayList<>();
        addNumbers(numList);
        System.out.println("Numbers: " + numList);
        
        // Unbounded
        List<String> stringList = Arrays.asList("A", "B", "C");
        printList(stringList);
        printList(intList);
    }
}
```

**Output:**
```
Sum of integers: 15.0
Sum of doubles: 6.6
Numbers: [1, 2, 3, 4, 5]
A B C 
1 2 3 4 5 
```

---

## 8. Method Cheat Sheet

### List Methods Quick Reference

```java
╔═══════════════════════════════════════════════════════════════╗
║                    ARRAYLIST METHODS                          ║
╚═══════════════════════════════════════════════════════════════╝

// Creating
ArrayList<String> list = new ArrayList<>();
ArrayList<String> list = new ArrayList<>(50);  // Initial capacity
ArrayList<String> list = new ArrayList<>(otherCollection);

// Adding
list.add("element");                    // Add at end
list.add(0, "element");                 // Add at index
list.addAll(collection);                // Add all from collection
list.addAll(0, collection);             // Add all at index

// Accessing
String item = list.get(0);              // Get element at index
int index = list.indexOf("element");    // First occurrence
int index = list.lastIndexOf("element");// Last occurrence
boolean exists = list.contains("elem"); // Check existence

// Modifying
list.set(0, "newElement");              // Replace at index
list.replaceAll(s -> s.toUpperCase());  // Transform all elements

// Removing
list.remove(0);                         // Remove by index
list.remove("element");                 // Remove by value
list.removeAll(collection);             // Remove all from collection
list.retainAll(collection);             // Keep only from collection
list.removeIf(s -> s.startsWith("A")); // Remove with condition
list.clear();                           // Remove all

// Size operations
int size = list.size();                 // Get size
boolean empty = list.isEmpty();         // Check if empty
list.ensureCapacity(100);               // Ensure capacity
list.trimToSize();                      // Trim to current size

// Searching & Sorting
Collections.sort(list);                 // Sort (natural order)
Collections.sort(list, comparator);     // Sort (custom)
Collections.reverse(list);              // Reverse order
Collections.shuffle(list);              // Random shuffle
int index = Collections.binarySearch(list, "key");  // Binary search

// Conversion
Object[] array = list.toArray();        // To Object array
String[] array = list.toArray(new String[0]);  // To typed array
List<String> subList = list.subList(1, 4);  // Get sublist [1,4)

// Iteration
for (String item : list) { }            // Enhanced for
list.forEach(item -> { });              // Lambda forEach
Iterator<String> it = list.iterator();  // Iterator
ListIterator<String> lit = list.listIterator();  // ListIterator
```

---

### LinkedList Additional Methods

```java
╔═══════════════════════════════════════════════════════════════╗
║              LINKEDLIST ADDITIONAL METHODS                    ║
╚═══════════════════════════════════════════════════════════════╝

LinkedList<String> list = new LinkedList<>();

// First/Last operations
list.addFirst("first");                 // Add at beginning
list.addLast("last");                   // Add at end
String first = list.getFirst();         // Get first
String last = list.getLast();           // Get last
list.removeFirst();                     // Remove first
list.removeLast();                      // Remove last

// Queue operations
list.offer("element");                  // Add to end (Queue)
String elem = list.poll();              // Remove from front (Queue)
String elem = list.peek();              // Get front without remove
boolean added = list.offerFirst("elem");// Add at front
boolean added = list.offerLast("elem"); // Add at end
String elem = list.pollFirst();         // Remove from front
String elem = list.pollLast();          // Remove from end
String elem = list.peekFirst();         // Peek at front
String elem = list.peekLast();          // Peek at end

// Stack operations
list.push("element");                   // Add to front (Stack)
String elem = list.pop();               // Remove from front (Stack)
```

---

### Set Methods Quick Reference

```java
╔═══════════════════════════════════════════════════════════════╗
║                      SET METHODS                              ║
╚═══════════════════════════════════════════════════════════════╝

// HashSet
HashSet<String> set = new HashSet<>();
HashSet<String> set = new HashSet<>(50);         // Initial capacity
HashSet<String> set = new HashSet<>(collection); // From collection

// LinkedHashSet (maintains insertion order)
LinkedHashSet<String> set = new LinkedHashSet<>();

// TreeSet (sorted order)
TreeSet<String> set = new TreeSet<>();
TreeSet<String> set = new TreeSet<>(comparator); // Custom order

// Adding
set.add("element");                     // Add element
set.addAll(collection);                 // Add all

// Checking
boolean exists = set.contains("elem");  // Check existence
boolean hasAll = set.containsAll(coll); // Check all exist

// Removing
set.remove("element");                  // Remove element
set.removeAll(collection);              // Remove all from collection
set.retainAll(collection);              // Keep only from collection
set.clear();                            // Remove all

// Size
int size = set.size();                  // Get size
boolean empty = set.isEmpty();          // Check if empty

// Set operations
Set<String> union = new HashSet<>(set1);
union.addAll(set2);                     // Union

Set<String> intersection = new HashSet<>(set1);
intersection.retainAll(set2);           // Intersection

Set<String> difference = new HashSet<>(set1);
difference.removeAll(set2);             // Difference

// TreeSet specific methods
String first = treeSet.first();         // First element
String last = treeSet.last();           // Last element
String lower = treeSet.lower("elem");   // Greatest < elem
String higher = treeSet.higher("elem"); // Least > elem
String floor = treeSet.floor("elem");   // Greatest <= elem
String ceiling = treeSet.ceiling("elem");// Least >= elem
String polled = treeSet.pollFirst();    // Remove and return first
String polled = treeSet.pollLast();     // Remove and return last

// Range operations
SortedSet<String> head = treeSet.headSet("elem");  // < elem
SortedSet<String> tail = treeSet.tailSet("elem");  // >= elem
SortedSet<String> sub = treeSet.subSet("from", "to"); // [from, to)

// Descending
NavigableSet<String> desc = treeSet.descendingSet();
```

---

### Map Methods Quick Reference

```java
╔═══════════════════════════════════════════════════════════════╗
║                      MAP METHODS                              ║
╚═══════════════════════════════════════════════════════════════╝

// HashMap
HashMap<String, Integer> map = new HashMap<>();
HashMap<String, Integer> map = new HashMap<>(50);  // Initial capacity
HashMap<String, Integer> map = new HashMap<>(otherMap);

// LinkedHashMap (maintains insertion order)
LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

// TreeMap (sorted by keys)
TreeMap<String, Integer> map = new TreeMap<>();
TreeMap<String, Integer> map = new TreeMap<>(comparator);

// Adding/Updating
map.put("key", 100);                    // Put key-value
map.putAll(otherMap);                   // Put all from map
map.putIfAbsent("key", 100);            // Put if key absent
map.replace("key", 200);                // Replace value
map.replace("key", 100, 200);           // Replace if current = 100

// Getting
Integer value = map.get("key");         // Get value
Integer value = map.getOrDefault("key", 0);  // Get or default

// Checking
boolean hasKey = map.containsKey("key");     // Check key exists
boolean hasValue = map.containsValue(100);   // Check value exists
boolean empty = map.isEmpty();               // Check if empty
int size = map.size();                       // Get size

// Removing
Integer removed = map.remove("key");         // Remove by key
boolean removed = map.remove("key", 100);    // Remove if value = 100
map.clear();                                 // Remove all

// Views
Set<String> keys = map.keySet();             // Get all keys
Collection<Integer> values = map.values();   // Get all values
Set<Map.Entry<String, Integer>> entries = map.entrySet();  // Get entries

// Iterating
for (String key : map.keySet()) {
    Integer value = map.get(key);
}

for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
    entry.setValue(newValue);                // Can modify value
}

map.forEach((key, value) -> {
    // Process key and value
});

// Compute operations (Java 8+)
map.compute("key", (k, v) -> v == null ? 1 : v + 1);
map.computeIfAbsent("key", k -> defaultValue);
map.computeIfPresent("key", (k, v) -> v + 1);
map.merge("key", 1, Integer::sum);           // Merge with function

// TreeMap specific methods
String firstKey = treeMap.firstKey();        // First key
String lastKey = treeMap.lastKey();          // Last key
Map.Entry<String, Integer> first = treeMap.firstEntry();
Map.Entry<String, Integer> last = treeMap.lastEntry();
Map.Entry<String, Integer> lower = treeMap.lowerEntry("key");
Map.Entry<String, Integer> higher = treeMap.higherEntry("key");

// Range operations
SortedMap<String, Integer> head = treeMap.headMap("key");
SortedMap<String, Integer> tail = treeMap.tailMap("key");
SortedMap<String, Integer> sub = treeMap.subMap("from", "to");

// Descending
NavigableMap<String, Integer> desc = treeMap.descendingMap();
```

---

### Iterator Methods

```java
╔═══════════════════════════════════════════════════════════════╗
║                  ITERATOR METHODS                             ║
╚═══════════════════════════════════════════════════════════════╝

// Iterator
Iterator<String> it = collection.iterator();
boolean hasMore = it.hasNext();         // Check if more elements
String element = it.next();             // Get next element
it.remove();                            // Remove current element

// ListIterator (bidirectional)
ListIterator<String> lit = list.listIterator();
ListIterator<String> lit = list.listIterator(index);  // Start at index

// Forward
boolean hasNext = lit.hasNext();        // Check if next exists
String next = lit.next();               // Get next
int nextIndex = lit.nextIndex();        // Get next index

// Backward
boolean hasPrev = lit.hasPrevious();    // Check if previous exists
String prev = lit.previous();           // Get previous
int prevIndex = lit.previousIndex();    // Get previous index

// Modification
lit.add("element");                     // Add at current position
lit.set("element");                     // Replace current element
lit.remove();                           // Remove current element

// Example: Safe removal during iteration
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String elem = it.next();
    if (condition) {
        it.remove();  // Safe removal
    }
}
```

---

### Collections Utility Methods

```java
╔═══════════════════════════════════════════════════════════════╗
║              COLLECTIONS UTILITY METHODS                      ║
╚═══════════════════════════════════════════════════════════════╝

import java.util.Collections;

// Sorting
Collections.sort(list);                             // Natural order
Collections.sort(list, comparator);                 // Custom order
Collections.sort(list, Collections.reverseOrder()); // Reverse order

// Searching
int index = Collections.binarySearch(list, "key");  // Binary search
int index = Collections.binarySearch(list, "key", comparator);

// Shuffling & Reversing
Collections.shuffle(list);                          // Random shuffle
Collections.reverse(list);                          // Reverse order
Collections.rotate(list, distance);                 // Rotate elements
Collections.swap(list, i, j);                       // Swap two elements

// Fill & Replace
Collections.fill(list, "value");                    // Fill with value
Collections.replaceAll(list, "old", "new");         // Replace all

// Min & Max
String min = Collections.min(collection);           // Find minimum
String max = Collections.max(collection);           // Find maximum
String min = Collections.min(collection, comparator);
String max = Collections.max(collection, comparator);

// Frequency & Disjoint
int count = Collections.frequency(collection, "elem"); // Count occurrences
boolean disjoint = Collections.disjoint(c1, c2);    // No common elements

// Copying
Collections.copy(dest, src);                        // Copy src to dest
List<String> copy = new ArrayList<>(original);      // Simple copy

// Creating immutable collections
List<String> immutable = Collections.unmodifiableList(list);
Set<String> immutable = Collections.unmodifiableSet(set);
Map<String, Integer> immutable = Collections.unmodifiableMap(map);

// Creating synchronized collections
List<String> syncList = Collections.synchronizedList(list);
Set<String> syncSet = Collections.synchronizedSet(set);
Map<String, Integer> syncMap = Collections.synchronizedMap(map);

// Singleton collections
Set<String> singleton = Collections.singleton("elem");
List<String> singletonList = Collections.singletonList("elem");
Map<String, Integer> singletonMap = Collections.singletonMap("key", 1);

// Empty collections
List<String> empty = Collections.emptyList();
Set<String> empty = Collections.emptySet();
Map<String, Integer> empty = Collections.emptyMap();

// Adding multiple elements
Collections.addAll(collection, "elem1", "elem2", "elem3");

// Check if sorted
boolean sorted = Collections.isSorted(list);  // Custom method
```

---

### Performance Comparison Table

```
╔════════════════════════════════════════════════════════════════════╗
║              TIME COMPLEXITY COMPARISON                            ║
╠════════════════════════════════════════════════════════════════════╣
║ Operation          │ ArrayList │ LinkedList │ HashSet │ TreeSet    ║
╠════════════════════╪═══════════╪════════════╪═════════╪════════════╣
║ add(element)       │ O(1)*     │ O(1)       │ O(1)    │ O(log n)   ║
║ add(index, elem)   │ O(n)      │ O(n)       │ N/A     │ N/A        ║
║ get(index)         │ O(1)      │ O(n)       │ N/A     │ N/A        ║
║ remove(index)      │ O(n)      │ O(n)       │ N/A     │ N/A        ║
║ remove(element)    │ O(n)      │ O(n)       │ O(1)    │ O(log n)   ║
║ contains(element)  │ O(n)      │ O(n)       │ O(1)    │ O(log n)   ║
║ iterator.next()    │ O(1)      │ O(1)       │ O(1)    │ O(log n)   ║
║ size()             │ O(1)      │ O(1)       │ O(1)    │ O(1)       ║
╠════════════════════════════════════════════════════════════════════╣
║ * Amortized constant time                                          ║
╚════════════════════════════════════════════════════════════════════╝

╔════════════════════════════════════════════════════════════════════╗
║                      MAP OPERATIONS                                ║
╠════════════════════════════════════════════════════════════════════╣
║ Operation          │ HashMap   │ LinkedHashMap │ TreeMap           ║
╠════════════════════╪═══════════╪═══════════════╪═══════════════════╣
║ put(key, value)    │ O(1)      │ O(1)          │ O(log n)          ║
║ get(key)           │ O(1)      │ O(1)          │ O(log n)          ║ 
║ remove(key)        │ O(1)      │ O(1)          │ O(log n)          ║
║ containsKey(key)   │ O(1)      │ O(1)          │ O(log n)          ║
║ Iteration order    │ Random    │ Insertion     │ Sorted            ║
╚════════════════════════════════════════════════════════════════════╝
```

---

### Common Pitfalls and Best Practices

```
╔═══════════════════════════════════════════════════════════════╗
║                  COMMON PITFALLS                              ║
╚═══════════════════════════════════════════════════════════════╝

❌ DON'T:
1. Modify collection during iteration (use Iterator.remove())
   for (String s : list) {
       list.remove(s);  // ConcurrentModificationException!
   }

2. Compare objects with ==
   if (str1 == str2)  // Compares references, not values!

3. Use raw types
   ArrayList list = new ArrayList();  // No type safety!

4. Ignore initial capacity for large collections
   ArrayList<String> list = new ArrayList<>();  // Defaults to 10

5. Use Vector or Hashtable
   Vector<String> v = new Vector<>();  // Legacy, use ArrayList


✅ DO:
1. Use Iterator for safe removal
   Iterator<String> it = list.iterator();
   while (it.hasNext()) {
       if (condition) it.remove();
   }

2. Use .equals() for comparison
   if (str1.equals(str2))  // Compares values

3. Always use generics
   ArrayList<String> list = new ArrayList<>();

4. Set initial capacity if known
   ArrayList<String> list = new ArrayList<>(1000);

5. Choose right collection for the task
   - ArrayList for fast access
   - LinkedList for frequent insertions
   - HashSet for uniqueness
   - TreeSet for sorted uniqueness
   - HashMap for key-value pairs
```

---

### Real-World Use Cases

```java
╔═══════════════════════════════════════════════════════════════╗
║                 REAL-WORLD EXAMPLES                           ║
╚═══════════════════════════════════════════════════════════════╝

// 1. Shopping Cart (List allows duplicates)
ArrayList<String> cart = new ArrayList<>();
cart.add("Laptop");
cart.add("Mouse");
cart.add("Mouse");  // Can buy multiple

// 2. Unique visitor tracking (Set)
HashSet<String> visitors = new HashSet<>();
visitors.add("user123");
visitors.add("user456");
visitors.add("user123");  // Duplicate ignored
System.out.println("Unique visitors: " + visitors.size());

// 3. Priority Queue (TreeSet for sorted)
TreeSet<Task> tasks = new TreeSet<>();
tasks.add(new Task("High", "Fix bug"));
tasks.add(new Task("Low", "Update docs"));
// Tasks automatically sorted by priority

// 4. LRU Cache (LinkedHashMap)
LinkedHashMap<String, String> cache = new LinkedHashMap<>(16, 0.75f, true);

// 5. Phone Directory (HashMap)
HashMap<String, String> directory = new HashMap<>();
directory.put("Alice", "555-1234");
directory.put("Bob", "555-5678");
String number = directory.get("Alice");

// 6. Word Frequency Counter
HashMap<String, Integer> wordCount = new HashMap<>();
String[] words = {"apple", "banana", "apple", "cherry"};
for (String word : words) {
    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
}

// 7. Student Grade Book (TreeMap for sorted names)
TreeMap<String, ArrayList<Integer>> gradeBook = new TreeMap<>();
gradeBook.put("Alice", new ArrayList<>(Arrays.asList(90, 85, 92)));
gradeBook.put("Bob", new ArrayList<>(Arrays.asList(78, 82, 88)));

// 8. Task Queue (LinkedList as Queue)
LinkedList<String> taskQueue = new LinkedList<>();
taskQueue.offer("Task1");  // Add to end
taskQueue.offer("Task2");
String task = taskQueue.poll();  // Remove from front

// 9. Undo Stack (LinkedList as Stack)
LinkedList<String> undoStack = new LinkedList<>();
undoStack.push("Action1");  // Add to front
undoStack.push("Action2");
String undo = undoStack.pop();  // Remove from front

// 10. Removing duplicates from List
List<String> listWithDupes = Arrays.asList("A", "B", "A", "C", "B");
Set<String> uniqueSet = new LinkedHashSet<>(listWithDupes);
List<String> uniqueList = new ArrayList<>(uniqueSet);
```

---

## Summary

```
╔═══════════════════════════════════════════════════════════════╗
║               QUICK DECISION GUIDE                            ║
╠═══════════════════════════════════════════════════════════════╣
║                                                               ║
║  Need index-based access?                                     ║
║  └─ Yes → ArrayList                                           ║
║  └─ No  → Continue                                            ║
║                                                               ║
║  Need unique elements only?                                   ║
║  └─ Yes → Continue                                            ║
║  │  ├─ Need sorted? → TreeSet                                 ║ 
║  │  ├─ Need insertion order? → LinkedHashSet                  ║
║  │  └─ Fastest? → HashSet                                     ║
║  └─ No → Continue                                             ║
║                                                               ║
║  Frequent insertions/deletions at beginning?                  ║
║  └─ Yes → LinkedList                                          ║
║  └─ No  → ArrayList                                           ║
║                                                               ║
║  Need key-value pairs?                                        ║
║  └─ Yes → Continue                                            ║
║     ├─ Need sorted keys? → TreeMap                            ║
║     ├─ Need insertion order? → LinkedHashMap                  ║
║     └─ Fastest? → HashMap                                     ║
║                                                               ║
║  Need FIFO (Queue)?                                           ║
║  └─ Yes → LinkedList or PriorityQueue                         ║
║                                                               ║
║  Need LIFO (Stack)?                                           ║
║  └─ Yes → LinkedList or Stack (legacy)                        ║
║                                                               ║
╚═══════════════════════════════════════════════════════════════╝
```

---

```
╔═══════════════════════════════════════════════════════════════╗
║                     END OF NOTES                              ║
║                                                               ║
║   Remember: Choose the right collection for your use case!    ║
║             Practice makes perfect!                           ║
╚═══════════════════════════════════════════════════════════════╝
```
