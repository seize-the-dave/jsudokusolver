package com.google.code.jsudokusolver;

import com.google.code.jsudokusolver.strategy.HiddenSingle;
import com.google.code.jsudokusolver.strategy.NakedPair;
import com.google.code.jsudokusolver.strategy.NakedSingle;

public class Main {
    public static void main(String[] args) throws Exception {
        Grid grid = new Grid(9);
        grid.fill("000400100000705032032000700001080605070000020503010800008000560650803000007001000");
        
        grid.registerStrategy(new HiddenSingle());
        grid.registerStrategy(new NakedSingle());
        grid.registerStrategy(new NakedPair());
        grid.solve();
        System.out.print(grid);
    }
}
