package com.google.code.jsudokusolver;

/**
 * This interface represents an event that is fired when a cell has its digit 
 * set by a solver.
 * 
 * @author David Grant
 */
public interface DigitChangeEvent {
    /**
     * Returns the cell that was subject to the digit change
     * 
     * @return the cell
     */
    Cell getCell();
}
