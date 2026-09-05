# Star Catcher - Project Documentation

## 1. Overview

**Star Catcher** is a simple 2D game developed using **Java and libGDX**.

The player controls a character at the bottom of the screen and moves horizontally to catch falling stars. The project was created to practice Java programming concepts while learning the fundamentals of developing a game with the libGDX framework.

---

## 2. Technologies Used

The project currently uses:

* **Java** - Main programming language
* **libGDX** - Game development framework
* **Gradle** - Project build system
* **IntelliJ IDEA** - Development environment

---

## 3. Game Concept

The game consists of a player-controlled object and falling stars.

The basic gameplay loop is:

1. A star appears above the play area.
2. The star falls downward.
3. The player moves horizontally.
4. The player attempts to catch the falling star.
5. The game detects whether the player and star have collided.
6. The relevant game state is updated.

The main objective is to successfully catch the falling stars.

---

## 4. Project Setup

The project was created using libGDX and uses its desktop application configuration to run the game.

The project is organized according to the standard libGDX project structure, with the main game logic located in the `core` module.

The main game class is located under:

```text
core/
└── src/
    └── main/
        └── java/
            └── io/
                └── github/
                    └── starcatcher/
```

---

## 5. Game Initialization

The game is built around libGDX's `ApplicationAdapter`.

The main game class extends `ApplicationAdapter`, which provides lifecycle methods that allow the game to initialize resources, continuously update the game, and render its contents.

The main stages of the game are handled through:

* `create()` — initializes game resources.
* `render()` — repeatedly updates and draws the game.

This provides the foundation for the game's continuous execution.

---

## 6. Rendering

The game uses libGDX's rendering functionality to display objects on the screen.

A `SpriteBatch` is used to draw graphical elements.

The rendering process occurs repeatedly inside the `render()` method. Before drawing the next frame, the screen is cleared so that the previous frame does not remain visible.

The general rendering process is:

```text
Clear screen
    ↓
Begin SpriteBatch
    ↓
Draw game objects
    ↓
End SpriteBatch
```

---

## 7. Player

The player is represented as a rectangular game object.

A `Rectangle` is used to store the player's:

* X position
* Y position
* Width
* Height

Using a rectangle provides both a convenient way to position the player and a boundary that can later be used for collision detection.

---

## 8. Player Movement

The player can be controlled using the keyboard.

Keyboard input is handled through libGDX's `Input` class.

The game checks whether the left or right movement keys are being pressed and changes the player's horizontal position accordingly.

The basic movement logic is:

```text
Check keyboard input
       ↓
Is left pressed?
       ↓
Move player left

Is right pressed?
       ↓
Move player right
```

Movement is performed during the game's update cycle, allowing the player to respond continuously to keyboard input.

---

## 9. Falling Stars

Stars are represented as game objects that move vertically down the screen.

Each star has a position that determines where it appears.

During the game loop, its vertical position is changed so that it moves downward.

Conceptually:

```text
Star appears
     ↓
Move downward
     ↓
Check position
     ↓
Continue falling
```

The falling movement creates the main interaction between the player and the star.

---

## 10. Collision Detection

Collision detection is used to determine whether the player has caught a falling star.

Both the player and the falling object can be represented using `Rectangle` objects.

The rectangles provide boundaries that can be compared to determine whether the two objects overlap.

The basic process is:

```text
Player Rectangle
       +
Star Rectangle
       ↓
Check for intersection
       ↓
Collision detected
```

This allows the game to recognize when the player successfully catches a star.

---

## 11. Game Loop

The game continuously runs through the `render()` method.

Each frame performs the necessary updates and rendering.

The current game loop can be understood as:

```text
        ┌───────────────┐
        │ Read input    │
        └───────┬───────┘
                ↓
        ┌───────────────┐
        │ Update player │
        │ and objects   │
        └───────┬───────┘
                ↓
        ┌───────────────┐
        │ Check         │
        │ collisions    │
        └───────┬───────┘
                ↓
        ┌───────────────┐
        │ Render screen │
        └───────┬───────┘
                ↓
             Repeat
```

Repeating this process creates the real-time behavior required by the game.

---

## 12. Development Challenges

One of the main challenges while developing the game was understanding how the different parts of a game fit together.

Instead of treating the program as one large piece of code, the game can be understood as several smaller systems:

* Input handling
* Player movement
* Object movement
* Collision detection
* Rendering

Understanding how these systems interact helped make the game easier to develop incrementally.

---

## 13. Current State

At this stage, Star Catcher has the basic foundation of a playable 2D game:

* Java and libGDX project setup
* Game initialization
* Screen rendering
* Player object
* Keyboard-controlled movement
* Falling stars
* Collision detection
* Continuous game loop

Further gameplay features will be added as development continues.
