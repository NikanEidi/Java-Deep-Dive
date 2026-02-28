# 🖥️ Complete JavaFX & GUI Programming — Master Study Guide

> **Scope:** User Interfaces · JavaFX Architecture · Components · Containers · Layouts · Event Handling  
> **Audience:** Students learning GUI application development from scratch  
> **Style:** Concept → Why it matters → Analogy → Original Code → Pseudocode → Flow

---

## 📋 Table of Contents

1. [Understanding User Interfaces](#1-understanding-user-interfaces)
2. [The Model–UI Separation Principle](#2-the-modelui-separation-principle)
3. [What is a GUI?](#3-what-is-a-gui)
4. [History: AWT → Swing → JavaFX](#4-history-awt--swing--javafx)
5. [What is JavaFX?](#5-what-is-javafx)
6. [JavaFX vs Swing — A Comparison](#6-javafx-vs-swing--a-comparison)
7. [JavaFX Window Architecture: Stage, Scene & Nodes](#7-javafx-window-architecture-stage-scene--nodes)
8. [Scene Graph — The Tree of Visual Elements](#8-scene-graph--the-tree-of-visual-elements)
9. [FXML & Scene Builder](#9-fxml--scene-builder)
10. [JavaFX Project Structure in Eclipse](#10-javafx-project-structure-in-eclipse)
11. [Window Components In Depth](#11-window-components-in-depth)
12. [Layout Containers — Organizing Your UI](#12-layout-containers--organizing-your-ui)
13. [Grouping Components with Custom Panes](#13-grouping-components-with-custom-panes)
14. [Event Handling — Making the UI Interactive](#14-event-handling--making-the-ui-interactive)
15. [ActionEvent — Button & Field Interactions](#15-actionevent--button--field-interactions)
16. [MouseEvent — Responding to Mouse Input](#16-mouseevent--responding-to-mouse-input)
17. [KeyEvent — Responding to Keyboard Input](#17-keyevent--responding-to-keyboard-input)
18. [Shared Event Handlers — One Handler, Many Sources](#18-shared-event-handlers--one-handler-many-sources)
19. [Full Application Walkthrough — Calculator](#19-full-application-walkthrough--calculator)
20. [Quick Reference Cheat Sheet](#20-quick-reference-cheat-sheet)

---

# 1. Understanding User Interfaces

## What Is a User Interface?

A **user interface (UI)** is the boundary between a human and a system — it is the layer through which a user gives input to a program and receives output back. Without a UI, software would be inaccessible to most people.

### The Two Dimensions of Interaction

Every UI must support two fundamental directions of communication:

| Direction | Description | Example |
|-----------|-------------|---------|
| **Input** | User → System | Typing a password, clicking a button |
| **Output** | System → User | Displaying search results, showing an error message |

### Real-World Analogy 🏦

Think about interacting with your bank account. You have two physical interfaces:

1. **The Bank Teller** — A human intermediary. You speak to them (input), and they hand you cash or a statement (output).
2. **The ATM** — A machine interface. You press buttons (input), and the screen shows your balance or a slot dispenses cash (output).

Both interfaces talk to the same underlying system (your bank account), but through completely different surfaces. This is a crucial insight: **the underlying data (your account) is separate from the interface used to access it.**

In software, the same principle holds. A mobile banking app, a website, and a desktop program can all talk to the same database but look completely different.

### Types of User Interfaces

```
User Interfaces
│
├── Physical / Tangible
│   ├── ATM keypads
│   ├── Car dashboards
│   └── Elevator buttons
│
├── Text-Based (CLI)
│   ├── Command-line terminal
│   ├── Java console (System.out.println)
│   └── SSH sessions
│
└── Graphical (GUI)
    ├── Desktop apps (Windows, macOS, Linux)
    ├── Mobile apps (Android, iOS)
    └── Web apps (browsers)
```

### Why Does This Matter in Java?

Before learning JavaFX, all your Java programs likely ran in the **console** — you called `System.out.println()` and stared at text. That is a valid, minimal UI. However:

- Console output is sequential and not interactive in real-time.
- Users cannot click, drag, resize, or navigate visually.
- It gives no indication of the "shape" or "structure" of the data.

JavaFX solves all of these by providing a full **Graphical User Interface** toolkit.

---

# 2. The Model–UI Separation Principle

## The Most Important Architectural Rule

Before writing a single line of JavaFX code, you must understand one golden rule:

> **The Model and the User Interface must be completely separate.**

### Defining the Two Layers

#### The Model (Business Logic Layer)
The **model** is the brain of your application. It contains all classes that represent real-world concepts and computations — completely independent of how the result will be displayed.

- A `BankAccount` class with `deposit()` and `withdraw()` methods → **Model**
- A `Student` class with `calculateGPA()` → **Model**
- A `ShoppingCart` class with `addItem()` and `getTotal()` → **Model**

**Critical rule:** Model classes must **never** assume how they'll be shown. They must never call `System.out.println()`, create buttons, or reference any JavaFX class.

#### The User Interface (Presentation Layer)
The **UI** is the face of your application. It contains JavaFX classes that show data to the user and collect user input, then delegates work to the model.

- A window with text fields to enter a bank account number → **UI**
- A button that calls `account.deposit(amount)` → **UI**
- A label that displays `account.getBalance()` → **UI**

### Why Separate Them?

```
╔═══════════════════════════════════════════════════════════════╗
║  WITHOUT SEPARATION                                           ║
║                                                               ║
║  - Change the UI? Must rewrite business logic too.            ║
║  - Want a mobile version? Everything must be rewritten.       ║
║  - Want to test logic? Must fire up the entire window.        ║
╠═══════════════════════════════════════════════════════════════╣
║  WITH SEPARATION                                              ║
║                                                               ║
║  - Swap out the UI? Model stays untouched.                    ║
║  - Add a web UI? Same model, new UI layer.                    ║
║  - Unit test business logic? No UI needed at all.             ║
╚═══════════════════════════════════════════════════════════════╝
```

### Real-World Analogy 🍽️

Think of a restaurant:
- The **kitchen** (model) prepares the food. It doesn't know if the customer will sit inside, order via app, or take out.
- The **waiter** (UI) takes the order from the customer and brings it to the kitchen, then delivers the result.

The kitchen has no idea what the dining room looks like. The dining room doesn't know how to cook. They communicate through a clearly defined interface.

### Illustrated Architecture

```
┌─────────────────────┐         ┌──────────────────────────┐
│     MODEL LAYER     │◄───────►│      UI LAYER (JavaFX)   │
│                     │interact │                          │
│  • BankAccount.java │         │  • MainWindow.java       │
│  • Customer.java    │         │  • AccountController.java│
│  • Transaction.java │         │  • (FXML files)          │
│                     │         │                          │
│  No JavaFX imports! │         │  Calls model methods     │
│  No System.out!     │         │  Displays model data     │
└─────────────────────┘         └──────────────────────────┘
```

### Original Code Example — Correct Separation

```java
// =============================================
// MODEL CLASS — knows nothing about UI
// =============================================
public class TemperatureConverter {

    private double celsius;

    public TemperatureConverter(double celsius) {
        this.celsius = celsius;
    }

    // Pure logic — no UI dependency whatsoever
    public double toCelsius() {
        return celsius;
    }

    public double toFahrenheit() {
        return (celsius * 9.0 / 5.0) + 32;
    }

    public double toKelvin() {
        return celsius + 273.15;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }
}
```

```java
// =============================================
// UI CLASS — uses the model, displays results
// =============================================
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConverterApp extends Application {

    // UI components stored as instance variables
    TextField celsiusInput;
    Label fahrenheitLabel, kelvinLabel;

    // The MODEL — the UI uses it but doesn't become it
    TemperatureConverter converter = new TemperatureConverter(0);

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();

        Label prompt = new Label("Enter °C:");
        prompt.relocate(10, 10);
        prompt.setPrefSize(80, 30);

        celsiusInput = new TextField("0");
        celsiusInput.relocate(100, 10);
        celsiusInput.setPrefSize(120, 30);

        Button convertBtn = new Button("Convert");
        convertBtn.relocate(10, 50);
        convertBtn.setPrefSize(210, 30);

        fahrenheitLabel = new Label("°F: —");
        fahrenheitLabel.relocate(10, 90);
        fahrenheitLabel.setPrefSize(210, 30);

        kelvinLabel = new Label("K: —");
        kelvinLabel.relocate(10, 130);
        kelvinLabel.setPrefSize(210, 30);

        // Wire up the event — UI calls MODEL methods
        convertBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                double c = Double.parseDouble(celsiusInput.getText());
                converter.setCelsius(c);  // Tell the model

                // Ask the model for results, display them
                fahrenheitLabel.setText("°F: " + converter.toFahrenheit());
                kelvinLabel.setText("K: " + converter.toKelvin());
            }
        });

        pane.getChildren().addAll(prompt, celsiusInput, convertBtn,
                                   fahrenheitLabel, kelvinLabel);

        stage.setTitle("Temperature Converter");
        stage.setScene(new Scene(pane, 230, 170));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

---

# 3. What is a GUI?

## Graphical User Interface — Definition

A **GUI (Graphical User Interface)** is a type of user interface that communicates with the user through **visual elements** — windows, icons, buttons, menus, and other graphical components — rather than plain text commands.

### Key Characteristics of a GUI

| Feature | Explanation |
|---------|-------------|
| **Window-based** | Interaction happens inside resizable, movable windows |
| **Event-driven** | The program responds to user actions, not sequential commands |
| **WIMP model** | **W**indows, **I**cons, **M**enus, **P**ointers |
| **Visual feedback** | State changes are immediately visible |

### GUI Components (Widgets)

GUI components are also called **controls** or **widgets** (short for *window gadgets*). They are objects with which the user interacts.

```
GUI Component Categories
│
├── INPUT COMPONENTS (user → program)
│   ├── TextField       — Single-line text entry
│   ├── TextArea        — Multi-line text entry
│   ├── Button          — Trigger an action
│   ├── CheckBox        — Toggle true/false
│   ├── RadioButton     — Select one from a group
│   ├── ComboBox        — Drop-down selection
│   ├── Slider          — Numeric range selector
│   └── ListView        — Scrollable item list
│
├── OUTPUT COMPONENTS (program → user)
│   ├── Label           — Display static/dynamic text
│   ├── ImageView       — Display images
│   └── ProgressBar     — Show task progress
│
└── HYBRID (both input and output)
    ├── TextField       — Show AND receive text
    └── ComboBox        — Show selection AND receive choice
```

### Why GUI Over CLI?

Imagine the internet as pure text — no images, no buttons, no dropdowns, just commands you type. GUIs make software:
- **Discoverable** — users can see what's available without memorizing commands
- **Intuitive** — visual metaphors (like a trash can for delete) reduce learning curve
- **Efficient** — clicking is faster than typing commands for most users

---

# 4. History: AWT → Swing → JavaFX

## Java's GUI Evolution

Understanding this history explains **why** JavaFX exists and why it's preferred.

### Timeline

```
1995 ─── AWT (Abstract Window Toolkit)
         • Java's original GUI library
         • Used OS-native widgets (looked different on each OS)
         • Limited components, poor cross-platform consistency
              │
              ▼
1998 ─── Swing (Java SE 1.2)
         • Drew its own widgets (same look on all OS)
         • More components, better customization
         • Still widely used in legacy applications
         • Separate APIs needed for graphics and multimedia
              │
              ▼
2007 ─── JavaFX (announced by Sun Microsystems)
         • Designed to compete with Adobe Flash & MS Silverlight
         • One unified API for GUI + graphics + audio + video
         • CSS styling, FXML layout language, Scene Builder tool
         • Hardware-accelerated via GPU
              │
              ▼
2010 ─── Oracle acquires Sun Microsystems
              │
              ▼
Present ─ JavaFX is the modern standard
           • Open source (OpenJFX)
           • Active development and community
```

### Key Takeaway

**JavaFX is NOT a replacement for Swing but a leap forward.** Swing apps can embed JavaFX panels via `JFXPanel`, and JavaFX apps can embed Swing components via `SwingNode`.

---

# 5. What is JavaFX?

## Definition & Purpose

**JavaFX** is an open-source Java framework for building **rich client applications** — programs with sophisticated graphical interfaces that run on desktops, and can be embedded in browsers or mobile environments.

### What JavaFX Provides in One Package

```
JavaFX = GUI + Graphics + Multimedia + CSS + FXML + Animation
         ↑         ↑           ↑         ↑      ↑        ↑
     Buttons    2D/3D       Audio/     Style  Layout  Motion
     Labels     shapes      Video     sheets  markup  effects
```

### Comparable Technologies

| Technology | Platform |
|-----------|---------|
| JavaFX | Java / JVM |
| Adobe AIR | Flash / ActionScript |
| Microsoft Blazor | .NET / C# |
| Electron | JavaScript / Node.js |

---

# 6. JavaFX vs Swing — A Comparison

| Aspect | Swing | JavaFX |
|--------|-------|--------|
| **Scope** | GUI only | GUI + Graphics + Multimedia |
| **Styling** | Complex, Java-only customization | CSS stylesheets (like web) |
| **Layout tools** | IDE-specific (different code per IDE) | Scene Builder (same code everywhere) |
| **Threading** | Basic | Advanced multi-core support |
| **Rendering** | Software-based | GPU hardware-accelerated |
| **Layout description** | Pure Java code | FXML (declarative XML) |
| **Animation** | Limited | Built-in animation API |
| **Interoperability** | Can embed JavaFX via `JFXPanel` | Can embed Swing via `SwingNode` |

### Analogy 🏗️

Swing is like building a house with hand tools — it works, but it's slow, inconsistent, and each carpenter (IDE) produces slightly different results. JavaFX is like using a modern construction kit with standardized parts, power tools, and a unified blueprint (FXML) that any builder reads the same way.

---

# 7. JavaFX Window Architecture: Stage, Scene & Nodes

## The Three-Layer Model

Every JavaFX application is organized into exactly three conceptual layers, nested inside each other like Russian dolls.

### Layer Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                         STAGE                               │
│  (The operating system window — title bar, borders, etc.)   │
│                                                             │
│  ┌───────────────────────────────────────────────────────┐  │
│  │                       SCENE                           │  │
│  │   (The canvas inside the window — background area)    │  │
│  │                                                       │  │
│  │   ┌──────────┐  ┌──────────┐  ┌──────────────────┐   │  │
│  │   │  Button  │  │  Label   │  │    ImageView     │   │  │
│  │   └──────────┘  └──────────┘  └──────────────────┘   │  │
│  │                   NODES (visual elements)              │  │
│  └───────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

### Stage — The Operating System Window

The **Stage** (`javafx.stage.Stage`) represents the actual OS window — the thing with the title bar, minimize/maximize/close buttons, and a resizable border. Think of it as the **picture frame**.

- Every JavaFX app gets one primary Stage, passed into your `start()` method.
- You can create additional stages (secondary windows) manually.
- You set its title, size, and whether it's resizable.

### Scene — The Canvas

The **Scene** (`javafx.scene.Scene`) is the drawing area inside the stage. It holds all visual content and defines the window's dimensions. Think of it as the **canvas inside the frame**.

- A Stage can only display one Scene at a time, but you can swap them.
- The Scene holds the root node of the scene graph.
- The Scene's preferred size at startup is determined by the root node.

### Node — Every Visual Element

A **Node** (`javafx.scene.Node`) is any visual element — a button, an image, a text field, a shape, or even a layout container. Think of each node as a **brushstroke** on the canvas.

Nodes form a **tree structure** called the Scene Graph (explained in Section 8).

### The `Application` Class — Entry Point

Every JavaFX program extends `Application` and overrides the `start(Stage)` method:

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * HelloWorld JavaFX Application
 * Demonstrates the minimal Stage → Scene → Node structure.
 */
public class HelloWorldApp extends Application {

    /**
     * The start() method is JavaFX's entry point.
     * The framework constructs the primary Stage and passes it here.
     */
    @Override
    public void start(Stage primaryStage) {

        // Step 1 — Create a Node (a Label with text)
        Label greeting = new Label("Hello, JavaFX World!");
        greeting.relocate(50, 70);        // Position at (x=50, y=70)
        greeting.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Step 2 — Create a container (Pane) and add the node
        Pane rootPane = new Pane();
        rootPane.getChildren().add(greeting);

        // Step 3 — Create a Scene with the container and dimensions
        Scene scene = new Scene(rootPane, 300, 150);

        // Step 4 — Configure the Stage
        primaryStage.setTitle("Hello World App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Step 5 — Make the Stage visible
        primaryStage.show();
    }

    /**
     * main() is the JVM entry point.
     * launch() is an Application method that bootstraps JavaFX
     * and then calls start() on the JavaFX Application Thread.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Pseudocode — JavaFX App Structure

```
PROGRAM JavaFXApplication:
    EXTEND Application

    METHOD start(primaryStage):
        1. CREATE visual elements (nodes)
        2. CREATE a root container (Pane)
        3. ADD nodes to the container
        4. CREATE a Scene with (container, width, height)
        5. SET stage title
        6. SET stage scene
        7. SHOW the stage

    METHOD main(args):
        CALL launch(args)   // Hands control to JavaFX runtime
END PROGRAM
```

### Flowchart — JavaFX Startup Sequence

```
[JVM starts main()]
        │
        ▼
[launch(args) called]
        │
        ▼
[JavaFX Runtime Initializes]
        │
        ▼
[Primary Stage created by framework]
        │
        ▼
[start(primaryStage) called on App Thread]
        │
        ▼
[Your code: creates nodes, scene, configures stage]
        │
        ▼
[primaryStage.show()]
        │
        ▼
[Window appears — Event Loop begins]
        │
        ▼
[User Interaction → Events → Handlers]
        │
        ▼
[User closes window → Application exits]
```

---

# 8. Scene Graph — The Tree of Visual Elements

## What is the Scene Graph?

The **scene graph** is a **hierarchical tree structure** that organizes all the visual elements in your JavaFX application. Every element (node) in the scene has exactly one parent (except the root), and can have zero or more children.

### Why a Tree?

A tree structure lets JavaFX efficiently:
- **Render** only what changed (dirty nodes)
- **Transform** groups of elements together (move a container, all children move)
- **Apply styles** that cascade from parent to child (like CSS in HTML)
- **Propagate events** up and down the tree (event bubbling)

### Scene Graph Anatomy

```
                    Stage
                      │
                    Scene
                      │
               ┌── Root Node ──┐
               │  (e.g., VBox) │
               └───────────────┘
                  /     │     \
           ┌────┐  ┌────┐  ┌─────────┐
           │HBox│  │Btn │  │  Pane   │
           └────┘  └────┘  └─────────┘
            /  \                │
        ┌──┐  ┌──┐         ┌────────┐
        │Lbl│  │Txt│         │ListView│
        └──┘  └──┘         └────────┘
```

### Parent–Child Relationships

Every node in the scene graph knows its **parent** and every parent knows its **children**:

```java
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.ObservableList;

public class SceneGraphExplorer {

    public static void demonstrateRelationships(Pane rootPane) {

        // Create a child label inside the root pane
        Label childLabel = new Label("I am a child node");
        rootPane.getChildren().add(childLabel);

        // Navigate UPWARD — child finds its parent
        Parent myParent = childLabel.getParent();
        System.out.println("Parent type: " + myParent.getClass().getSimpleName());

        // Navigate FURTHER UP — grandparent
        Parent grandParent = myParent.getParent();
        // grandParent could be the Scene's root, or null if rootPane IS the root

        // Navigate DOWNWARD — parent finds all its children
        ObservableList<Node> allChildren = rootPane.getChildren();
        System.out.println("Number of children: " + allChildren.size());

        // Access specific children by index
        Node firstChild = allChildren.get(0);
        System.out.println("First child: " + firstChild.getClass().getSimpleName());
    }
}
```

### Node Class Hierarchy (Partial)

```
Object
└── Node  (javafx.scene.Node)
    ├── Parent  (can have children)
    │   ├── Region  (has CSS layout support)
    │   │   ├── Control  (interactive UI controls)
    │   │   │   ├── Label
    │   │   │   ├── Button
    │   │   │   ├── TextField
    │   │   │   ├── ListView
    │   │   │   ├── ComboBox
    │   │   │   └── ... (many more)
    │   │   └── Pane  (layout containers)
    │   │       ├── AnchorPane
    │   │       ├── BorderPane
    │   │       ├── HBox
    │   │       ├── VBox
    │   │       ├── GridPane
    │   │       └── FlowPane
    │   └── Group  (transforms a group of nodes)
    └── Shape  (geometric shapes — Circle, Rectangle, Line...)
```

---

# 9. FXML & Scene Builder

## Declarative vs. Imperative UI

There are two ways to build a JavaFX UI:

| Approach | How | Analogy |
|---------|-----|---------|
| **Imperative** (pure Java) | Write code that creates every component programmatically | Sculpting clay by hand |
| **Declarative** (FXML) | Describe the layout in an XML file | Following an architectural blueprint |

## FXML — The Markup Language

**FXML** (FX Markup Language) is an XML vocabulary for describing JavaFX UI structure. Instead of writing `new Button("OK")` in Java, you describe it in XML:

```xml
<!-- Example FXML file: Calculator.fxml -->
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.control.Button?>

<VBox alignment="CENTER" spacing="10"
      prefWidth="250" prefHeight="300"
      xmlns:fx="http://javafx.com/fxml"
      fx:controller="CalculatorController">

    <TextField fx:id="inputField" promptText="Enter a number..." />
    <Button text="Compute" onAction="#handleCompute" />
    <TextField fx:id="resultField" editable="false" />

</VBox>
```

The `fx:id` attribute names a component so the Controller can reference it. The `onAction="#handleCompute"` links the button to a method in the Controller.

## Scene Builder

**JavaFX Scene Builder** is a visual drag-and-drop tool that generates FXML automatically. You design the UI visually and the tool writes the FXML for you — then you add behavior in a Controller class.

### The Three-File JavaFX Project Pattern

```
MyProject/
├── Main.java               ← Loads the FXML and shows the Stage
├── MyView.fxml             ← Describes the UI structure (Scene Builder)
└── MyViewController.java   ← Contains event handlers (business logic bridge)
```

### Responsibilities

```
Main.java
  └── Loads MyView.fxml
  └── Creates Scene and Stage
  └── Shows the window
  
MyView.fxml
  └── Declares all UI components (buttons, labels, etc.)
  └── Links fx:id names to controller variables
  └── Links onAction to controller methods
  
MyViewController.java
  └── Has @FXML-annotated variables matching fx:id names
  └── Has @FXML-annotated methods matching onAction names
  └── Calls Model classes to do the real work
```

### Benefits of FXML Separation

- Designers can work on the FXML while developers write Java logic
- Switching the visual design doesn't require rewriting event handler logic
- Easier to read and maintain than walls of `new Button()` code

---

# 10. JavaFX Project Structure in Eclipse

## Setting Up a JavaFX Project

### Steps Overview

```
1. File → New → Project → JavaFX → JavaFX Project
2. Name your project
3. Select JavaSE JRE version (e.g., JavaSE-17)
4. Choose "Create separate folders for sources and class files"
5. Configure Declarative UI settings:
   - Language: FXML
   - Root-Type: javafx.scene.layout.BorderPane (or VBox, etc.)
   - File Name: YourView
   - Controller Name: YourViewController
6. Finish

7. Add the JavaFX SDK library:
   - Right-click project → Build Path → Configure Build Path
   - Libraries → Add Library → User Library → select "Fx"
```

### The Generated Files

| File | Role |
|------|------|
| `Main.java` | Entry point — loads FXML, creates Stage |
| `YourView.fxml` | Describes the visual layout |
| `YourViewController.java` | Event handlers and controller logic |
| `application.css` | Optional stylesheet for component styling |
| `module-info.java` | Module system configuration (Java 9+) |

---

# 11. Window Components In Depth

## Buttons — The Most Common Interactive Component

A **Button** is a clickable region with a label (text and/or image) that triggers an action when clicked.

### Creating and Styling Buttons

```java
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.*;

public class ButtonExamples {

    public static Button createStyledButton() {
        // --- Basic button ---
        Button btn = new Button("Save File");

        // --- Position and size ---
        btn.relocate(20, 30);       // Top-left corner at (x=20, y=30)
        btn.setPrefSize(140, 35);   // Width=140, Height=35

        // --- Text alignment within button ---
        btn.setAlignment(Pos.CENTER);       // Text centered
        // btn.setAlignment(Pos.CENTER_LEFT); // Text left-aligned
        // btn.setAlignment(Pos.CENTER_RIGHT);// Text right-aligned

        // --- CSS styling ---
        // -fx-font: "size fontfamily"
        // -fx-base: background color
        // -fx-text-fill: text color
        btn.setStyle(
            "-fx-font: 14 Arial;" +
            "-fx-base: rgb(0, 120, 215);" +      // Blue background
            "-fx-text-fill: rgb(255, 255, 255);"  // White text
        );

        // --- Disable so user cannot click it ---
        btn.setDisable(false); // false = enabled (default)
        // btn.setDisable(true); // true = grayed out, unclickable

        // --- Hide completely ---
        // btn.setVisible(false); // invisible AND uninteractable

        return btn;
    }

    public static Button createImageButton() {
        // Load an image from the src folder
        Image icon = new Image(
            ButtonExamples.class.getResourceAsStream("save_icon.png")
        );

        // Button with text AND image
        Button btnWithImage = new Button("Save", new ImageView(icon));

        // Button with ONLY image (no text)
        Button iconOnly = new Button();
        iconOnly.setGraphic(new ImageView(icon));

        return iconOnly;
    }
}
```

### Button State Analogy 🚦

Think of a Button like a traffic light button at a pedestrian crossing:
- **Enabled (`setDisable(false)`)**: The button lights up when pressed.
- **Disabled (`setDisable(true)`)**: The button exists but is grayed — pressing it does nothing.
- **Invisible (`setVisible(false)`)**: The button is physically not there at all.

---

## Labels — Displaying Text

A **Label** is a non-interactive text display element. It shows information to the user but doesn't receive input.

```java
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LabelExamples {

    public static Label createStatusLabel() {
        Label status = new Label("Ready");

        status.relocate(10, 200);
        status.setPrefSize(200, 25);

        // Style with CSS
        status.setStyle(
            "-fx-font: bold 13 'Segoe UI';" +
            "-fx-text-fill: rgb(0, 150, 0);" // Green text
        );

        // Change text dynamically (from an event handler)
        // status.setText("Processing...");
        // status.setText("Done!");

        return status;
    }
}
```

---

## TextField — Single-Line Text Input

A **TextField** is an interactive single-line text box where the user can type. You can also read and write its contents programmatically.

```java
import javafx.scene.control.TextField;

public class TextFieldExamples {

    public static void demonstrateTextField() {

        TextField nameField = new TextField();
        nameField.relocate(100, 10);
        nameField.setPrefSize(200, 30);

        // Set placeholder text (shown when field is empty)
        nameField.setPromptText("Enter your full name...");

        // Pre-fill with a default value
        nameField.setText("Jane Smith");

        // Make read-only (user can see but not edit)
        nameField.setEditable(false);

        // Read the current value (used in event handlers)
        String currentValue = nameField.getText();
        System.out.println("Current value: " + currentValue);

        // --- Parsing text into numbers ---
        TextField ageField = new TextField();
        // Assume user typed "25"
        ageField.setText("25");

        int age    = Integer.parseInt(ageField.getText());
        double gpa = Double.parseDouble("3.85");
        float  temp = Float.parseFloat("98.6");

        System.out.println("Age: " + age + ", GPA: " + gpa);
    }
}
```

---

## ListView — Scrollable List of Items

A **ListView** displays a scrollable list of items. The user can select one or more items.

```java
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListViewExamples {

    public static ListView<String> createColorList() {

        // ObservableList automatically updates the UI when changed
        ObservableList<String> colors = FXCollections.observableArrayList(
            "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"
        );

        ListView<String> listView = new ListView<>(colors);
        listView.relocate(10, 10);
        listView.setPrefSize(150, 200);

        // To add an item later (from an event handler):
        // colors.add("Magenta");

        // To remove an item:
        // colors.remove("Red");

        // To get the currently selected item:
        // String selected = listView.getSelectionModel().getSelectedItem();

        // To get the selected index:
        // int index = listView.getSelectionModel().getSelectedIndex();

        return listView;
    }
}
```

---

## ComboBox — Drop-Down Selection

A **ComboBox** shows one selected item; clicking it reveals a dropdown list of all options.

```java
import javafx.collections.*;
import javafx.scene.control.ComboBox;

public class ComboBoxExamples {

    public static ComboBox<String> createMonthSelector() {

        ObservableList<String> months = FXCollections.observableArrayList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        );

        ComboBox<String> monthPicker = new ComboBox<>(months);
        monthPicker.relocate(10, 10);
        monthPicker.setPrefSize(180, 30);

        // Placeholder text when nothing is selected
        monthPicker.setPromptText("Select a month...");

        // Or pre-select a value
        monthPicker.setValue("January");

        // Get the selected value (in an event handler):
        // String chosen = monthPicker.getValue();

        return monthPicker;
    }
}
```

---

## Component Position & Size — The Coordinate System

Every component in a Pane has an (x, y) **position** (top-left corner) and a **size** (width, height).

```
(0,0) ──────────────────────────────────► x-axis
  │
  │    (10, 20) ← top-left of component
  │    ┌─────────────────────┐
  │    │                     │ ▲
  │    │    Button "Click"   │ │ height=30
  │    │                     │ ▼
  │    └─────────────────────┘
  │    ◄──── width=150 ──────►
  │
  ▼
y-axis
```

**Note:** In JavaFX (and most graphics systems), **y increases downward**, unlike mathematical graphs where y increases upward.

```java
// Position: top-left corner at x=10, y=20
button.relocate(10, 20);

// Size: 150 pixels wide, 30 pixels tall
button.setPrefSize(150, 30);
```

---

# 12. Layout Containers — Organizing Your UI

## What is a Layout Container?

A **layout container** (or **Pane**) is a special node that holds other nodes and manages their arrangement automatically. Instead of manually positioning every component with `relocate()`, layout containers let you define rules for how components arrange themselves.

### The Container Family

```
Pane (base — no automatic layout, manual positioning)
│
├── AnchorPane  — Anchor children to edges (resize-friendly)
├── BorderPane  — 5 zones: TOP, BOTTOM, LEFT, RIGHT, CENTER
├── VBox        — Stack children VERTICALLY
├── HBox        — Stack children HORIZONTALLY
├── GridPane    — Arrange in rows and columns (like a table)
├── FlowPane    — Wrap children like text (left-to-right, then next row)
├── StackPane   — Layer children on top of each other
└── TilePane    — Uniform-size cells in a grid
```

### VBox — Vertical Stack

```java
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class VBoxExample {

    public static VBox createLoginForm() {

        // VBox stacks children top-to-bottom
        // spacing=10 means 10px gap between each child
        VBox form = new VBox(10);
        form.setAlignment(Pos.CENTER);         // Center everything
        form.setStyle("-fx-padding: 20;");     // 20px padding around edges

        Label title = new Label("Log In");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPrefWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(200);

        Button loginBtn = new Button("Sign In");
        loginBtn.setPrefWidth(200);
        loginBtn.setStyle("-fx-base: rgb(0, 100, 200); -fx-text-fill: white;");

        // Add all children to VBox — they stack vertically automatically
        form.getChildren().addAll(title, usernameField, passwordField, loginBtn);

        return form;
    }
}
```

### HBox — Horizontal Row

```java
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class HBoxExample {

    public static HBox createToolbar() {

        // HBox arranges children left-to-right
        HBox toolbar = new HBox(5); // 5px spacing between children
        toolbar.setAlignment(Pos.CENTER_LEFT);
        toolbar.setStyle("-fx-padding: 8; -fx-background-color: #f0f0f0;");

        Button newBtn    = new Button("New");
        Button openBtn   = new Button("Open");
        Button saveBtn   = new Button("Save");
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-base: rgb(200, 0, 0); -fx-text-fill: white;");

        toolbar.getChildren().addAll(newBtn, openBtn, saveBtn, deleteBtn);

        return toolbar;
    }
}
```

### BorderPane — Five Regions

```java
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class BorderPaneExample {

    public static BorderPane createAppLayout() {

        BorderPane root = new BorderPane();

        // TOP — navigation bar
        Label header = new Label("My Application");
        header.setStyle("-fx-font-size: 18px; -fx-padding: 10;");
        root.setTop(header);

        // LEFT — sidebar menu
        VBox sidebar = new VBox(5);
        sidebar.setStyle("-fx-padding: 10; -fx-background-color: #e8e8e8;");
        sidebar.getChildren().addAll(
            new Button("Dashboard"),
            new Button("Reports"),
            new Button("Settings")
        );
        root.setLeft(sidebar);

        // CENTER — main content area
        Label content = new Label("Main content goes here");
        root.setCenter(content);

        // BOTTOM — status bar
        Label status = new Label("Ready | Connected");
        status.setStyle("-fx-padding: 5; -fx-background-color: #d0d0d0;");
        root.setBottom(status);

        // RIGHT — optional panel (can be null)
        root.setRight(null);

        return root;
    }
}
```

### Pane — Manual Absolute Positioning

When you need pixel-perfect control over where every component appears, use the base `Pane` class:

```java
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class ManualLayoutExample {

    public static Pane createGameBoard() {

        Pane canvas = new Pane();
        canvas.setPrefSize(400, 300);
        canvas.setStyle("-fx-background-color: #2c3e50;");

        // Place elements at exact pixel coordinates
        Label scoreLabel = new Label("Score: 0");
        scoreLabel.relocate(10, 10);
        scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        Button pauseBtn = new Button("Pause");
        pauseBtn.relocate(320, 10);
        pauseBtn.setPrefSize(70, 25);

        Label levelLabel = new Label("Level 1");
        levelLabel.relocate(170, 10);
        levelLabel.setStyle("-fx-text-fill: gold; -fx-font-size: 16px;");

        canvas.getChildren().addAll(scoreLabel, pauseBtn, levelLabel);

        return canvas;
    }
}
```

---

# 13. Grouping Components with Custom Panes

## The Problem: Repeating UI Patterns

Many real applications have the same group of components appearing in multiple windows. Without abstraction, you would copy-paste the same layout code everywhere — a maintenance nightmare.

### The Solution: Custom Pane Classes

Create a **custom class that extends `Pane`**, placing its components in its constructor. Now the entire group acts as a single, reusable component.

### Analogy 🧩

Think of LEGO. Instead of gluing individual bricks together every time, you can pre-assemble a "door module" or "window module" and snap that entire pre-built piece wherever you need it.

### Pseudocode — Custom Pane Pattern

```
CLASS MyCustomPane EXTENDS Pane:
    CONSTRUCTOR (parameter):
        CREATE component1
        POSITION component1
        SET SIZE of component1

        CREATE component2
        POSITION component2
        SET SIZE of component2

        ADD all components to THIS pane
        (optional) STYLE this pane with a border
END CLASS
```

### Original Example — `ContactCard` Custom Pane

```java
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 * ContactCard — A reusable Pane that displays a labeled
 * group of input fields for entering contact information.
 *
 * USAGE:
 *   ContactCard card = new ContactCard("BILLING CONTACT");
 *   card.relocate(10, 50);
 *   mainPane.getChildren().add(card);
 */
public class ContactCard extends Pane {

    // Expose text fields so the outer app can read values
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField phoneField;

    /**
     * @param sectionTitle  Text to appear in the border's title
     */
    public ContactCard(String sectionTitle) {

        // --- Inner pane holds all the labeled fields ---
        Pane innerPane = new Pane();
        innerPane.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #999999;" +
            "-fx-border-radius: 4;" +
            "-fx-padding: 5 5 5 5;"
        );

        int labelX = 10;     // x-position for all labels
        int fieldX = 110;    // x-position for all text fields
        int fieldW = 220;    // width of all text fields
        int fieldH = 28;     // height of all text fields
        int rowGap = 40;     // vertical distance between rows

        // --- Row 1: First Name ---
        Label fnLabel = new Label("First Name:");
        fnLabel.relocate(labelX, 15);
        fnLabel.setPrefSize(90, fieldH);

        firstNameField = new TextField();
        firstNameField.relocate(fieldX, 15);
        firstNameField.setPrefSize(fieldW, fieldH);

        // --- Row 2: Last Name ---
        Label lnLabel = new Label("Last Name:");
        lnLabel.relocate(labelX, 15 + rowGap);
        lnLabel.setPrefSize(90, fieldH);

        lastNameField = new TextField();
        lastNameField.relocate(fieldX, 15 + rowGap);
        lastNameField.setPrefSize(fieldW, fieldH);

        // --- Row 3: Email ---
        Label emailLabel = new Label("Email:");
        emailLabel.relocate(labelX, 15 + rowGap * 2);
        emailLabel.setPrefSize(90, fieldH);

        emailField = new TextField();
        emailField.setPromptText("name@example.com");
        emailField.relocate(fieldX, 15 + rowGap * 2);
        emailField.setPrefSize(fieldW, fieldH);

        // --- Row 4: Phone ---
        Label phoneLabel = new Label("Phone:");
        phoneLabel.relocate(labelX, 15 + rowGap * 3);
        phoneLabel.setPrefSize(90, fieldH);

        phoneField = new TextField();
        phoneField.setPromptText("(555) 000-0000");
        phoneField.relocate(fieldX, 15 + rowGap * 3);
        phoneField.setPrefSize(fieldW, fieldH);

        // Add all components to the inner pane
        innerPane.getChildren().addAll(
            fnLabel, firstNameField,
            lnLabel, lastNameField,
            emailLabel, emailField,
            phoneLabel, phoneField
        );

        // --- Title label floats over the border ---
        Label titleLabel = new Label(sectionTitle);
        titleLabel.setStyle(
            "-fx-background-color: white;" +
            "-fx-translate-y: -8;" +    // Lift it up to overlap border
            "-fx-translate-x: 8;"       // Indent from left
        );

        // Add inner pane first, then title on top
        this.getChildren().addAll(innerPane, titleLabel);
    }

    // --- Getters so the parent app can read field values ---

    public String getFirstName() { return firstNameField.getText(); }
    public String getLastName()  { return lastNameField.getText(); }
    public String getEmail()     { return emailField.getText(); }
    public String getPhone()     { return phoneField.getText(); }

    // Convenience method — fill fields with existing contact data
    public void populate(String fn, String ln, String email, String phone) {
        firstNameField.setText(fn);
        lastNameField.setText(ln);
        emailField.setText(email);
        phoneField.setText(phone);
    }
}
```

### Using the Custom Pane in Two Different Windows

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Demonstrates reusing ContactCard in a single window.
 * The SAME ContactCard class is placed twice — no code duplication!
 */
public class OrderFormApp extends Application {

    @Override
    public void start(Stage stage) {

        Pane mainPane = new Pane();

        Label formTitle = new Label("New Order Form");
        formTitle.relocate(10, 10);
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // ── First instance of ContactCard ──
        ContactCard billingCard = new ContactCard("BILLING CONTACT");
        billingCard.relocate(10, 45);

        // ── Second instance of the SAME class — no duplication! ──
        ContactCard shippingCard = new ContactCard("SHIPPING CONTACT");
        shippingCard.relocate(360, 45);  // Placed to the right

        Button submitBtn = new Button("Submit Order");
        submitBtn.relocate(10, 230);
        submitBtn.setPrefSize(700, 35);

        mainPane.getChildren().addAll(formTitle, billingCard, shippingCard, submitBtn);

        stage.setTitle("Order Form");
        stage.setScene(new Scene(mainPane, 720, 280));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

### Flowchart — Custom Pane Reuse

```
DEFINE ContactCard class once
         │
         ├─────────────────────────────────────────┐
         │                                         │
         ▼                                         ▼
  ContactCard("BILLING CONTACT")      ContactCard("SHIPPING CONTACT")
  placed at (10, 45)                  placed at (360, 45)
         │                                         │
         └──────────────┬──────────────────────────┘
                        │
                   mainPane.getChildren()
                        │
                   Scene → Stage → show()
```

---

# 14. Event Handling — Making the UI Interactive

## What is an Event?

An **event** is a signal that something has occurred in the program — typically caused by user interaction. The program responds to events rather than running line-by-line from top to bottom. This is called **event-driven programming**.

### Three Core Concepts

| Term | Definition | Example |
|------|-----------|---------|
| **Event** | A specific occurrence of interest | User clicks a button |
| **Event Source** | The component that generated the event | The `Button` object |
| **Event Handler** | The method that runs in response | Your `handle()` code |

### Analogy 🔔

Think of event handling like a hotel reception bell:
- The **bell** = the UI component (Button, TextField, etc.)
- **Ringing the bell** = the user action (clicking, typing)
- The **notification** = the Event object created by JavaFX
- The **receptionist who appears** = your event handler
- The **receptionist's action** (checking you in) = your `handle()` method code

You don't hire a receptionist to stand there 24/7 staring at the desk. Instead, you **register** them so that when the bell rings, they automatically show up. This registration is exactly what `setOnAction()` does.

### The Event Class Hierarchy

```
Object
└── EventObject  (java.util)
    └── Event  (javafx.event)
        ├── ActionEvent        ← Button pressed, Enter key in TextField
        ├── InputEvent
        │   ├── KeyEvent       ← Keyboard pressed/released/typed
        │   └── MouseEvent     ← Mouse clicked/moved/dragged
        │       └── MouseDragEvent
        ├── WindowEvent        ← Window opened/closed/minimized
        └── ... (many more)
```

### The EventHandler Interface

To handle any event, you implement the `EventHandler<T>` interface, which has exactly one method:

```java
public interface EventHandler<T extends Event> {
    void handle(T event);  // Your code goes here
}
```

### How Registration Works

```
                    ┌──────────────────────────┐
                    │   JavaFX Event System    │
                    └──────────────────────────┘
                              ▲
                              │ 2. User clicks button
                              │    → JavaFX creates ActionEvent
                              │    → Looks up registered handler
                              │    → Calls handler.handle(event)
                              │
   ┌──────────┐               │                 ┌───────────────────┐
   │  Button  │───────────────┘                 │  Your EventHandler │
   │ "Submit" │                                 │  handle() method   │
   └──────────┘                                 └───────────────────┘
        │                                                ▲
        │  1. Registration (in your start() method)      │
        └────── btn.setOnAction(yourEventHandler) ───────┘
```

### General Event Handler Template

```java
// Syntax: component.setOnEvent(new EventHandler<EventType>() {
//     public void handle(EventType eventObject) {
//         // Your response code
//     }
// });

myButton.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent event) {
        // This runs every time myButton is clicked
        System.out.println("Button was clicked!");
    }
});
```

---

# 15. ActionEvent — Button & Field Interactions

## When is ActionEvent Fired?

An `ActionEvent` fires when:
- A **Button** is clicked
- The **Enter key** is pressed inside a `TextField`
- A **menu item** is selected
- A **timer event** fires

## Original Example — Simple Counter Application

```java
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

/**
 * CounterApp — Demonstrates ActionEvent on buttons.
 * Two buttons increment and decrement a counter; a Label displays the value.
 */
public class CounterApp extends Application {

    private int count = 0;        // Model data
    private Label countDisplay;   // UI output component

    @Override
    public void start(Stage stage) {

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30;");

        Label title = new Label("Click Counter");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // This label shows the current count
        countDisplay = new Label("0");
        countDisplay.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Button incrementBtn = new Button("▲  Increment");
        incrementBtn.setPrefSize(160, 40);
        incrementBtn.setStyle("-fx-base: rgb(39, 174, 96); -fx-text-fill: white; -fx-font-size: 14px;");

        Button decrementBtn = new Button("▼  Decrement");
        decrementBtn.setPrefSize(160, 40);
        decrementBtn.setStyle("-fx-base: rgb(192, 57, 43); -fx-text-fill: white; -fx-font-size: 14px;");

        Button resetBtn = new Button("↺  Reset");
        resetBtn.setPrefSize(160, 35);

        // --- Event Handlers ---

        incrementBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                count++;                                  // Update model
                countDisplay.setText("" + count);        // Update UI
                updateDisplayColor();
            }
        });

        decrementBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                count--;
                countDisplay.setText("" + count);
                updateDisplayColor();
            }
        });

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                count = 0;
                countDisplay.setText("0");
                countDisplay.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
            }
        });

        layout.getChildren().addAll(title, countDisplay, incrementBtn, decrementBtn, resetBtn);

        stage.setTitle("Counter App");
        stage.setScene(new Scene(layout, 250, 300));
        stage.show();
    }

    /** Change display color based on count value — visual feedback */
    private void updateDisplayColor() {
        if (count > 0) {
            countDisplay.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: rgb(39,174,96);");
        } else if (count < 0) {
            countDisplay.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: rgb(192,57,43);");
        } else {
            countDisplay.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        }
    }

    public static void main(String[] args) { launch(args); }
}
```

### Pseudocode — ActionEvent Pattern

```
ON APPLICATION START:
    CREATE UI components (buttons, labels, text fields)
    
    FOR EACH button that should respond to clicks:
        REGISTER an ActionEvent handler:
            WHEN button is clicked:
                READ any relevant input values
                COMPUTE new values (update model)
                UPDATE display labels/fields with new values
    
    SHOW the window
```

### Flowchart — Button Click Lifecycle

```
User moves mouse over button
         │
         ▼
User presses mouse button down
         │
         ▼
User releases mouse button (click complete)
         │
         ▼
JavaFX creates ActionEvent object
         │
         ▼
JavaFX calls registered handler's handle(ActionEvent)
         │
         ▼
Your handle() code executes
    │
    ├── Read TextField.getText()
    ├── Perform computation
    └── Update Label.setText() / TextField.setText()
         │
         ▼
UI visually updates to reflect new state
         │
         ▼
Program waits for next user action
```

---

# 16. MouseEvent — Responding to Mouse Input

## When is MouseEvent Fired?

`MouseEvent` is generated for any mouse interaction:

| Method | When it fires |
|--------|--------------|
| `setOnMouseClicked()` | Mouse button pressed AND released on the component |
| `setOnMousePressed()` | Mouse button pushed down |
| `setOnMouseReleased()` | Mouse button released |
| `setOnMouseEntered()` | Mouse cursor moves INTO the component |
| `setOnMouseExited()` | Mouse cursor moves OUT of the component |
| `setOnMouseMoved()` | Mouse moves over the component (no button) |
| `setOnMouseDragged()` | Mouse moves while a button is held |

## What Information Does a MouseEvent Carry?

```java
import javafx.scene.input.MouseEvent;

// Inside a handle() method:
public void handle(MouseEvent event) {

    // Coordinates relative to the component
    double localX = event.getX();
    double localY = event.getY();

    // Coordinates relative to the scene (the window's canvas)
    double sceneX = event.getSceneX();
    double sceneY = event.getSceneY();

    // Which mouse button triggered this?
    // event.getButton() returns: MouseButton.PRIMARY, SECONDARY, MIDDLE
    boolean wasLeftClick = event.getButton() == javafx.scene.input.MouseButton.PRIMARY;

    // Was it a double-click?
    boolean isDoubleClick = event.getClickCount() == 2;

    // Were modifier keys held?
    boolean shiftHeld = event.isShiftDown();
    boolean ctrlHeld  = event.isControlDown();
}
```

## Original Example — Interactive Draggable Panel

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * DraggablePanel — A panel the user can drag around the window
 * using mouse events. Demonstrates mousePressed + mouseDragged.
 */
public class DraggablePanel extends Application {

    // Store the offset between mouse position and panel's top-left
    // This prevents the panel from "jumping" to the cursor position
    private double offsetX, offsetY;

    @Override
    public void start(Stage stage) {

        Pane workspace = new Pane();
        workspace.setStyle("-fx-background-color: #ecf0f1;");

        // --- Create a draggable card ---
        VBox card = new VBox(5);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #bdc3c7;" +
            "-fx-border-radius: 6;" +
            "-fx-padding: 12;" +
            "-fx-effect: dropshadow(gaussian, #aaa, 6, 0, 2, 2);"
        );
        card.setPrefSize(180, 100);
        card.relocate(50, 50);

        Label cardTitle = new Label("📌 Drag Me!");
        cardTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        Label cardBody  = new Label("Hold left button\nand drag anywhere.");

        card.getChildren().addAll(cardTitle, cardBody);

        // --- Mouse PRESSED: record the click offset ---
        card.setOnMousePressed((MouseEvent e) -> {
            // How far from the panel's top-left corner did the user click?
            offsetX = e.getX();
            offsetY = e.getY();
            // Bring this card to the front visually
            card.toFront();
        });

        // --- Mouse DRAGGED: move the card with the mouse ---
        card.setOnMouseDragged((MouseEvent e) -> {
            // New position = cursor position − original click offset
            double newX = e.getSceneX() - offsetX;
            double newY = e.getSceneY() - offsetY;

            // Clamp to workspace boundaries (optional)
            newX = Math.max(0, Math.min(newX, 620 - card.getWidth()));
            newY = Math.max(0, Math.min(newY, 380 - card.getHeight()));

            card.relocate(newX, newY);
        });

        // --- Mouse ENTERED: visual hover feedback ---
        card.setOnMouseEntered((MouseEvent e) -> {
            card.setStyle(
                "-fx-background-color: #eaf4fb;" +
                "-fx-border-color: #3498db;" +
                "-fx-border-radius: 6;" +
                "-fx-padding: 12;" +
                "-fx-cursor: move;"
            );
        });

        // --- Mouse EXITED: restore normal style ---
        card.setOnMouseExited((MouseEvent e) -> {
            card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #bdc3c7;" +
                "-fx-border-radius: 6;" +
                "-fx-padding: 12;"
            );
        });

        workspace.getChildren().add(card);

        stage.setTitle("Draggable Panel Demo");
        stage.setScene(new Scene(workspace, 620, 400));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

---

# 17. KeyEvent — Responding to Keyboard Input

## When is KeyEvent Fired?

| Handler Method | When it fires |
|---------------|--------------|
| `setOnKeyPressed()` | A key is pushed down (fires repeatedly if held) |
| `setOnKeyReleased()` | A key is released |
| `setOnKeyTyped()` | A character is fully typed (pressed+released, useful for text) |

## KeyCode — Identifying Specific Keys

```java
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

// Inside a setOnKeyPressed handler:
public void handle(KeyEvent event) {

    KeyCode key = event.getCode();

    // Named keys
    if (key == KeyCode.ENTER)  System.out.println("Enter pressed");
    if (key == KeyCode.ESCAPE) System.out.println("Escape pressed");
    if (key == KeyCode.DELETE) System.out.println("Delete pressed");
    if (key == KeyCode.UP)     System.out.println("Up arrow");
    if (key == KeyCode.DOWN)   System.out.println("Down arrow");
    if (key == KeyCode.LEFT)   System.out.println("Left arrow");
    if (key == KeyCode.RIGHT)  System.out.println("Right arrow");
    if (key == KeyCode.SPACE)  System.out.println("Spacebar");

    // Letter/digit keys
    if (key == KeyCode.A)      System.out.println("A key");

    // Check modifier keys
    boolean shift = event.isShiftDown();
    boolean ctrl  = event.isControlDown();
    boolean alt   = event.isAltDown();

    // Text of the key (for setOnKeyTyped — gives the actual character)
    String character = event.getCharacter();
}
```

## Original Example — Real-Time Input Validator

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

/**
 * NumericOnlyField — A TextField that only accepts digits.
 * Demonstrates KeyEvent filtering: reject non-numeric input in real-time.
 */
public class NumericFieldDemo extends Application {

    @Override
    public void start(Stage stage) {

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20;");

        Label prompt = new Label("Enter numbers only:");
        prompt.setStyle("-fx-font-weight: bold;");

        TextField numericField = new TextField();
        numericField.setPrefWidth(200);
        numericField.setPromptText("Type digits here...");

        Label feedbackLabel = new Label(" ");
        feedbackLabel.setStyle("-fx-font-size: 12px;");

        // --- KEY TYPED: fires when a character is about to be entered ---
        // This is the right event for filtering typed characters
        numericField.setOnKeyTyped((KeyEvent event) -> {

            String typed = event.getCharacter();

            // Allow digits 0-9 only
            if (!typed.matches("[0-9]")) {
                // Consume the event — character won't appear in the field
                event.consume();
                feedbackLabel.setText("⚠️  Numbers only! '" + typed + "' rejected.");
                feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            } else {
                feedbackLabel.setText("✓ Valid digit entered.");
                feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
            }
        });

        // --- KEY RELEASED: after the user lets go of a key ---
        numericField.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                String val = numericField.getText();
                if (!val.isEmpty()) {
                    int number = Integer.parseInt(val);
                    feedbackLabel.setText("✓ Value submitted: " + number);
                    feedbackLabel.setStyle("-fx-text-fill: blue; -fx-font-size: 12px;");
                }
            }
        });

        layout.getChildren().addAll(prompt, numericField, feedbackLabel);

        stage.setTitle("Numeric Field Demo");
        stage.setScene(new Scene(layout, 280, 150));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

---

# 18. Shared Event Handlers — One Handler, Many Sources

## The Problem

When you have many components that behave similarly (like a keypad with 12 buttons), writing a separate event handler for each one produces massive, repetitive code.

## The Solution: One Handler, Inspect the Source

The `event.getSource()` method returns a reference to the component that **generated** the event. You can cast it to the appropriate type and inspect its properties.

### Analogy 📞

Imagine a call center with 50 phone lines. Instead of hiring 50 receptionists (one per phone), you hire one very smart receptionist who checks the phone's caller-ID display to know which line rang and responds accordingly.

### Pseudocode — Shared Handler Pattern

```
CREATE all buttons

DEFINE single_handler:
    METHOD handle(event):
        source = event.getSource()        // Which button was it?
        CAST source to Button
        text = source.getText()           // What does that button say?
        RESPOND based on text             // Take appropriate action

FOR EACH button:
    REGISTER single_handler on that button
```

## Original Example — Number Pad with Shared Handler

```java
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;

/**
 * NumberPadApp — A 4×3 keypad where all 12 buttons
 * share a single event handler.
 *
 * The handler inspects which button was pressed using
 * event.getSource() and builds a number string.
 */
public class NumberPadApp extends Application {

    // Accumulates the number being dialed
    private StringBuilder enteredDigits = new StringBuilder();
    private Label displayLabel;

    @Override
    public void start(Stage stage) {

        // --- Number pad layout ---
        String[][] padLayout = {
            {"7", "8", "9"},
            {"4", "5", "6"},
            {"1", "2", "3"},
            {"*", "0", "#"}
        };

        GridPane pad = new GridPane();
        pad.setAlignment(Pos.CENTER);
        pad.setHgap(6);
        pad.setVgap(6);
        pad.setStyle("-fx-padding: 10;");

        // The single, shared event handler for ALL buttons
        EventHandler<ActionEvent> sharedHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                // Identify the source — which button was pressed?
                Button pressed = (Button) event.getSource();
                String label = pressed.getText();

                if (label.equals("⌫")) {
                    // Backspace — remove last character
                    if (enteredDigits.length() > 0) {
                        enteredDigits.deleteCharAt(enteredDigits.length() - 1);
                    }
                } else {
                    enteredDigits.append(label);
                }

                // Update the display
                displayLabel.setText(enteredDigits.toString());
            }
        };

        // Create buttons from the layout grid and wire them all to ONE handler
        for (int row = 0; row < padLayout.length; row++) {
            for (int col = 0; col < padLayout[row].length; col++) {
                Button btn = new Button(padLayout[row][col]);
                btn.setPrefSize(70, 55);
                btn.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                btn.setOnAction(sharedHandler);   // Same handler for everyone

                pad.add(btn, col, row);
            }
        }

        // --- Display screen ---
        displayLabel = new Label("");
        displayLabel.setPrefSize(222, 40);
        displayLabel.setAlignment(Pos.CENTER_RIGHT);
        displayLabel.setStyle(
            "-fx-background-color: #ecf0f1;" +
            "-fx-border-color: #bdc3c7;" +
            "-fx-font-size: 20px;" +
            "-fx-padding: 5;"
        );

        // --- Backspace button (separate row) ---
        Button backspaceBtn = new Button("⌫");
        backspaceBtn.setPrefSize(222, 35);
        backspaceBtn.setOnAction(sharedHandler);   // Also uses shared handler!

        // --- Assemble layout ---
        VBox root = new VBox(8);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 12; -fx-background-color: #2c3e50;");
        root.getChildren().addAll(displayLabel, pad, backspaceBtn);

        stage.setTitle("Number Pad");
        stage.setScene(new Scene(root, 260, 320));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

### Flowchart — Shared Handler Dispatch

```
User clicks ANY button on the keypad
         │
         ▼
JavaFX creates ActionEvent
- event.getSource() = the specific Button that was clicked
         │
         ▼
sharedHandler.handle(event) is called
         │
         ▼
Button pressed = (Button) event.getSource()
         │
         ▼
String label = pressed.getText()
         │
         ├── label == "⌫"  →  remove last digit from enteredDigits
         │
         └── otherwise     →  append label to enteredDigits
         │
         ▼
displayLabel.setText(enteredDigits.toString())
         │
         ▼
UI updates — display shows new string
         │
         ▼
Waits for next click
```

---

# 19. Full Application Walkthrough — Calculator

## Complete Original Calculator Application

This example ties together everything from the course: Model–UI separation, layout, text fields, labels, buttons, event handling, type conversion, and user feedback.

### Application Design

```
┌─────────────────────────────────┐
│         Unit Converter          │
├─────────────────────────────────┤
│  Kilometers:  [         ]  km   │
│                                 │
│       [  Convert  ]             │
│                                 │
│  Miles:       [         ]  mi   │
│  Meters:      [         ]  m    │
│  Yards:       [         ]  yd   │
│  Feet:        [         ]  ft   │
│                                 │
│  [  Clear  ]                    │
└─────────────────────────────────┘
```

### Model Class

```java
/**
 * DistanceConverter — Pure business logic.
 * No JavaFX imports. No UI dependencies.
 * Can be unit-tested completely independently of the GUI.
 */
public class DistanceConverter {

    private double kilometres;

    public DistanceConverter() {
        this.kilometres = 0;
    }

    public void setKilometres(double km) {
        this.kilometres = km;
    }

    public double getKilometres()   { return kilometres; }

    public double toMiles()   { return kilometres * 0.621371; }
    public double toMetres()  { return kilometres * 1000; }
    public double toYards()   { return kilometres * 1093.61; }
    public double toFeet()    { return kilometres * 3280.84; }

    /** Format a double to 4 decimal places for display */
    public static String format(double value) {
        return String.format("%.4f", value);
    }
}
```

### UI Application Class

```java
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * UnitConverterApp — The UI layer.
 * Uses DistanceConverter (the model) to perform calculations.
 * Only responsible for: reading input, calling the model, showing output.
 */
public class UnitConverterApp extends Application {

    // --- UI Components declared as instance variables ---
    // (needed by event handlers AND the start() method)
    private TextField kmInput;
    private TextField milesOutput, metresOutput, yardsOutput, feetOutput;

    // --- The model (business logic) ---
    private DistanceConverter converter = new DistanceConverter();

    @Override
    public void start(Stage stage) {

        // ──────────────────────────────────────────────────────
        // BUILD THE UI
        // ──────────────────────────────────────────────────────

        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER_LEFT);
        mainLayout.setStyle("-fx-padding: 20; -fx-background-color: #fafafa;");

        Label appTitle = new Label("Distance Unit Converter");
        appTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Input row
        HBox inputRow = buildRow("Kilometres:", true, null);
        kmInput = (TextField) inputRow.getChildren().get(1);
        kmInput.setPromptText("e.g. 5.0");

        // Separator
        Label sep = new Label("──── Results ────");
        sep.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 11px;");

        // Output rows (editable=false — read-only result fields)
        HBox milesRow   = buildRow("Miles:",    false, "mi");
        HBox metresRow  = buildRow("Metres:",   false, "m");
        HBox yardsRow   = buildRow("Yards:",    false, "yd");
        HBox feetRow    = buildRow("Feet:",     false, "ft");

        // Grab references to output fields
        milesOutput  = (TextField) milesRow.getChildren().get(1);
        metresOutput = (TextField) metresRow.getChildren().get(1);
        yardsOutput  = (TextField) yardsRow.getChildren().get(1);
        feetOutput   = (TextField) feetRow.getChildren().get(1);

        // Buttons
        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);

        Button convertBtn = new Button("⇄  Convert");
        convertBtn.setPrefSize(120, 35);
        convertBtn.setStyle(
            "-fx-base: rgb(41, 128, 185);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;"
        );

        Button clearBtn = new Button("✕  Clear");
        clearBtn.setPrefSize(90, 35);
        clearBtn.setStyle("-fx-base: rgb(149, 165, 166);");

        buttonRow.getChildren().addAll(convertBtn, clearBtn);

        Label statusLabel = new Label("Enter a value in kilometres, then click Convert.");
        statusLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px;");

        mainLayout.getChildren().addAll(
            appTitle, inputRow, buttonRow, sep,
            milesRow, metresRow, yardsRow, feetRow,
            statusLabel
        );

        // ──────────────────────────────────────────────────────
        // WIRE UP EVENT HANDLERS
        // ──────────────────────────────────────────────────────

        // Convert button handler
        convertBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                String rawInput = kmInput.getText().trim();

                // Guard: don't process empty input
                if (rawInput.isEmpty()) {
                    statusLabel.setText("⚠️  Please enter a number of kilometres first.");
                    statusLabel.setStyle("-fx-text-fill: orange; -fx-font-size: 11px;");
                    return;
                }

                try {
                    // Parse the text input to a double
                    double km = Double.parseDouble(rawInput);

                    // Validate: no negative distances
                    if (km < 0) {
                        statusLabel.setText("⚠️  Distance cannot be negative.");
                        statusLabel.setStyle("-fx-text-fill: orange; -fx-font-size: 11px;");
                        return;
                    }

                    // ── Update the MODEL ──
                    converter.setKilometres(km);

                    // ── Query the MODEL, update the UI ──
                    milesOutput.setText(DistanceConverter.format(converter.toMiles()));
                    metresOutput.setText(DistanceConverter.format(converter.toMetres()));
                    yardsOutput.setText(DistanceConverter.format(converter.toYards()));
                    feetOutput.setText(DistanceConverter.format(converter.toFeet()));

                    statusLabel.setText("✓  Converted " + km + " km successfully.");
                    statusLabel.setStyle("-fx-text-fill: green; -fx-font-size: 11px;");

                } catch (NumberFormatException ex) {
                    // Input was not a valid number
                    statusLabel.setText("✗  Invalid input. Please enter a numeric value.");
                    statusLabel.setStyle("-fx-text-fill: red; -fx-font-size: 11px;");
                }
            }
        });

        // Clear button handler
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                kmInput.clear();
                milesOutput.clear();
                metresOutput.clear();
                yardsOutput.clear();
                feetOutput.clear();
                kmInput.requestFocus();
                statusLabel.setText("Cleared. Enter a new value.");
                statusLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px;");
            }
        });

        // Also convert when the user presses Enter in the input field
        kmInput.setOnAction(convertBtn.getOnAction());

        // ──────────────────────────────────────────────────────
        // CONFIGURE AND SHOW THE STAGE
        // ──────────────────────────────────────────────────────

        stage.setTitle("Distance Unit Converter");
        stage.setResizable(false);
        stage.setScene(new Scene(mainLayout, 360, 320));
        stage.show();
    }

    /**
     * Helper method — builds a labeled row: [Label] [TextField] [Unit label]
     * @param labelText   Text of the left label
     * @param editable    Whether the TextField accepts user input
     * @param unitSuffix  Short unit string shown to the right (e.g., "km"), or null
     */
    private HBox buildRow(String labelText, boolean editable, String unitSuffix) {
        HBox row = new HBox(8);
        row.setAlignment(Pos.CENTER_LEFT);

        Label lbl = new Label(labelText);
        lbl.setPrefWidth(100);
        lbl.setStyle("-fx-font-weight: bold;");

        TextField field = new TextField();
        field.setPrefWidth(160);
        field.setEditable(editable);
        if (!editable) {
            field.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #2c3e50;");
        }

        row.getChildren().addAll(lbl, field);

        if (unitSuffix != null) {
            Label unit = new Label(unitSuffix);
            unit.setStyle("-fx-text-fill: #7f8c8d;");
            row.getChildren().add(unit);
        }

        return row;
    }

    public static void main(String[] args) { launch(args); }
}
```

### Flowchart — Full Converter Application

```
Application starts → start() called
         │
         ▼
Build all UI components (labels, text fields, buttons)
         │
         ▼
Register event handlers on buttons and text fields
         │
         ▼
Show the Stage
         │
         ▼
Wait for user interaction
         │
    ┌────┴─────────────────────────────┐
    │                                  │
    ▼                                  ▼
"Convert" clicked                  "Clear" clicked
    │                                  │
    ▼                                  ▼
Read kmInput.getText()         Clear all text fields
    │                                  │
    ▼                                  ▼
Is input empty?               Request focus on input
    │ Yes → show warning              │
    │ No  → try Double.parseDouble()  ▼
    │           │                  Wait for next input
    │       NumberFormatEx?
    │         Yes → show error
    │         No  →
    │              Is km < 0?
    │                Yes → show warning
    │                No  →
    │                     converter.setKilometres(km)
    │                     Update 4 output fields
    │                     Show success message
    ▼
Wait for next user interaction
```

---

# 20. Quick Reference Cheat Sheet

## Component Creation & Positioning

```java
// --- BUTTON ---
Button btn = new Button("Label");
btn.relocate(x, y);
btn.setPrefSize(width, height);
btn.setStyle("-fx-base: blue; -fx-text-fill: white;");
btn.setDisable(true);           // Gray out
btn.setVisible(false);          // Hide
btn.setOnAction(handler);       // Register click handler

// --- LABEL ---
Label lbl = new Label("Text");
lbl.relocate(x, y);
lbl.setPrefSize(width, height);
lbl.setText("New text");        // Change text dynamically
lbl.setStyle("-fx-font-size: 14px; -fx-text-fill: red;");

// --- TEXTFIELD ---
TextField tf = new TextField();
tf.relocate(x, y);
tf.setPrefSize(width, height);
tf.setPromptText("placeholder...");
tf.setText("initial value");
tf.setEditable(false);          // Read-only
String val = tf.getText();      // Read value
tf.clear();                     // Empty the field

// --- LISTVIEW ---
ObservableList<String> data = FXCollections.observableArrayList("A","B","C");
ListView<String> lv = new ListView<>(data);
lv.relocate(x, y);
lv.setPrefSize(width, height);
String sel = lv.getSelectionModel().getSelectedItem();
data.add("D");                  // Dynamic add (updates UI automatically)

// --- COMBOBOX ---
ComboBox<String> cb = new ComboBox<>(data);
cb.setPromptText("Choose...");
cb.setValue("A");               // Pre-select
String chosen = cb.getValue();  // Get selection
```

## Type Conversion — String ↔ Number

```java
// String → Number
int    i = Integer.parseInt("42");
double d = Double.parseDouble("3.14");
float  f = Float.parseFloat("1.5f");

// Number → String (for setText)
String s1 = "" + 42;                    // Simple concatenation
String s2 = String.format("%.2f", 3.14159);  // "3.14" (2 decimal places)
String s3 = Integer.toString(42);
```

## Event Handler Templates

```java
// --- ActionEvent (Button click / Enter key) ---
myButton.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e) {
        // your code
    }
});

// --- MouseEvent (click, hover, drag) ---
myNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e) {
        double x = e.getX();   // click position
        double y = e.getY();
    }
});

// --- KeyEvent (keyboard input) ---
myNode.setOnKeyPressed(new EventHandler<KeyEvent>() {
    public void handle(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) { /* ... */ }
        if (e.getCode() == KeyCode.ESCAPE) { /* ... */ }
    }
});

// --- Identifying event source (for shared handlers) ---
EventHandler<ActionEvent> shared = new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e) {
        Button source = (Button) e.getSource();
        String text = source.getText();   // What does the button say?
    }
};
```

## JavaFX App Skeleton

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Add components to root...

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("My App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

## CSS Style Properties Reference

| Property | Example | Effect |
|----------|---------|--------|
| `-fx-font` | `-fx-font: bold 14 Arial;` | Font weight, size, family |
| `-fx-font-size` | `-fx-font-size: 16px;` | Font size only |
| `-fx-font-weight` | `-fx-font-weight: bold;` | Bold text |
| `-fx-text-fill` | `-fx-text-fill: rgb(255,0,0);` | Text color |
| `-fx-base` | `-fx-base: rgb(0,0,200);` | Component background |
| `-fx-background-color` | `-fx-background-color: white;` | Pane/region background |
| `-fx-border-color` | `-fx-border-color: gray;` | Border color |
| `-fx-border-radius` | `-fx-border-radius: 5;` | Rounded corners |
| `-fx-padding` | `-fx-padding: 10 5 10 5;` | Inner spacing (T R B L) |
| `-fx-cursor` | `-fx-cursor: hand;` | Mouse cursor style |

---

## Key Concepts Summary

| Concept | Core Idea |
|---------|-----------|
| **User Interface** | The bridge between user and software — input + output |
| **Model–UI Separation** | Business logic must never know about the UI |
| **GUI** | Visual, window-based, event-driven interaction |
| **JavaFX** | Java's modern GUI + graphics + multimedia framework |
| **Stage** | The OS window (the outermost container) |
| **Scene** | The drawing canvas inside the Stage |
| **Node** | Every visual element — button, label, pane, shape |
| **Scene Graph** | The tree of all Nodes — parent/child relationships |
| **FXML** | XML-based declarative UI description language |
| **Scene Builder** | Visual drag-and-drop tool that generates FXML |
| **Pane** | A container that holds and optionally arranges Nodes |
| **Event** | A signal that something happened (user action, timer, etc.) |
| **Event Handler** | The code that runs in response to a specific event |
| **ActionEvent** | Fired by button clicks, Enter in text fields |
| **MouseEvent** | Fired by all mouse interactions |
| **KeyEvent** | Fired by keyboard key presses/releases |
| **setOnAction()** | Registers an ActionEvent handler on a component |
| **event.getSource()** | Returns the component that triggered the event |
| **Custom Pane** | A class extending Pane — reusable grouped component |

---

*End of Study Guide — JavaFX & GUI Programming*
*Covers: User Interfaces • Model–UI Pattern • JavaFX Architecture • Scene Graph • FXML • Components • Layouts • Custom Panes • Event Handling*
