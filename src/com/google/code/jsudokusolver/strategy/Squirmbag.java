package com.google.code.jsudokusolver.strategy;

/**
*
* @author David Grant
*/
public class Squirmbag extends AbstractBasicFish {
    public String getName() {
        return "Squirmbag";
    }

    @Override
    protected int getFishSize() {
        return 5;
    }
}