package com.google.code.jsudokusolver;

import java.util.HashSet;
import java.util.Set;

public class Chute 
{
	private Set<House> houses;
	
	public Chute()
	{
		houses = new HashSet<House>();
	}
	
	public void add(House house)
	{
		houses.add(house);
	}
	
	public String toString()
	{
		return houses.toString();
	}
}
