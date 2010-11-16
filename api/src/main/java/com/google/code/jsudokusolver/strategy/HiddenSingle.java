package com.google.code.jsudokusolver.strategy;

import java.util.Collection;
import java.util.HashSet;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.RepeatedDigitException;
import com.google.code.jsudokusolver.SolverStrategy;

/**
 * @author David Grant
 */
public class HiddenSingle implements SolverStrategy 
{
	/**
	 * {@inheritDoc}
	 */
    public boolean solve(Grid grid) throws RepeatedDigitException 
    {
    	Collection<House> houses = grid.getHouses();
    	boolean solved = false;
    	
    	for (int i = 1; i <= 9; i++)
    	{
    		for (House house : houses)
    		{
    			solved |= solveHouse(house, i);
    		}
    	}
    	return solved; 
    }
    
    private boolean solveHouse(House house, int candidate) throws RepeatedDigitException 
    {
        Collection<Cell> candidates = new HashSet<Cell>();
        for (Cell cell : house.getCells()) 
        {
            if (cell.isSolved()) 
            {
                if (cell.getDigit().equals(candidate)) 
                {
                    return false;
                }
                continue;
            }
            if (cell.contains(candidate)) 
            {
                candidates.add(cell);
            }
        }
        if (candidates.size() == 1)
        {
            Cell cell = candidates.iterator().next();
            cell.setDigit(candidate);
            return true;
        }
        return false;
    }
}
