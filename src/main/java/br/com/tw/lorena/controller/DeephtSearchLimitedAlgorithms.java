package br.com.tw.lorena.controller;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DeephtSearchLimitedAlgorithms {

	private Stack<Town> townsStack = new Stack<Town>();
	private  ArrayList<Town> visitedTowns = new ArrayList<Town>();
	private int numberOfTrips = 0;

	private String conditionChoice;

	private List<Town> towns;

	public void setConditionChoice(String conditionChoice) {
		this.conditionChoice = conditionChoice;
	}

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}

	public void execute() throws IOException {
		String condition = getCondition(conditionChoice);
    	showTheNumberOfTripsBetweenTownsWithCondition(towns, condition);
	}

	private void showTheNumberOfTripsBetweenTownsWithCondition(List<Town> town, String condition) throws IOException {
		computerAllPathsDepthLimitedSearchWithCondition(town.get(0), town.get(1), condition);
		numberOfTrips--;
		Printer.printNumberOfTrips(numberOfTrips);
		numberOfTrips = 0;
		visitedTowns.clear();
		townsStack.clear();
	}

	private void computerAllPathsDepthLimitedSearchWithCondition(Town startTown, Town goalTown, String condition){
		townsStack.add(startTown);
		visitedTowns.add(startTown);

		if(limitedOfSearch(townsStack,condition)) {
			if (startTown.equals(goalTown) && condition.substring(4, 5).equals("<")) {
				Printer.printStack(townsStack);
				numberOfTrips++;
			} else if (startTown.equals(goalTown) && condition.substring(4, 5).equals("=")) {
				if(condition.substring(0,4).contains("stop") && townsStack.size() == Integer.parseInt(condition.substring(5))){
					Printer.printStack(townsStack);
					numberOfTrips++;
				} if(condition.substring(0,4).contains("dist") && retrieveDistanceOfRoute(townsStack)==Integer.parseInt(condition.substring(5))){
					Printer.printStack(townsStack);
					numberOfTrips++;
				}
			}

			for(Town w : retrieveTownAdj(startTown.getAdjacencies())){
				computerAllPathsDepthLimitedSearchWithCondition(w,goalTown, condition);
			}
		}
		townsStack.pop();
		visitedTowns.remove(startTown);
	}

	private boolean limitedOfSearch(Stack<Town> stackRoute, String condition){
		String number = condition.substring(5);
		String sinal = condition.substring(4, 5);

		if(condition.substring(0,4).contains("stop")){
			if(sinal.equals("=") && stackRoute.size() <= Integer.parseInt(number))
				return true;
			else if(sinal.equals("<") && stackRoute.size() < Integer.parseInt(number))
				return true;
			else
				return false;
		}else{ //dist
			if(sinal.equals("=") && retrieveDistanceOfRoute(stackRoute) <= Integer.parseInt(number))
				return true;
			else if(sinal.equals("<") && retrieveDistanceOfRoute(stackRoute) < Integer.parseInt(number))
				return true;
			else
				return false;
		}
	}

	private int retrieveDistanceOfRoute(Stack<Town> route){
		List<Town> towns = new ArrayList<Town>(route);
		return (int) getFinalDistanceOfTowns(towns);
	}

	private static List<Town> retrieveTownAdj(List<Edje> adj){
		List<Town> listTown = new ArrayList<Town>();
		for(Edje r : adj){
			listTown.add(r.goalTown);
		}
		return listTown;
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

	private String getCondition(String distanceStop) throws IOException {
		String condition = null;
		BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
		do{
			Printer.printDistanceStopModelExemplo(distanceStop);
			try {
				condition = in.readLine();
				condition = condition.replace(" ", "");
				int number = Integer.parseInt(condition.substring(5));
				number=number+1;
				condition = condition.substring(0, 5) + number;
			} catch (IOException e) {
				Printer.printErrCondition();
			} catch (NumberFormatException e) {
				Printer.printErrEnterWithNumber();
			}
		}while(!Validator.isCodition(condition,distanceStop));
		return condition;
	}
}
