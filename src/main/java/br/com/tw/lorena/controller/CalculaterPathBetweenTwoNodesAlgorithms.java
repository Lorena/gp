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
	
	private void calculateDistanceOfTowns(List<Town> town) throws IOException {
		if(Validator.checkRouteValidation(town, getValueOfRoute(town)))
			Printer.printDistanceOfTowns(getFinalDistanceOfTowns(town));
		else
			Printer.printNoRouch();
	}

	private List<Double> getValueOfRoute(List<Town> route){
		List<Double> routeValue = new ArrayList<Double>();
    	Town previous = route.get(0);
		for (Town next : route){
			for(Edje r : previous.getAdjacencies()){
				if(r.goalTown.equals(next)){
					routeValue.add(new Double(r.distance));
					break;
					}
				}
			previous = next;
		}
		return routeValue;
	}

	private double getFinalDistanceOfTowns(List<Town> town){
		double value = 0.0;
		List<Double> valueOfRoute = getValueOfRoute(town);
		if(Validator.checkRouteValidation(town, valueOfRoute)){
			for(Double v : valueOfRoute)
				value += v.doubleValue();
		}
		return value;
	}
}
