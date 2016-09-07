package br.com.tw.lorena.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.tw.lorena.controller.CalculaterPathBetweenTwoNodesAlgorithms;
import br.com.tw.lorena.controller.DeephtSearchLimitedAlgorithms;
import br.com.tw.lorena.controller.DijkistraAlgorithms;
import br.com.tw.lorena.controller.StrategyGraphSearchAlgorithms;
import br.com.tw.lorena.controller.Validator;
import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;

/*
 * @author Lorena
 * Classe de Menu.
 * */
public class Menu {


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
        
        StrategyGraphSearchAlgorithms strategyAlgorithms = null;
        
		if (viewNumber == 1) //DONE!
		    strategyAlgorithms = new CalculaterPathBetweenTwoNodesAlgorithms(town);
        else if (viewNumber == 2) //DONE!
        	strategyAlgorithms = new DijkistraAlgorithms(town);
        else if (viewNumber == 3) //DONE!
        	strategyAlgorithms = new DeephtSearchLimitedAlgorithms(town, "stop");
        else if (viewNumber == 4) //DONE!
        	strategyAlgorithms = new DeephtSearchLimitedAlgorithms(town, "dist");
        	
		strategyAlgorithms.execute(town);
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

}
