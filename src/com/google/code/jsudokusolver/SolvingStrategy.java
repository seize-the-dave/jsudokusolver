package com.google.code.jsudokusolver;

/**
 * This interface represents a solving strategy.
 * 
 * @author David Grant
 */
public interface SolvingStrategy {
    /**
     * Returns the name of this strategy.
     * 
     * @return the name
     */
    String getName();
    /**
     * Used for injecting the grid into the solving strategy.
     * 
     * @param grid the grid
     */
    void setGrid(Grid grid);
    /**
     * Takes a step towards solving the grid.  This method should return as
     * soon as a change is made, either to a cell's candidates or its digit
     * 
     * @return true if a change was made; false otherwise
     */
    boolean solve();
}
