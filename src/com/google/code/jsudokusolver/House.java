package com.google.code.jsudokusolver;

import java.util.ArrayList;
import java.util.List;

public class House {
    private final List<Cell> cells;
    
    public House(int size)
    {
        cells = new ArrayList<Cell>(size);
    }
    
    public void addCell(Cell cell)
    {
        cells.add(cell);
    }
    
    public List<Cell> getCells()
    {
        return cells;
    }
    
    public void removeCandidate(Integer candidate)
    {
        for (Cell cell : cells)
        {
            cell.removeCandidate(candidate);
        }
    }
    
    @Override
    public String toString()
    {
        return cells.toString();
    }
}
