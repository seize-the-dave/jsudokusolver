package com.google.code.jsudokusolver;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of the smallest element of a Sudoku grid
 * 
 * @author David Grant
 */
public class Cell {
    private final Set<Integer> candidates;
    private final House row;
    private final House column;
    private final House box;
    private Integer digit;
    
    public Cell(House row, House column, House box, Set<Integer> candidates)
    {
        this.candidates = candidates;
        this.row = row;
        this.column = column;
        this.box = box;
        
        row.addCell(this);
        column.addCell(this);
        box.addCell(this);
    }
    
    /**
     * @return the candidates for this cell
     */
    public Set<Integer> getCandidates() {
        return candidates;
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
    public boolean hasCandidate(Integer digit) {
        return candidates.contains(digit);
    }
    
    public boolean removeCandidate(Integer candidate) {
        return candidates.remove(candidate);
    }
    
    public boolean removeCandidates(Set<Integer> candidates) {
        return this.candidates.removeAll(candidates);
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
        
        row.removeCandidate(digit);
        column.removeCandidate(digit);
        box.removeCandidate(digit);
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
        Set<Integer> candidateSet = new HashSet<Integer>();
        for (Integer candidate : candidates) {
            candidateSet.add(candidate);
        }
        return candidateSet;
    }
}
