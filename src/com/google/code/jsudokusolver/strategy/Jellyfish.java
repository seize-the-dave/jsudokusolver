package com.google.code.jsudokusolver.strategy;

/**
*
* @author David Grant
*/
public class Jellyfish extends AbstractBasicFish {
    public String getName() {
        return "Jellyfish";
    }

    @Override
    protected int getFishSize() {
        return 4;
    }
}