package com.google.code.jsudokusolver;

import com.google.code.jsudokusolver.strategy.HiddenPair;
import com.google.code.jsudokusolver.strategy.HiddenSingle;
import com.google.code.jsudokusolver.strategy.NakedPair;
import com.google.code.jsudokusolver.strategy.NakedSingle;

public class Main {
    public static void main(String[] args) throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "801006094" +
                        "300009080" +
                        "970080500" +
                        "547062030" +
                        "632000050" +
                        "198375246" + 
                        "083620915" +
                        "065198000" +
                        "219500008";
        grid.fill(puzzle);
        
        System.out.print(grid);
        grid.registerStrategy(new HiddenSingle());
        grid.registerStrategy(new HiddenPair());
        grid.registerStrategy(new NakedSingle());
        grid.registerStrategy(new NakedPair());
        grid.solve();
        System.out.print(grid);
    }
}
