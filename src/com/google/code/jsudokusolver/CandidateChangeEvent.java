package com.google.code.jsudokusolver;

import java.util.Set;

/**
 * This interface represents an event that is fired when a cell's candidates
 * are changed.
 * 
 * @author David Grant
 */
public interface CandidateChangeEvent {
    /**
     * Returns the cell's candidates as they were before the change
     * 
     * @return an unmodifiable candidate set
     */
    Set<Integer> getPreChangeCandidates();
    /**
     * Returns the cell's candidates as they were after the change
     * 
     * @return an unmodifiable candidate set
     */
    Set<Integer> getPostChangeCandidates();
    /**
     * Returns the cell that was changed when this event fired.
     * 
     * @return the cell
     */
    Cell getCell();
}
