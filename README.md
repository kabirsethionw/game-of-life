<h1>Game of Life:</h1>

<h2>Requirements:</h2>

 - Infinite two-dimensional orthogonal grid of square cells.
 - Each of which is in one of two possible states, `alive` or `dead`.
 - At each step in time, the following transitions occur:
   1. Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
   2. Any live cell with two or three live neighbors lives on to the next generation.
   3. Any live cell with more than three live neighbors dies, as if by overcrowding.
   4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

 <h2>Objectives</h2>
 - Implement the game of life data structures and algorithm.
 - Demonstrate that game of life algorithm works.

<h2> Testing and Demonstration</h2>

- Use the <a href="https://conwaylife.com/wiki/Gosper_glider_gun">‘Glider’</a> pattern placed in the middle of the 25x25 cell universe for demonstration.
- Output the resulting grid in the console.

<h2>Assumptions</h2>
- Given *MxN* grid, user will input the live cells in that grid and not somewhere outside.
- The grid expands if the live cells reach the boundary, by a factor `F` and the older cell values are translated to the center of the new matrix, making the rest of the new cells are padding of the older ones.