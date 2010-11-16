/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.jsudokusolver.strategy;

/**
 * @see http://www.sudopedia.org/wiki/Hidden_Triple
 */
public class HiddenTriple extends AbstractHiddenSubset 
{
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() 
    {
        return 3;
    }
}
