package com.google.code.jsudokusolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is the representation of a non-cell constraint, e.g. a row or a
 * column.
 * 
 * @author David Grant
 */
public class House {
    private final List<Cell> cells;
    private final int offset;
    private final int size;
    
    /**
     * Constructor
     * 
     * @param size the size of this house
     * @param offset the offset of this house
     */
    public House(int size, int offset) {
        cells = new ArrayList<Cell>(size);
        this.size = size;
        this.offset = offset;
    }
    
    /**
     * Add a Cell to this house
     * 
     * @param cell the Cell
     */
    public void addCell(Cell cell) {
        cells.add(cell);
    }
    
    /**
     * Returns a List of the Cells added to this house
     * 
     * @return an unmodifiable list
     */
    public List<Cell> getCells() {
        return Collections.unmodifiableList(cells);
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
    public void removeCandidate(Integer candidate) {
        for (Cell cell : cells) {
            cell.remove(candidate);
        }
    }
    
    /**
     * Returns the offset of this grid within its grid
     * 
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }
    
    public int getChute() {
        return (int) (offset % Math.sqrt(size));
    }
    
    @Override
    public String toString() {
        return cells.toString();
    }
    
    /**
     * Shortcut for adding a CellChangeListener to each cell in this house
     * 
     * @param listener the listener to add
     */
    public void addCellChangeListener(CellChangeListener listener) {
        for (Cell cell : cells) {
            cell.addCellChangeListener(listener);
        }
    }
    
    /**
     * Shortcut for removing a CellChangeListener from each cell in this house
     * 
     * @param listener the listener to remove
     */
    public void removeCellChangeListener(CellChangeListener listener) {
        for (Cell cell : cells) {
            cell.removeCellChangeListener(listener);
        }
    }
}
