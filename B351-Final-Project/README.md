# Algorithms for International Draughts

Topic: CSCI-B 351 Final Project

Authors: Hunter Vevia, Kaleb Robinson & Aidan Schilling

## Introduction

We developed an implementation, using Python 3 and the tKinter UI library, of International Draughts that supports both human and computer play. In order to support computer play, we employ both the Minimax and Monte-Carlo Tree Search algorithms, alongside an assortment of heuristics. 

## International Draughts

International Draughts is a two player, turn-based, game that bears many similarities to checkers. Both games are played on an NxN board (usually n=8, or n=10). At the start of the game, each player starts with 12-20 pawns (depending on the board size) positioned on every other tile. On their turn, each player can move any pawn one space diagonally forward, so long as the destination space is unoccupied. Pawns can only move forward. If an enemy piece is located on the destination spot, the player can "jump" over that piece to one more spot over, in order to capture it. If the capture conditions are met again after completing an intial capture, then the player may immediately capture again before ending their turn. This is known as a chain capture. Additionally, pawns that make it all the way to the other side of the board become promoted to kings. Kings follow the same movement rules as pawns, but they can move both forwards diagonally and backwards diagonally. The player that captures all of their opponent's pieces wins the game. Draw conditions typically depend on specific rule sets at tournaments. 

[Click HERE read the wikipedia article on Checkers](https://en.wikipedia.org/wiki/Checkers)

### International Draughts vs. Checkers

Here are some of the primary differences of the rule set of International Draughts vs. the rule set of Checkers:

- International Draughts is played on a 10x10 board space, meaning both players start with 20 pawns, compared to Checkers' 8x8 board space and 12 starting pawns. 

- Captures are FORCED. If a player has the ability to capture an opponents piece when their turn starts, they must make that move and end their turn. 

- If multiple captures are possible. The player's next move must be the longest capture chain available. 

- International Draughts allows captures in any direction. This means that pawns can move backwards, but only when capturing. Checkers only allows pawns to capture forwards.

- In International Draughts, kings can "fly" as many spaces in one direction as they wish. Kings can do this when capturing as well. In Checkers, kings can only move 1 space in each permissible direction. 

[Click HERE read the wikipedia article on International Draughts](https://en.wikipedia.org/wiki/International_draughts)

### Our specific implementation

These are some choices we made, specific to our implementation of International Draughts:

- Draws occur if the same game state has occurred three times in a row (i.e. players repeating the same move over and over). This differs from official tournament rules for the sake of simplicity in our implementation. 

## File Structure

Our International Draughts program has the following file structure:

```
Root:
    |----AI/
    |     |---- Advanced_Heuristics/...
    |     |---- Heuristics/...
    |     |---- baseAI.py
    |     |---- mctsAI.py
    |
    |---- draughts.py
    |---- game_logic.py
    |---- training.py
```

### Root directory files 

#### `draughts.py`
- This file handles the user interface for our International Draguths implementation. In `draughts.py`, you will find two classes: `GameController` and `DraughtsUI`. The `GameController` class handles managing the turns between two given players and updating the game state after each turn. The `DraughtsUI` class handles tKinter widgets and having the user inferface visually reflect the current game state.

#### `game_logic.py`
- This file contains the representation of the state space and the functions that update the state space, according to the rules of checkers. In `game_logic.py`, you will find three classes: `Move`,`GameState`, and `RulesEngine`. The `Move` and `GameState` classes are data classes, meaning they essentially function as structs and do not have their own methods. The `Move` class contains two components, which represent the path a piece takes when making a move and any captures that the piece makes. The `GameState` class stores the game board as a 2-dimensional list and the current turn. The `RulesEngine` class contains all of the logic for making moves, finding captures, detecting wins, and detecting draws. 

#### `training.py`
- This files primary responsibility is to pit heuristics against other heuristics. Using a given list of heuristics, the file will iteratively choose one heuristic and simulate multiple instances of the game with the choosen heuristic against all other heuristics. This will repeat until every heuristic has competed against all other heuristics. After each iteration, the file writes the results of each trial to `results.txt`, which contains the win rates and timing metrics for each heuristic that was tested. 

### AI directory files

#### Subdirectores

##### `Advanced_Heuristics`
- This folder contains a few heuristic files that we consider to be "advanced heuristics". These heuristics typically involve helper functions in addition to the base heuristic function, or they combine components from multiple trivial heuristics. Our current best advanced heuristic is `SafetyAI`, which only makes moves that avoid immediate captures, or if captures are makes the least worst move possible. 

##### `Heuristics`
- This folder contains multiple heuristic files that we consider to be "trivial heuristics". These heuristics involve very simple calculations, that are calculated separately for each player. Heuristics that fall under this category include: the number of pawns each player has on the board, number of kings each player has on the board, the number of pieces each player has in the center and much more.

#### Standalone files

##### `baseAI.py`
- This file contains a MiniMax with Alpha-Beta pruning implementation. This is one of the two base algorithms that we have implemented for computer players to choose their moves based on a given heuristic. In addition, this file contains three helper functions for calculating heuristics. These helper functions include a function that counts all of the pieces on the board for each player, a function that simulates moves and provides a new game state without changing the current one, and a function that finds any pieces that are under threat of capture. 

##### `mcts.py`
- This file contains an implementation of Monte-Carlo Tree Search instead of Minimax. Otherwise, this file is functionally similar to `baseAI.py`, whereby the heuristic function is left empty for other files to each provide a different heuristic implementation. 

## Algorithms

### Minimax with Alpha-Beta Pruning

```

```

[Click HERE to read the wikipedia article on Alpha-Beta Pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning)

### Monte-Carlo Tree Search

```

```

[Click HERE to read the wikipedia article on MCTS](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search)

## Creating your own heuristic

The process of creating your own heuristic is simple. Just follow these three steps:

1. Create a new file in either of the heuristic folders.
2. Import the BaseAI class from `baseAI.py`
3. 