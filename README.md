# Sudoku Complexity Analyzer

A Java-based Sudoku solver that analyzes the relationship between puzzle difficulty and solution complexity using backtracking algorithms and custom data structures.

## Overview

This project implements a sophisticated Sudoku solver to investigate how the number of initial clues affects solving time. Using depth-first search with backtracking and a stack-based approach, the solver reveals surprising patterns in computational complexity across different puzzle configurations.

## Key Features

- **Advanced Backtracking Algorithm**: Stack-based depth-first search with intelligent cell selection
- **Smart Cell Selection**: `findBetterCell()` method chooses cells with minimum possible values
- **Complexity Analysis**: Measures solving time vs. number of initial clues
- **Custom Data Structures**: Implemented Stack, Queue, and LinkedList from scratch
- **Scalable Board Sizes**: Supports variable NxN boards (tested up to 25x25)
- **Visual Interface**: GUI display with optional visualization
- **File I/O**: Load puzzles from text files

## Core Components

### Solver Engine
- **Sudoku.java**: Main solver class with backtracking implementation
- **Board.java**: Game board representation and validation logic
- **Cell.java**: Individual cell with possible values tracking
- **Stack.java**: Custom stack implementation for algorithm state management

### Data Structures
- **LinkedList.java**: Custom doubly-linked list with iterator support
- **Queue.java**: FIFO queue implementation
- **Stack.java**: LIFO stack for backtracking state

### Optimization Algorithms
1. **Serial Search**: Basic left-to-right, top-to-bottom cell selection
2. **Smart Selection**: `findBetterCell()` prioritizes cells with fewer possibilities

## Key Findings

### Performance Comparison
The optimized cell selection algorithm dramatically outperforms serial search:
- **Serial Algorithm**: Extremely inefficient for complex puzzles
- **Smart Selection**: Orders of magnitude faster solution times

### Complexity Curve
Analysis of 1000+ puzzles revealed a **parabolic relationship** between initial clues and solving time:

| Initial Clues | Solving Behavior |
|---------------|------------------|
| Very Few (1-10) | Fast - many valid solutions exist |
| Medium (15-20) | **Slowest** - optimal challenge range |
| Many (25+) | Fast - quickly determines unsolvable or finds unique solution |

### Board Size Scaling
- **9x9**: Standard performance
- **16x16**: Manageable with smart algorithms  
- **25x25**: Works but requires significant computation
- **36x36**: Exceeds practical time limits (>1M iterations)

## Technical Implementation

### Algorithm Strategy
```java
// Pseudocode for optimized solver
while (!stack.isEmpty()) {
    Cell bestCell = findBetterCell(); // Select cell with min possibilities
    if (bestCell == null) return solution;
    
    for (int value : possibleValues) {
        stack.push(currentState);
        setCell(bestCell, value);
        
        if (!isValid()) {
            backtrack();
        }
    }
}
```

### Command Line Interface
```bash
java Sudoku [boardSize] [initialClues] [filename]
```

## Test Cases

Includes various puzzle configurations:
- **breakalgorithm.txt**: Demonstrates algorithm performance differences
- **miracleboard.txt**: Challenging puzzle for complexity testing
- **sixteen.txt**: 16x16 board example
- **fourbyfour.txt**: Simple 4x4 puzzle
- **blankboard.txt**: Empty board template

## Performance Insights

1. **Sweet Spot Complexity**: Puzzles with 15-20 initial clues require maximum solving time
2. **Algorithm Efficiency**: Smart cell selection reduces computation by orders of magnitude  
3. **Scalability Limits**: Board sizes beyond 25x25 become computationally prohibitive
4. **Backtracking Efficiency**: Stack-based approach enables efficient state management

## Running the Solver

### Basic Usage
```bash
javac Sudoku.java
java Sudoku board1.txt
```

### Custom Configuration  
```bash
java Sudoku 16 10 customboard.txt
```

## Technical Highlights

- **Custom ADT Implementation**: All data structures built from scratch
- **Algorithmic Optimization**: Multiple solving strategies with performance comparison
- **Empirical Analysis**: Statistical analysis of solving complexity patterns
- **Scalable Architecture**: Supports arbitrary board sizes within computational limits

## Author

**Aayan Shah**  
Computer Science & Physics Student  
[GitHub Profile](https://github.com/aayans314)
