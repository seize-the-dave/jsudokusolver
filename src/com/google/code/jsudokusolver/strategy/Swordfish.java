package com.google.code.jsudokusolver.strategy;

/**
* @author David Grant
*/
public class Swordfish extends AbstractBasicFish {
    public String getName() {
        return "Swordfish";
    }

    @Override
    protected int getFishSize() {
        return 3;
    }
}
