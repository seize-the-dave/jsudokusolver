package com.google.code.jsudokusolver;

/**
 * This interface represents a solving strategy.
 */
public interface SolverStrategy 
{
    /**
     * Attempt to solve the grid with this strategy.
     * 
     * This method should return as soon as a atomic set of changes is made, 
     * either to a cell's candidates or its digit
     * 
     * @return true if a change was made; false otherwise
     */
    boolean solve(Grid grid) throws IllegalPuzzleException;
}
