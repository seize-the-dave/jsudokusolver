package com.google.code.jsudokusolver.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;

/**
*
* @author David Grant
*/
public class XWing implements SolvingStrategy {
    private final static String NAME = "X-Wing";
    private static final Logger LOGGER = Logger.getLogger(NakedTriple.class.getCanonicalName());
    private Grid grid;
    
    public String getName() {
        return NAME;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean solve() {
        boolean solved = false;
        List<House> rows = grid.getRows();
        for (int i = 1; i <= grid.getSize(); i++) {
            Map<Integer, Set<Integer>> matches = new TreeMap<Integer, Set<Integer>>();
            System.out.println("Searching for... " + i);
            for (House row : rows) {
                Set<Integer> rowMatches = new TreeSet<Integer>();
                for (Cell cell : row.getUnsolvedCells()) {
                    if (cell.contains(i)) {
                        rowMatches.add(cell.getColumn().getOffset());
                    }
                }
                if (rowMatches.isEmpty() == false && rowMatches.size() <= 2) {
                    matches.put(row.getOffset(), rowMatches);
                }
            }
            for (int j = 1; j <= grid.getSize(); j++) {
                if (matches.get(j) == null) {
                    continue;
                }
                for (int k = j + 1; k <= grid.getSize(); k++) {
                    if (matches.get(k) == null) {
                        continue;
                    }
                    if (matches.get(j).equals(matches.get(k))) {
                        // We've got a match, but we need to make sure it isn't in the same
                        // chute
                        System.out.println(j + "," + k);
                        List<Cell> cells = new ArrayList<Cell>(rows.get(j).getCells());
                        for (Integer match : matches.get(j)) {
                            cells.remove(match);
                        }
                        for (Cell cell : cells) {
                            if (cell.remove(i)) {
                                System.out.println("Removed " + i + " from " + cell);
                            }
                        }
                        System.out.println("X-Wing for " + matches);
                    }
                }
            }
//            Set<Set<Cell>> matches = new HashSet<Set<Cell>>();
//            // Find rows with pairs of the candidate in them
//            for (House house : rows) {
//                Set<Cell> rowCandidates = new HashSet<Cell>();
//                for (Cell cell : house.getUnsolvedCells()) {
//                    if (cell.contains(i)) {
//                        rowCandidates.add(cell);
//                    }
//                }
//                // Determine if we have a row with two cells
//                // with the same candidates
//                if (rowCandidates.size() == 2) {
//                    matches.add(rowCandidates);
//                }
//            }
//            if (matches.size() < 2) {
//                // Don't have two matching rows, continue
//                continue;
//            }
//            // These matches need to be split over two columns
//            Map<House, Set<Cell>> colMatches = new HashMap<House, Set<Cell>>();
//            for (Set<Cell> cells : matches) {
//                for (Cell cell : cells) {
//                    if (colMatches.containsKey(cell.getColumn())) {
//                        colMatches.get(cell.getColumn()).add(cell);
//                    } else {
//                        Set<Cell> colMatch = new HashSet<Cell>();
//                        colMatch.add(cell);
//                        colMatches.put(cell.getColumn(), colMatch);
//                    }
//                }
//            }
//            solved |= solveHouseMatches(colMatches);            
        }
        return solved;
    }
    
    private boolean solveHouseMatches(Map<House, Set<Cell>> matches) {
        boolean solved = false;
        for (Map.Entry<House, Set<Cell>> entry : matches.entrySet()) {
            if (entry.getValue().size() != 2) {
            }
        }
        return solved;
    }
}