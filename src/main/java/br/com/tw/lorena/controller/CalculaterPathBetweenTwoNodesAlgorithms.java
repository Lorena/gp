package br.com.tw.lorena.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

public class CalculaterPathBetweenTwoNodesAlgorithms extends StrategyGraphSearchAlgorithms {

	public CalculaterPathBetweenTwoNodesAlgorithms(List<Town> towns) {
		super(towns);
	}

	@Override
	public void execute(List<Town> towns) {
		calculateDistanceOfTowns(towns);
	}
	
	protected void calculateDistanceOfTowns(List<Town> town){
		if(Validator.checkRouteValidation(town, getValueOfRoute(town)))
			Printer.printDistanceOfTowns(getFinalDistanceOfTowns(town));
		else
			Printer.printNoRouch();
	}
	
	private List<Double> getValueOfRoute(List<Town> route){
		List<Double> routeValue = new ArrayList<Double>();
    	Town previous = route.get(0);
		for (Town next : route){
			for(Edje r : previous.adjacencies){
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
