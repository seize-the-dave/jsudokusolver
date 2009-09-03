package com.google.code.jsudokusolver;

/**
 * This is the listener interface that should be implemented in order to listen
 * for changes to a cell.
 * 
 * @author David Grant
 */
public interface CellChangeListener 
{
    /**
     * This is the method called when a cell's candidates change
     * 
     * @param event the event
     */
    void candidatesChanged(CandidateChangeEvent event);
    /**
     * This is the method called when a cell's digit is set
     * 
     * @param event the event
     */
    void digitChanged(DigitChangeEvent event);
}
