package com.google.code.jsudokusolver.strategy;

import java.util.List;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;

public class NakedSingle implements SolvingStrategy {
    private static final String NAME = "Naked Single";
    
    public String getName() {
        return NAME;
    }
    
    public boolean solve(Grid grid)
    {
        if (solveHouses(grid.getRows())) 
        {
            return true;
        }
        if (solveHouses(grid.getColumns())) 
        {
            return true;
        }
        if (solveHouses(grid.getBoxes())) 
        {
            return true;
        }
        return false;
    }
    
    private boolean solveHouses(List<? extends House> houses)
    {
        for (House house : houses) 
        {
            for (Cell cell : house.getCells()) 
            {
                if (cell.isSolved()) 
                {
                    continue;
                }
                if (cell.getCandidates().size() == 1) 
                {
                    Integer candidate = cell.getCandidates().iterator().next();
                    cell.setDigit(candidate);
                    Grid.logCandidateRetention(cell, candidate, NAME);
                    return true;
                }
            }
        }
        return false;
    }
}
