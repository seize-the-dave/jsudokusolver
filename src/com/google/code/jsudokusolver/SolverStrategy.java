package com.google.code.jsudokusolver;

/**
 * This interface represents a solving strategy.
 * 
 * @author David Grant
 */
public interface SolverStrategy 
{
    /**
     * Returns the name of this strategy.
     * 
     * @return the name
     */
//    String getName();
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
