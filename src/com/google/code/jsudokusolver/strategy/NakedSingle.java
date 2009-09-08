package com.google.code.jsudokusolver.strategy;

import java.util.List;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.RepeatedDigitException;
import com.google.code.jsudokusolver.SolverStrategy;
import com.google.code.jsudokusolver.Util;

public class NakedSingle implements SolverStrategy {
    private static final String NAME = "Naked Single";
    
    public String getName() {
        return NAME;
    }
    
    public boolean solve(Grid grid) throws RepeatedDigitException
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
    
    private boolean solveHouses(List<? extends House> houses) throws RepeatedDigitException
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
                    Util.logCandidateRetention(cell, candidate, NAME);
                    return true;
                }
            }
        }
        return false;
    }
}
