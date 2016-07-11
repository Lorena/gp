package br.com.tw.lorena.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.tw.lorena.controller.GraphSearchAlgorithms;
import br.com.tw.lorena.controller.Validator;
import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;

/*
 * @author Lorena
 * Classe de Menu.
 * */
public class Menu extends GraphSearchAlgorithms {


	protected void showMenuOption(){
		Printer.printMenu();
		
	}
	
	protected int getTheMenuOption(){
    	BufferedReader in = new BufferedReader (
                new InputStreamReader (System.in));
    	int op = 0;
    	do {
	    	try {
	    		Printer.printAnswerTown();
				op = Integer.parseInt (in.readLine());
			} catch (NumberFormatException e) {
				Printer.printErrEnterWithNumber();
			} catch (IOException e) {
				Printer.printErrEnterWithNumber();
			}
    	} while(op == 0);
    	
    	return op;
    }
	
	protected void executeMenuOption(int viewNumber){
		List<Town> town = new ArrayList<Town>();
        town = getTwoOrMoreThanTwoTowns();
		if (viewNumber == 1) //DONE!
			menuOne(town);
        else if (viewNumber == 2) //DONE!
        	menuTwo(town);
        else if (viewNumber == 3) //DONE!
        	menuThree(town);
        else if (viewNumber == 4) //DONE!
        	menuFour(town);
    }
	
	private String getCondition(String distanceStop){
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
	
	private List<Town> getTwoOrMoreThanTwoTowns(){
		String townsInput = null;
        BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
        
        do{
        	Printer.printTownModelExemplo();
	        try {
	        	townsInput = in.readLine();
			} catch (IOException e) {
		        Printer.printErrTown();
			}
        }while(!Validator.isRoute(townsInput));
        
        return getTownByUser(townsInput);
	}
	
	private List<Town> getTownByUser(String str){
		List<Town> towns = new ArrayList<Town>(); 
		for (String s : str.split("-")) {
			Town town = getTown(s);
        	towns.add(town);
		}
		return towns;
	}

	private Town getTown(String node){
		for(Town t : Graph.townsGraph){
			if(node.equals(t.name))
				return t;
		}
		return null;		
	}

	private void menuOne(List<Town> town){
    	if(town.size()>=2)
    		calculateDistanceOfTowns(town);
    	else
    		Printer.printFormatAtLeastTwoTown();
	}

	private void menuTwo(List<Town> town){
    	if(town.size()==2)
    		calculateLengthShortestRouteBetweenTwoTowns(town.get(0), town.get(1));
    	else
    		Printer.printFormatTwoTown();
	}
	
	private void menuThree(List<Town> town){
    	if(town.size()==2){
        	String condition = getCondition("stop");
        	showTheNumberOfTripsBetweenTownsWithCondition(town, condition);
    	}else
    		Printer.printFormatTwoTown();
	}
	
	private void menuFour(List<Town> town){
    	if(town.size()==2){
        	String condition = getCondition("dist");
        	showTheNumberOfTripsBetweenTownsWithCondition(town, condition);
    	}else
    		Printer.printFormatTwoTown();
	}


}
