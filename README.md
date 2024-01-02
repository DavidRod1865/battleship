# Battleship Game in Java

This repository contains a Java implementation of the classic game Battleship. The game is built using Java Swing for the graphical user interface and consists of two main classes: `Battleship` and `Ship`. The `Battleship` class handles the game logic and UI, while the `Ship` class represents the individual ships on the grid.

## Features

- A 10x10 grid representing the ocean where ships are hidden.
- Implementation of different types of ships (e.g., Carrier, Battleship, Cruiser, Submarine, Destroyer) with varying sizes.
- Random placement of ships on the grid at the start of the game.
- Count of total guesses and tracking of the number of ships sunk.
- Simple and intuitive graphical user interface.

## Classes

### Battleship
- Manages the main game logic and user interactions.
- Initializes the game board and randomly places the ships.
- Processes player's guesses and updates the game state.

### Ship
- Represents a ship with a name and size.
- Tracks the number of hits a ship has taken.
- Determines if a ship is sunk based on the number of hits.

## How to Run

Ensure you have Java installed on your computer to run the game. Follow these steps:

1. Clone the repository:

```git clone https://github.com/DavidRod1865/battleship.git```

2. Navigate to the directory containing the Java files.
3. Compile the Java programs:

```javac Battleship.java Ship.java```

4. Run the compiled Battleship class:

```java Battleship```

## Game Instructions

- At the start, ships are randomly placed on the grid.
- Click on a cell in the grid to make a guess.
- The game will indicate whether it's a 'Hit' or 'Miss'.
- The goal is to sink all ships with the fewest number of guesses.
- The game ends when all ships are sunk.

## Contributing

Contributions are welcome. Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## Contact

Your Name - David.Rod1865@gmail.com

Project Link: https://github.com/DavidRod1865/battleship


