package com.google.code.jsudokusolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;


public class Grid {
    private static final Logger LOGGER = Logger.getLogger(Grid.class.getCanonicalName());
    private final int size;
    private final List<House> rows;
    private final List<House> columns;
    private final List<House> boxes;
    private final List<SolvingStrategy> strategies = new ArrayList<SolvingStrategy>();
    private static int step = 1;
    private int currentStrategy = 0;
    
    public Grid(int size) {
        this.size = size;
        
        rows = generateHouses();
        columns = generateHouses();
        boxes = generateHouses();
    }
    
    private List<House> generateHouses() {
        List<House> houses = new ArrayList<House>(size);
        for (int i = 0; i < size; i++) {
            houses.add(new House(size, i + 1));
        }
        return houses;
    }
    
    public Grid() {
        this(9);
    }
    
    /**
     * Fills in the puzzle.
     * 
     * This method expects a String of numbers equal to the square of the
     * puzzle size.  For example, a puzzle size of 9 would take a String length
     * of 81.  Numbers that are not filled in should use a zero, e.g.
     * 
     * 1302465798...
     * 
     * @param puzzle
     * @throws InvalidSudokuException if the puzzle String is invalid
     */
    public void fill(String puzzle) throws InvalidSudokuException {
        if (puzzle.length() != (size * size)) {
            throw new InvalidSudokuException("Wrong Length");
        }
        Set<Cell> cells = new HashSet<Cell>();
        for (int i = 0; i < puzzle.length(); i++) {
            House row = getRow(i);
            House column = getColumn(i);
            House box = getBox(i);
            
            Cell cell = new Cell(row, column, box, Cell.generateCandidateSet(1, 9));
            cells.add(cell);
            Integer digit = Integer.valueOf(String.valueOf(puzzle.charAt(i)));
            if (digit != 0) {
                cell.setDigit(digit);
            }
        }
        // Flush Cells
        for (Cell cell : cells) {
            if (cell.isSolved()) {
                cell.setDigit(cell.getDigit());
            }
        }
    }
    
    private House getRow(int offset) {
        int row = offset / size;
        return rows.get(row);
    }
    
    private House getColumn(int offset) {
        int column = offset % size;
        return columns.get(column);
    }
    
    private House getBox(int offset) {
        int row = offset / size;
        int column = offset % size;
        int sqrt = (int) Math.sqrt((double) size);
        
        int rowOffset = (row / sqrt);
        int columnOffset = (column / sqrt);
        int box = (rowOffset * sqrt) + columnOffset;
        return boxes.get(box);
    }
    
    public List<House> getRows() {
        return rows;
    }
    
    public List<House> getColumns() {
        return columns;
    }
    
    public List<House> getBoxes() {
        return boxes;
    }
    
    public int getSize() {
        return size;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (House row : rows) {
            sb.append(row.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public String getPuzzle() {
        StringBuilder sb = new StringBuilder();
        for (House row : rows) {
            for (Cell cell : row.getCells()) {
                if (cell.isSolved()) {
                    sb.append(cell.getDigit());
                } else {
                    sb.append("0");
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * Registers a SolvingStrategy to use for solving this Sudoku.
     * 
     * @param strategy the SolvingStrategy to register
     */
    public void registerStrategy(SolvingStrategy strategy) {
        strategy.setGrid(this);
        strategies.add(strategy);
    }
    
    /**
     * Attempts to solve this puzzle by invoking SolvingStrategy.solve() on each
     * registered SolvingStrategy.  This method continues to invoke each 
     * strategy until all strategies fail to change the puzzle.
     * 
     * @return true if the puzzle was changed; false otherwise
     */
    public boolean solve() {
        boolean changed = false;
        while (step()) {
            changed = true;
        }
        return changed;
    }
    
    public boolean step() {
        boolean changed = false;
        for (int i = 0; i < strategies.size(); i++) {
            currentStrategy = i;
            while (stepOnce()) {
                changed = true;
            }
	    // If we find a solution after the first strategy, restart
	    // so we don't move onto complex strategies too early.
	    if (changed && currentStrategy != 0) {
		return true;
	    }
        }
        return changed;
    }
    
    public boolean stepOnce() {
        SolvingStrategy strategy = strategies.get(currentStrategy);
        if (strategy.solve()) {
            step++;
            return true;
        }
        return false;
    }
    
    public Integer[][] toArray()
    {
        Integer[][] puzzle = new Integer[9][9];
        int i = 0;
        for (House row : rows) {
            int j = 0;
            for (Cell cell : row.getCells()) {
                if (cell.isSolved()) {
                    puzzle[i][j] = cell.getDigit();
                } else {
                    puzzle[i][j] = 0;
                }
                j++;
            }
            i++;
        }
        return puzzle;
    }
    
    /**
     * 
     * @param rowIndex zero-based row number
     * @param columnIndex zero-based column number
     * @return
     */
    public Set<Integer> getCandidates(int rowIndex, int columnIndex) {
        return rows.get(columnIndex).getCells().get(rowIndex).getCandidates();
    }
    
    public Cell getCell(int rowIndex, int columnIndex) {
        return rows.get(columnIndex).getCells().get(rowIndex);
    }
    
    public void addCellChangeListener(CellChangeListener listener) {
        for (House house : getBoxes()) {
            house.addCellChangeListener(listener);
        }
        for (House house : getColumns()) {
            house.addCellChangeListener(listener);
        }
        for (House house : getRows()) {
            house.addCellChangeListener(listener);
        }
    }
    
    public void removeCellChangeListener(CellChangeListener listener) {
        for (House house : getBoxes()) {
            house.removeCellChangeListener(listener);
        }
        for (House house : getColumns()) {
            house.removeCellChangeListener(listener);
        }
        for (House house : getRows()) {
            house.removeCellChangeListener(listener);
        }
    }
    
    public static void logCandidateRemoval(Cell cell, int candidate, String strategy, Set<Cell> reference) {
        log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + candidate + " due to " + strategy + " in " + formatCells(reference));
    }
    
    public static void logCandidateRemoval(Cell cell, Set<Integer> candidates, String strategy, Set<Cell> reference) {
        log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + formatCandidates(candidates, false) + " due to " + strategy + " in " + formatCells(reference));
    }
    
    public static void logCandidateRemoval(Cell cell, Set<Integer> candidates, String strategy, Cell... reference) {
        Set<Cell> referenceSet = new TreeSet<Cell>(Arrays.asList(reference));
        log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + formatCandidates(candidates, false) + " due to " + strategy + " in " + formatCells(referenceSet));
    }
    
    public static void logCandidateRetention(Cell cell, int candidate, String strategy) {
        log(step + " " + strategy + ": " + cell.getPosition() + " can only contain " + candidate);
    }
    
    public static void logCandidateRetention(Cell cell, Set<Integer> candidates, String strategy) {
        log(step + " " + strategy + ": " + cell.getPosition() + " can only contain " + formatCandidates(candidates, true));
    }
    
    public static void logCandidateRetention(Set<Cell> cells, Set<Integer> candidates, String strategy) {
        log(step + " " + strategy + ": " + formatCells(cells) + " can only contain " + formatCandidates(candidates, true));
    }
    
    private static void log(String message) {
//        System.out.println(message);
        LOGGER.info(message);
    }
    
    public static String formatCells(Set<Cell> cells) {
        StringBuffer sb = new StringBuffer();
        Cell[] cellArray = cells.toArray(new Cell[]{});
        for (int i = 0; i < cellArray.length; i++) {
            sb.append(cellArray[i].getPosition());
            if (cellArray.length - i == 2) {
                sb.append(" and ");
            }
            if (cellArray.length - i > 2) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    public static String formatCandidates(Set<Integer> cells, boolean and) {
        StringBuffer sb = new StringBuffer();
        Integer[] cellArray = cells.toArray(new Integer[]{});
        for (int i = 0; i < cellArray.length; i++) {
            sb.append(cellArray[i]);
            if (cellArray.length - i == 2) {
                if (and) {
                    sb.append(" and ");
                } else {
                    sb.append(" or ");
                }
            }
            if (cellArray.length - i > 2) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}