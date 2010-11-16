package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolverStrategy;

/**
 * @see http://www.sudopedia.org/wiki/Naked_Single
 */
public class NakedSingle implements SolverStrategy 
{
    /**
     * {@inheritDoc}
     */
    public boolean solve(Grid grid)
    {
        for (House house : grid.getHouses()) 
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
                    return true;
                }
            }
        }
        return false;
    }
}
