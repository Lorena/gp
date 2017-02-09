package br.com.tw.lorena.controller;


import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

import java.io.IOException;
import java.util.List;

/*
 * Class Singleton Validator.
 * Esta classe está garantida para instanciar apenas uma vez.
 * */

public class Validator {

	public static boolean isRoutes(String route){
		for (String s : route.split("-")) {
			if(isTown(s))
				return true;
		}
		return false;
	}

    public static boolean isCodition(String condition) throws IOException {
        if(condition == null)
        	return false;

		String clause = condition.substring(0, 5);
        String number = condition.substring(5);

        if(clause.contains("dist") || clause.contains("stop")) {
            if (clause.contains("<") && hasNumberCondition(number))
                return true;
            else if (clause.contains("=") && hasNumberCondition(number))
                return true;
            else if (clause.contains(">") && hasNumberCondition(number))
                return true;
        }
        return false;
    }

    public static boolean checkRouteValidation(List<Town> route, List<Double> valuesOfRoute){
        if(route.size() == (valuesOfRoute.size()+1))
            return true;
        return false;
    }

	private static boolean isTown(String town){
		for(Town t : Graph.townsGraph){
			if(town.equals(t.getName()))
				return true;
		}
		return false;
	}

	private static boolean hasNumberCondition (String number) throws IOException {
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i)))
			{
				Printer.printEnterWithNumber();
				return false;
			}
		}
		return true;
	}
}
