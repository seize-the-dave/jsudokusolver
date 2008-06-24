package com.google.code.jsudokusolver;

import java.util.ArrayList;
import java.util.List;

public class House {
    private final List<Cell> cells;
    private final int offset;
    
    public House(int size, int offset)
    {
        cells = new ArrayList<Cell>(size);
        this.offset = offset;
    }
    
    public void addCell(Cell cell) {
        cells.add(cell);
    }
    
    public List<Cell> getCells() {
        return cells;
    }
    
    public void removeCandidate(Integer candidate) {
        for (Cell cell : cells)
        {
            cell.remove(candidate);
        }
    }
    
    public int getOffset() {
        return offset;
    }
    
    @Override
    public String toString() {
        return cells.toString();
    }
    
    public void addCellChangeListener(CellChangeListener listener) {
        for (Cell cell : cells) {
            cell.addCellChangeListener(listener);
        }
    }
    
    public void removeCellChangeListener(CellChangeListener listener) {
        for (Cell cell : cells) {
            cell.removeCellChangeListener(listener);
        }
    }
}
