package br.com.tw.lorena.view;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * @author Lorena
 * Classe de interação com usuário.
 * */
public class View extends Menu {
	
	private static final String PATH = new File("src/graphOfTown.txt").getAbsolutePath();
	private static final int SAIR = 5;
	  
	public View() throws IOException {
		new Graph (new FileInputStream(PATH));
		showGraph();
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
	
	public void executeProject() throws IOException {
		int menuOption = 0;
    	while(menuOption!=SAIR){
	        showMenuOption();
	        menuOption = getTheMenuOption();
	        if(menuOption==SAIR)
	        	break;
	        executeMenuOption(menuOption);

        }
	}
	
	
}
