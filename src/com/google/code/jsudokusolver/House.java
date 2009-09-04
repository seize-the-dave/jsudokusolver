package com.google.code.jsudokusolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is the representation of a non-cell constraint, e.g. a row or a
 * column.
 * 
 * @author David Grant
 */
public class House 
{
    private final List<Cell> cells;
    private final int offset;
    
    /**
     * Constructor
     * 
     * @param size the size of this house
     * @param offset the offset of this house
     */
    public House(int offset) 
    {
        cells = new LinkedList<Cell>();
        this.offset = offset;
    }
    
    /**
     * Add a Cell to this house
     * 
     * @param cell the Cell
     */
    public void addCell(Cell cell) 
    {
        cells.add(cell);
    }
    
    /**
     * Returns a List of the Cells added to this house
     * 
     * @return an unmodifiable list
     */
    public List<Cell> getCells() 
    {
        return Collections.unmodifiableList(cells);
    }
    
    public Set<Cell> getCellsWithCandidate(int candidate) 
    {
        Set<Cell> candidateCells = new TreeSet<Cell>();
        for (Cell cell : cells) {
            if (cell.isSolved() == false && cell.contains(candidate)) 
            {
                candidateCells.add(cell);
            }
        }
        return candidateCells;
    }
    
    public List<Cell> getUnsolvedCells() {
        List<Cell> unsolvedCells = new ArrayList<Cell>();
        for (Cell cell : cells) {
            if (cell.isSolved() == false) {
                unsolvedCells.add(cell);
            }
        }
        return unsolvedCells;
    }
    
    /**
     * Remove a candidate from each of the cells in this house
     * 
     * @param candidate the candidate to remove
     */
    protected void removeCandidate(Integer candidate, ReferenceReason reason) {
        for (Cell cell : cells) 
        {
            cell.remove(candidate, reason);
        }
    }
    
    /**
     * Returns the offset of this grid within its grid
     * 
     * @return the offset
     */
    public int getOffset() 
    {
        return offset;
    }
    
    @Override
    public String toString() 
    {
        return cells.toString();
    }
    
    /**
     * Shortcut for adding a CellChangeListener to each cell in this house
     * 
     * @param listener the listener to add
     */
    public void addCellChangeListener(CellChangeListener listener) 
    {
        for (Cell cell : cells) 
        {
            cell.addCellChangeListener(listener);
        }
    }
    
    /**
     * Shortcut for removing a CellChangeListener from each cell in this house
     * 
     * @param listener the listener to remove
     */
    public void removeCellChangeListener(CellChangeListener listener) 
    {
        for (Cell cell : cells) 
        {
            cell.removeCellChangeListener(listener);
        }
    }
}
