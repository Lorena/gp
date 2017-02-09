package br.com.tw.lorena.controller;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DeephtSearchLimitedController implements AlgorithmInterface {

	private Stack<Town> townsStack = new Stack<Town>();
	private  ArrayList<Town> visitedTowns = new ArrayList<Town>();
	private List<Town> towns;
	private String condition;
	private int numberOfTrips = 0;
	private final int OPTION = 3;

	public void execute() throws IOException {
    	showTheNumberOfTripsBetweenTownsWithCondition(towns, condition);
	}

	private void showTheNumberOfTripsBetweenTownsWithCondition(List<Town> town, String signal) throws IOException {
		computerAllPathsDepthLimitedSearchWithCondition(town.get(0), town.get(1), signal);
		numberOfTrips--;
		Printer.printNumberOfTrips(numberOfTrips);
		numberOfTrips = 0;
		visitedTowns.clear();
		townsStack.clear();
	}
	private void computerAllPathsDepthLimitedSearchWithCondition(Town startTown, Town goalTown, String signal){
		townsStack.add(startTown);
		visitedTowns.add(startTown);

		if(limitedOfSearch(townsStack,signal)) {
			if (startTown.equals(goalTown) && signal.substring(4, 5).equals("<")) {
				Printer.printStack(townsStack);
				numberOfTrips++;
			} else if (startTown.equals(goalTown) && signal.substring(4, 5).equals("=")) {
				if(signal.substring(0,4).contains("stop") && townsStack.size() == Integer.parseInt(signal.substring(5))){
					Printer.printStack(townsStack);
					numberOfTrips++;
				} if(signal.substring(0,4).contains("dist") && retrieveDistanceOfRoute(townsStack)==Integer.parseInt(signal.substring(5))){
					Printer.printStack(townsStack);
					numberOfTrips++;
				}
			}

			for(Town town : retrieveTownAdj(startTown.getAdjacencies())){
				computerAllPathsDepthLimitedSearchWithCondition(town,goalTown, signal);
			}
		}
		townsStack.pop();
		visitedTowns.remove(startTown);
	}

	private boolean limitedOfSearch(Stack<Town> stackRoute, String signal){
		String number = signal.substring(5);
		String sig = signal.substring(4, 5);

		if(signal.substring(0,4).contains("stop")){
			if(sig.equals("=") && stackRoute.size() <= Integer.parseInt(number))
				return true;
			else if(sig.equals("<") && stackRoute.size() < Integer.parseInt(number))
				return true;
			else
				return false;
		}else{ //dist
			if(sig.equals("=") && retrieveDistanceOfRoute(stackRoute) <= Integer.parseInt(number))
				return true;
			else if(sig.equals("<") && retrieveDistanceOfRoute(stackRoute) < Integer.parseInt(number))
				return true;
			else
				return false;
		}
	}

	private int retrieveDistanceOfRoute(Stack<Town> routes){
		List<Town> towns = new ArrayList<Town>(routes);
		return (int) getFinalDistanceOfTowns(towns);
	}

	private static List<Town> retrieveTownAdj(List<Edje> edjesAdj){
		List<Town> listTown = new ArrayList<Town>();
		for(Edje edje : edjesAdj){
			listTown.add(edje.goalTown);
		}
		return listTown;
	}

	private double getFinalDistanceOfTowns(List<Town> towns){
		double finalDistance = 0.0;
		List<Double> valueOfRoutes = getValueOfRoute(towns);
		if(Validator.checkRouteValidation(towns, valueOfRoutes)){
			for(Double valueOfRoute : valueOfRoutes)
				finalDistance += valueOfRoute.doubleValue();
		}
		return finalDistance;
	}

	private List<Double> getValueOfRoute(List<Town> route){
		List<Double> routeValue = new ArrayList<Double>();
		Town previous = route.get(0);
		for (Town town : route){
			for(Edje edje : previous.getAdjacencies()){
				if(edje.goalTown.equals(town)){
					routeValue.add(new Double(edje.distance));
					break;
				}
			}
			previous = town;
		}
		return routeValue;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}

	public int getOption() {
		return OPTION;
	}
}
