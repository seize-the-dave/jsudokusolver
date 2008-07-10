package com.google.code.jsudokusolver.strategy;

/**
*
* @author David Grant
*/
public class Jellyfish extends AbstractBasicFish {
    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "Jellyfish";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getFishSize() {
        return 4;
    }
}