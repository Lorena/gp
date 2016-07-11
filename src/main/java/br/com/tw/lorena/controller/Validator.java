package br.com.tw.lorena.controller;


import java.util.List;
import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

/*
 * Class Singleton Validator.
 * Esta classe está garantida para instanciar apenas uma vez.
 * */

public class Validator {
	
		private static Validator instancia;  
		  
		private Validator() {  
		  
		}  
		  
		public static synchronized Validator getInstancia() {  
		      if (instancia == null)  
		         instancia = new Validator();  
		      return instancia;  
		}  
		
	
		public static boolean isTown(String town){
			for(Town t : Graph.townsGraph){
				if(town.equals(t.name))
					return true;
			}
			return false;
		}
		
		private static boolean hasNumberCondition (String number){
			
			for (int i = 0; i < number.length(); i++) {
			        if (!Character.isDigit(number.charAt(i)))
			        {
			           Printer.printEnterWithNumber();
			            return false;
			        }
			      }
			return true;
		}
	
		public static boolean isCodition(String condition, String distanceStop){
		
			String clause = condition.substring(0, 5);
			String number = condition.substring(5);
			
				if(clause.contains(distanceStop+"<") && hasNumberCondition(number))
					return true;
				else if (clause.contains(distanceStop+"=") && hasNumberCondition(number))
					return true;
				else if (clause.contains(distanceStop+">") && hasNumberCondition(number))
					return true;
				else 
				return false;
				
		}
		
		public static boolean isRoute(String route){
	
			for (String s : route.split("-")) {
					if(isTown(s))
						return true;
			}
			return false;
				
		}
		
		public static boolean checkRouteValidation(List<Town> route, List<Double> valuesOfRoute){  	
			if(route.size() == (valuesOfRoute.size()+1))
				return true;
			return false;
	
		}
		
		

}
