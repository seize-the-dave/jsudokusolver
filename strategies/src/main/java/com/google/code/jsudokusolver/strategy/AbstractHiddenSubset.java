package com.google.code.jsudokusolver.strategy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolverStrategy;

/**
 * @see http://www.sudopedia.org/wiki/Hidden_Subset
 */
abstract public class AbstractHiddenSubset implements SolverStrategy 
{    
    /**
     * Gets the size of the subset that is being searched for, e.g. 4 for a
     * Hidden Quad, 3 for a Hidden Triple.
     * 
     * @return the subset size
     */
    abstract protected int getSetSize();
    
    private boolean solveHouses(Collection<? extends House> houses) 
    {
        Set<Set<Integer>> combinations = new HashSet<Set<Integer>>();
        combinations = Cell.generateCombinations(Cell.generateCandidateSet(1, 9), 
                                                 combinations, 
                                                 getSetSize());
        for (Set<Integer> combination : combinations) 
        {
            for (House house : houses) 
            {
                if (house.getUnsolvedCells().size() <= getSetSize()) 
                {
                    continue;
                }
                if (searchHouse(house, combination)) 
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean solve(Grid grid) 
    {
        return solveHouses(grid.getHouses());
    }
    
    protected boolean searchHouse(House house, Set<Integer> subset) 
    {
        boolean changed = false;
        final Set<Cell> matchingCells = new HashSet<Cell>();
        for (Cell cell : house.getCells()) 
        {
            if (cell.isSolved()) 
            {
                continue;
            }
            Set<Integer> cellCandidates 
                    = new HashSet<Integer>(cell.getCandidates());
            cellCandidates.retainAll(subset);
            if (cellCandidates.size() != 0) {
                matchingCells.add(cell);
            }
        }
        if (matchingCells.size() == subset.size()) {
            // Make sure all of the quad if covered
            Set<Integer> subsetCopy = new HashSet<Integer>(subset);
            for (Cell cell : matchingCells) {
                subsetCopy.removeAll(cell.getCandidates());
            }
            // Some left, so this is false
            if (subsetCopy.size() != 0) {
                return false;
            }
            for (Cell cell : matchingCells) {
                if (cell.retainAll(subset)) {
                    changed = true;
                }
            }
        }
        return changed;
    }
}
