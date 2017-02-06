package br.com.tw.lorena.controller;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculaterPathBetweenTwoNodesAlgorithms {

	private List<Town> towns;

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}

	public void execute() throws IOException {
		calculateDistanceOfTowns(towns);
	}
	
	private void calculateDistanceOfTowns(List<Town> towns) throws IOException {
		if(Validator.checkRouteValidation(towns, getValueOfRoutes(towns)))
			Printer.printDistanceOfTowns(getFinalDistanceOfTowns(towns));
		else
			Printer.printNoRouch();
	}

	private List<Double> getValueOfRoutes(List<Town> routes){
		List<Double> routeValues = new ArrayList<Double>();
    	Town previousTown = routes.get(0);
		for (Town nextTown : routes){
			for(Edje edje : previousTown.getAdjacencies()){
				if(edje.goalTown.equals(nextTown)){
					routeValues.add(new Double(edje.distance));
					break;
					}
				}
			previousTown = nextTown;
		}
		return routeValues;
	}

	private double getFinalDistanceOfTowns(List<Town> towns){
		double finalDistance = 0.0;
		List<Double> valueOfRoutes = getValueOfRoutes(towns);
		if(Validator.checkRouteValidation(towns, valueOfRoutes)){
			for(Double valueOfRoute : valueOfRoutes)
				finalDistance += valueOfRoute.doubleValue();
		}
		return finalDistance;
	}
}
