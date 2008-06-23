package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;
import java.util.List;

public class NakedSingle implements SolvingStrategy {
    private Grid grid;
    
    public boolean solve()
    {
        boolean changed = false;
        changed |= solveHouses(grid.getRows());
        changed |= solveHouses(grid.getColumns());
        changed |= solveHouses(grid.getBoxes());
        return changed;
    }
    
    private boolean solveHouses(List<House> houses)
    {
        boolean changed = false;
        for (House house : houses) {
            for (Cell cell : house.getCells())
            {
                if (cell.isSolved()) {
                    continue;
                }
                if (cell.getCandidates().size() == 1)
                {
                    cell.setDigit((Integer) cell.getCandidates().toArray()[0]);
                    changed = true;
                }
            }
        }
        return changed;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
