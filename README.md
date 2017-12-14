# Sokoban
AI solver
CS271 2017 Fall term project


Author: Gaozhiquan Wang, Luqi Tian, Yishan Ma

## Project target
We build this prototype to solve Sokoban puzzles with given map matrix. We used the concept similar to A*, which is a search algorithm based on states.

For each puzzle, we guarantee to find a optimal solution with the least moves.



## Instruction

Compile files

    cd src
    javac */*.java

The main method is SokobanMain. The program takes command-line arguments with
the following options.

    java solver/Main [-Datasource] [-Input format] [Sokoban input file]

    Datasource
        -l      Benchmark example   (under dir: src/data/Sokoban)
        -d      Example files       (under dir: src/data)
    
    Input format
        -m      Mapping format
        -s      Statement format
    e.g.  java solver/Main -l -s sokoban1.txt
          java solver/Main -d -m m1.txt

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

    1. String representation of initial map
    2. Move solution
    3. Number of states explored
    4. States seen before (identical states)
    5. Pruned deadlock states
    6. Time elapsed in millis
