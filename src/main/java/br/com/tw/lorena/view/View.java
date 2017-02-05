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
		int menuOption = 0;
		while(menuOption!= EXIT_OF_PROGRAM){
			menu.showMenuOption();
			menuOption = getMenuOptionFromUser();

			if(menuOption == EXIT_OF_PROGRAM)
				break;
			menu.executeMenuOption(menuOption, getTwoOrMoreThanTwoTowns());
		}
	}

	private int getMenuOptionFromUser() throws IOException {
		Printer.printAnswerTown();
		BufferedReader in = new BufferedReader (
                new InputStreamReader(System.in));
		return menu.getMenuOption(in.readLine());
	}

	private void showGraph() throws IOException {
		Printer.printTitleRepresentationRoute();
		for(Town t: Graph.townsGraph){
			showRouteByTown(t);
		}
	}

	private void showRouteByTown(Town town) throws IOException {
		Printer.printFromRoute(town.toString());
	    for(Edje r : town.adjacencies)
	    	Printer.printToRoute(r.toString());
	}


	private List<Town> getTwoOrMoreThanTwoTowns() throws IOException {
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
