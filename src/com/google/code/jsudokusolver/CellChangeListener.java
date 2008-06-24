package com.google.code.jsudokusolver;

/**
 *
 * @author David Grant
 */
public interface CellChangeListener {
    void candidatesChanged(CandidateChangeEvent event);
    void digitChanged(DigitChangeEvent event);
}
