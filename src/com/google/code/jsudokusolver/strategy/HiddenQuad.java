/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.jsudokusolver.strategy;

/**
 *
 * @author David Grant
 */
public class HiddenQuad extends AbstractHiddenSubset {
    private static final String NAME = "Hidden Quad";

    public String getName() {
        return NAME;
    }
    
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() {
        return 4;
    }
}