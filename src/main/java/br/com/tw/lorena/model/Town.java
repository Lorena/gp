package br.com.tw.lorena.model;

import java.util.ArrayList;
import java.util.List;

public class Town implements Comparable<Town>
{
    public final String name;
    public List<Edje> adjacencies = new ArrayList<Edje>();
    public double minDistance = Double.POSITIVE_INFINITY;
    public Town previous;
    
    public Town(String argName) { name = argName; }
    
    
    public int compareTo(Town other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
    
    public String toString() { return name; }


	public List<Edje> getAdjacencies() {
		return adjacencies;
	}


	public void setAdjacencies(List<Edje> adjacencies) {
		this.adjacencies = adjacencies;
	}


	public double getMinDistance() {
		return minDistance;
	}


	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}


	public Town getPrevious() {
		return previous;
	}


	public void setPrevious(Town previous) {
		this.previous = previous;
	}


	public String getName() {
		return name;
	}


    

}