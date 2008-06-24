package com.google.code.jsudokusolver;

public interface SolvingStrategy {
    String getName();
    void setGrid(Grid grid);
    boolean solve();
}
