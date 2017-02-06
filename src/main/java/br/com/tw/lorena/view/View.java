package br.com.tw.lorena.view;

import br.com.tw.lorena.controller.Validator;
import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Lorena
 * Classe de interação com usuário.
 * */
public class View {
	
	private static final String PATH = new File("src/graphOfTown.txt").getAbsolutePath();
	private static final int EXIT_OF_PROGRAM = 5;
	private Menu menu;

	public View(Menu menu) throws IOException {
		this.menu = menu;
		new Graph (new FileInputStream(PATH));
		showGraph();
	}

	public void executeProject() throws IOException {
		List<Town> towns;
		int menuOption = 0;
		String condition;

		while(menuOption!= EXIT_OF_PROGRAM){
			menu.showMenuOption();
			menuOption = getMenuOptionFromUser();
			towns = getTwoOrMoreThanTwoTownsFromUser();
			condition = recoverSignalOnCondition(menuOption);
			menu.executeMenuOption(menuOption, condition, towns);
		}
	}

	private int getMenuOptionFromUser() throws IOException {
		Printer.printAnswerTown();
		BufferedReader in = new BufferedReader (
                new InputStreamReader(System.in));
		return menu.getMenuOption(in.readLine());
	}

	private List<Town> getTwoOrMoreThanTwoTownsFromUser() throws IOException {
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

		return createTownsByUser(townsInput);
	}

	private String recoverSignalOnCondition(int menuOption) throws IOException {
		if(menuOption == 3 || menuOption == 4) {
			Printer.printDistanceStopModelExemplo(getTypeCondition(menuOption));
			String condition = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			do {
				try {
					condition = in.readLine();
					condition = condition.replace(" ", "");
					int number = Integer.parseInt(condition.substring(5));
					number = number + 1;
					condition = condition.substring(0, 5) + number;
				} catch (IOException e) {
					Printer.printErrCondition();
				} catch (NumberFormatException e) {
					Printer.printErrEnterWithNumber();
				}
			} while (!Validator.isCodition(condition));

			return condition;
		}

		return null;
	}

	private void showGraph() throws IOException {
		Printer.printTitleRepresentationRoute();
		for(Town town: Graph.townsGraph){
			showRouteByTown(town);
		}
	}

	private void showRouteByTown(Town town) throws IOException {
		Printer.printFromRoute(town.toString());
	    for(Edje edje : town.getAdjacencies())
	    	Printer.printToRoute(edje.toString());
	}

	private List<Town> createTownsByUser(String townsByUser){
		List<Town> towns = new ArrayList<Town>();
		for (String townByUser : townsByUser.split("-")) {
			towns.add(createTown(townByUser));
		}
		return towns;
	}
	private Town createTown(String townByUser){
		for(Town town : Graph.townsGraph){
			if(townByUser.equals(town.getName()))
				return town;
		}
		return null;
	}

	private String getTypeCondition(int menuOption) {
		if(menuOption == 3)
			return "stop";
		return "dist";
	}
}
