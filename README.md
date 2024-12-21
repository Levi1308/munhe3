# Dungeon and Dragons (D&D) Game - Java Implementation

Welcome to **munhe3** – a Dungeon and Dragons (D&D) inspired game implemented in Java. This project is part of an Object-Oriented Programming (OOP) course, demonstrating advanced OOP principles, design patterns, and game logic.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [How to Play](#how-to-play)
- [File Structure](#file-structure)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Overview
This is a simple turn-based strategy game set in a dungeon environment. Players can choose different character classes (Warrior, Mage, Hunter, etc.), fight enemies, and progress through levels.

The game runs in the terminal (CLI-based) and includes multiple levels designed to test players' skills.

## Features
- **Multiple Character Classes:** Choose from Warrior, Mage, Hunter, and more.
- **Enemies and Boss Fights:** Engage in battles with enemies and challenging bosses.
- **Dynamic Levels:** Explore different levels stored in text files.
- **Turn-based Mechanics:** Strategic gameplay with classic turn-based mechanics.
- **Test Suite:** Unit tests for core game components.

## Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/munhe3.git
   ```
2. Open the project in IntelliJ IDEA (or your preferred Java IDE).
3. Ensure Java 11 or higher is installed.
4. Build the project:
   ```bash
   javac -d out src/**/*.java
   ```
5. Run the game:
   ```bash
   java -cp out UI.Main
   ```

## How to Play
1. Launch the game.
2. Choose your character class.
3. Navigate through the dungeon by entering commands.
4. Battle enemies, collect loot, and survive until the end of the level.
5. Progress through increasingly difficult levels.

## File Structure
```
munhe3-main/
│
├── src/                  # Source code
│   ├── GameTiles/        # Game components (tiles, enemies, players, etc.)
│   ├── UI/               # User interface (CLI and main game logic)
│   ├── META-INF/         # Manifest file
│
├── levels_dir/           # Level configuration files
├── GameTests/            # Unit tests
├── out/                  # Compiled files
│
└── UML.drawio            # UML diagram for the project
```

## Testing
Unit tests are located in the `GameTests` directory. Run tests to ensure game components are functioning correctly:
```bash
java -cp out GameTests.GameTileTest
```

## Contributing
Feel free to contribute to this project by submitting pull requests or reporting issues.

## License
This project is licensed under the MIT License.

