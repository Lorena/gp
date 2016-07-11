package br.com.tw.lorena.view;

import java.util.List;
import java.util.Stack;

import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.resource.ResourseProperty;

/*
 * Classe que contém todas os tipos de outputs*/


public class Printer {
	
	public static void printTitleRepresentationRoute(){
		System.out.println(ResourseProperty.getProperty("graph_representation"));	    
	}

	public static void printFromRoute(String str){
		System.out.println(ResourseProperty.getProperty("from")+ " " + str);
	    
	}
	
	public static void printToRoute(String str){
		System.out.println(ResourseProperty.getProperty("to") + " " + str);
	    
	}
	
	public static void printMenu(){
		System.out.println(ResourseProperty.getProperty("choose_number"));
		System.out.println(ResourseProperty.getProperty("menu_one"));
		System.out.println(ResourseProperty.getProperty("menu_two"));
		System.out.println(ResourseProperty.getProperty("menu_three"));
		System.out.println(ResourseProperty.getProperty("menu_four"));
		System.out.println(ResourseProperty.getProperty("menu_five"));	    
	}
	
	public static void printEnterWithNumber(){
		 System.out.println(ResourseProperty.getProperty("must_enter_with_number"));
	    
	}
	
	public static void printNoSuchRouteTwoTowns(Town townStart, Town townGoal){
    	System.out.println(ResourseProperty.getProperty("no_such_route_there_is_not")+ " "  + townStart + " and " + townGoal);
	}
	
	public static void printShortestRouteBetweenTwoTowns(Town townStart, Town townGoal, List<Town> path){
		System.out.println(ResourseProperty.getProperty("distance_from")+ " " + townStart + " to "+ townGoal + " is " + townGoal.minDistance);
        System.out.println(ResourseProperty.getProperty("shortest_route")+ " " + townStart + " to " + townGoal + " is " + path);
    }
	
	public static void printDistanceOfTowns(double d){
		System.out.println(ResourseProperty.getProperty("distance")+ " " + d);
	}
	
	public static void printNoRouch(){
		System.out.println(ResourseProperty.getProperty("no_route"));
	}
	
	public static void printNumberOfTrips(int number){
		System.out.println(ResourseProperty.getProperty("number_trips")+ " " + number);
	}
	
	public static void printStack(Stack<Town> townsStack){
		System.out.println(townsStack);
	}
	
	public static void printDistanceStopModelExemplo(String str){
    	System.out.println(ResourseProperty.getProperty("please_model_examplo") + " \n "+ str + "< 3 or "+ str +" = 3. ");
    }
	
	public static void printTownModelExemplo(){
		System.out.println(ResourseProperty.getProperty("please_model_town_examplo"));
		printAnswerTown();   
    }

	public static void printAnswerTown(){
		System.out.print("> ");    
    }
	
	public static void printFormatAtLeastTwoTown(){
		System.out.println(ResourseProperty.getProperty("enter_with_at_leat_two_towns"));
    }
	
	public static void printFormatTwoTown(){
		System.out.println(ResourseProperty.getProperty("enter_with_two_towns"));
    }
	
	public static void printErrEnterWithNumber(){
		System.err.println(ResourseProperty.getProperty("must_enter_with_number"));
	}
	
	public static void printErrTown(){
		 System.err.println("IO error trying to read the Town!");
	}
	
	public static void printErrCondition(){
		System.err.println("IO error trying to read the condition!");
	}

	public static void printNotFindFile(){
		System.err.println("Could not find graphProblem.properties file");
	}
	
	public static void printMissingData(String name){
		System.err.println("WARNING: missing data: " + name);
	}

}
	
