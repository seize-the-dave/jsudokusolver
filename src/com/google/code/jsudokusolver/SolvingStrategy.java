package com.google.code.jsudokusolver;

public interface SolvingStrategy {
    void setGrid(Grid grid);
    boolean solve();
}
