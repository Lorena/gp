package br.com.tw.lorena.model;

public class Edje
{
    public final Town goalTown;
    public final double distance;
    public Edje(Town argTarget, double argDistance)
    { goalTown = argTarget; distance = argDistance; }
    
	@Override
	public String toString() {
		return "town " + goalTown + " is " + distance ;
	}

	public Town getGoalTown() {
		return goalTown;
	}

	public double getDistance() {
		return distance;
	}

	
}