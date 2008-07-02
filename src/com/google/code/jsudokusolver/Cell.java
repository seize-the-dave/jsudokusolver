package com.google.code.jsudokusolver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Representation of the smallest element of a Sudoku grid
 * 
 * @author David Grant
 */
public class Cell implements Comparable<Cell> {
    private final Set<Integer> candidates;
    private final House row;
    private final House column;
    private final House box;
    private Integer digit;
    private Set<CellChangeListener> listeners = new HashSet<CellChangeListener>();
    
    public Cell(House row, House column, House box, Set<Integer> candidates) {
        this.candidates = candidates;
        this.row = row;
        this.column = column;
        this.box = box;
        
        row.addCell(this);
        column.addCell(this);
        box.addCell(this);
    }
    
    /**
     * Returns an unmodifiable set.
     * 
     * @return the candidates for this cell
     */
    public Set<Integer> getCandidates() {
        return Collections.unmodifiableSet(candidates);
    }
    
    /**
     * @return true if this cell has been solved; false otherwise
     */
    public boolean isSolved() {
        return digit != null;
    }
    
    /**
     * 
     * @param digit
     * @return true if this 
     */
    public boolean contains(Integer digit) {
        return candidates.contains(digit);
    }
    
    public boolean remove(Integer candidate) {
        final Set<Integer> before = getCandidates();
        if (candidates.remove(candidate)) {
            final Set<Integer> after = getCandidates();
            fireCandidateChangeEvent(before, after);
            return true;
        }
        return false;
    }
    
    public boolean removeAll(Set<Integer> candidates) {
        final Set<Integer> before = getCandidates();
        if (this.candidates.removeAll(candidates)) {
            final Set<Integer> after = getCandidates();
            fireCandidateChangeEvent(before, after);
            return true;
        }
        return false;
    }
    
    public boolean retainAll(Set<Integer> candidates) {
        final Set<Integer> before = getCandidates();
        if (this.candidates.retainAll(candidates)) {
            Set<Integer> after = getCandidates();
            fireCandidateChangeEvent(before, after);
            return true;
        }
        return false;
    }
    
    public House getRow()
    {
        return row;
    }
    
    public House getColumn()
    {
        return column;
    }
    
    public House getBox()
    {
        return box;
    }
    
    public Integer getDigit()
    {
        return digit;
    }
    
    public void setDigit(Integer digit)
    {
        this.digit = digit;
        candidates.clear();
        
        row.removeCandidate(digit);
        column.removeCandidate(digit);
        box.removeCandidate(digit);
        
        fireDigitChangeEvent();
    }
 
    @Override
    public String toString()
    {
        if (digit == null)
        {
            return candidates.toString();
        }
        return digit.toString();
    }
    
    public static Set<Integer> generateCandidateSet(Integer... candidates) {
        Set<Integer> candidateSet = new TreeSet<Integer>();
        for (Integer candidate : candidates) {
            candidateSet.add(candidate);
        }
        return candidateSet;
    }
    
    public String getPosition() {
        return "(" + column.getOffset() + ", " + row.getOffset() + ")";
    }
    
    
    public void addCellChangeListener(CellChangeListener listener) {
        listeners.add(listener);
    }
    
    public void removeCellChangeListener(CellChangeListener listener) {
        listeners.remove(listener);
    }
    
    private void fireDigitChangeEvent() {
        DigitChangeEvent event = new DigitChangeEvent() {
            public Cell getCell() {
                return Cell.this;
            }
        };
        for (CellChangeListener listener : listeners) {
            listener.digitChanged(event);
        }
    }
    
    private void fireCandidateChangeEvent(final Set<Integer> before, final Set<Integer> after) {
        CandidateChangeEvent event = new CandidateChangeEvent() {
            public Set<Integer> getPreChangeCandidates() {
                return before;
            }

            public Set<Integer> getPostChangeCandidates() {
                return after;
            }

            public Cell getCell() {
                return Cell.this;
            }
        };
        for (CellChangeListener listener : listeners) {
            listener.candidatesChanged(event);
        }
    }

    public int compareTo(Cell cell)
    {
        if (column.getOffset() > cell.column.getOffset()) {
            return 1;
        }
        if (column.getOffset() < cell.column.getOffset()) {
            return -1;
        }
        if (row.getOffset() > cell.row.getOffset()) {
            return 1;
        }
        if (row.getOffset() < cell.row.getOffset()) {
            return -1;
        }
        return 0;
    }
}
