# Sokoban
AI solver
CS271 2017 Fall term project
Author: Gaozhiquan Wang

## Project target
We build this prototype to solve Sokoban puzzles with given map matrix. We used the concept similar to A*, which is a search algorithm based on status.

For each puzzle, we guarantee to find a optimal solution with the least moves.



## Instruction

Compile files

    cd src
    javac *.java

The main method is SokobanMain. The program takes command-line arguments with
the following options.

    java SokobanMain [-option] [Sokoban input file]

    Options
        -b      Breadth-first search
        -d      Depth-first search
        -u      Uniform-cost search (move = 1, push = 2)
        -gb     Greedy best-first search with number of boxes on goal heuristic
        -gm     Greedy best-first search with Manhattan distances heuristic
        -ab     AStar with number of boxes on goal heuristic
        -am     AStar with goals and boxes Manhattan distances heuristic




## Input


There are two different kinds of input support.
Mapping format

    #   (hash)      Wall 
    $   (dollar)    Box on floor 
    \*   (asterisk)  Box on goal 
    .   (period)    Empty goal 
    @   (at)        Player on floor 
    \+   (plus)      Player on goal 
    

Statement format

    input is 5 lines defining the board:
    sixeH sizeV, e.g. "3 5"
    nWallSquares a list of coordinates of wall squares, e.g. "12 1 1 1 2 1 3 2 1 2 3 3 1 3 3 4 1 4 3 5 1 5 2 5 3"
    nBoxes a list of coordinates of boxes, e.g. "1 3 2"
    nStorageLocations a list of coordinates of storage locations, e.g. "1 4 2"
    playes initial locatin x and y, e.g. "2 2"
    output is a single line, beginning with nMoves followed by a sequence of letters (U,D,L,R) indicating direction of the move, e.g. "1 D".

## Output


The output is in the following format.

    1. String representation of initial state
    2. Move solution
    3. Number of states explored
    4. Time elapsed in millis
