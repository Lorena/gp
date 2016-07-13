package br.com.tw.lorena.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

/*
 * @author Lorena
 * Classe que implementa os algoritmos de busca sobre o Grafo:
 * Dijkistra, Depht Limited Search e busca para cálculo da distancia entre cidade(s).
 * */
public class GraphSearchAlgorithms {
	
	private Stack<Town> townsStack = new Stack<Town>();
	private  ArrayList<Town> visitedTowns = new ArrayList<Town>();
	private int numberOfTrips = 0;
	

	protected void calculateLengthShortestRouteBetweenTwoTowns(Town startTown, Town goalTown){
		 
        Town townStart = startTown; 
        Town townGoal = goalTown;
        computePathsByDijkstra(townStart);
        List<Town> path = getShortestPathTo(townGoal);
        
        if(path.get(0).equals(townGoal) && townGoal.minDistance != 0)
        	Printer.printNoSuchRouteTwoTowns(townStart, townGoal);
        else
        	Printer.printShortestRouteBetweenTwoTowns(townStart, townGoal, path);
	    
	}
	
	protected void calculateDistanceOfTowns(List<Town> town){
		if(Validator.checkRouteValidation(town, getValueOfRoute(town)))
			Printer.printDistanceOfTowns(getFinalDistanceOfTowns(town));
		else
			Printer.printNoRouch();
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
	
	private void computePathsByDijkstra(Town startTown)
    {
        startTown.minDistance = 0.;
        PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
        townQueue.add(startTown);

        while (!townQueue.isEmpty()) {
        	Town u = townQueue.poll();
        	for (Edje e : u.adjacencies)
            {
            	Town v = e.goalTown;
                double distance = e.distance;
                double distanceThroughU = u.minDistance + distance;
        if (distanceThroughU < v.minDistance) {
            townQueue.remove(v);
            v.minDistance = distanceThroughU ;
            v.previous = u;
            townQueue.add(v);
        }
            }
        }
    }

    private List<Town> getShortestPathTo(Town goalTown)
    {
        List<Town> path = new ArrayList<Town>();
        for (Town vertex = goalTown; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
    
    protected void showTheNumberOfTripsBetweenTownsWithCondition(List<Town> town, String condition) 
	{
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
	    	
	    	for(Town w : retrieveTownAdj(startTown.adjacencies)){
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
	
}
