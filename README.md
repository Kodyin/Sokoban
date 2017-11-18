# Sokoban
AI solver
## Project target
We build this prototype to solve Sokoban puzzles with given map matrix. We used the concept similar to A star, which is a search algorithm based on status.

For each puzzle, we guarantee to find a optimal solution with the least moves.

## Input format
input is 5 lines defining the board:

sixeH sizeV, e.g. "3 5"

nWallSquares a list of coordinates of wall squares, e.g. "12 1 1 1 2 1 3 2 1 2 3 3 1 3 3 4 1 4 3 5 1 5 2 5 3"

nBoxes a list of coordinates of boxes, e.g. "1 3 2"

nStorageLocations a list of coordinates of storage locations, e.g. "1 4 2"

playes initial locatin x and y, e.g. "2 2"

output is a single line, beginning with nMoves followed by a sequence of letters (U,D,L,R) indicating direction of the move, e.g. "1 D".
