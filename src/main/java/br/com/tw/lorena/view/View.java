package br.com.tw.lorena.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;

/*
 * @author Lorena
 * Classe de interação com usuário.
 * */
public class View extends Menu {
	
	private static View instancia;  
	private static final String PATH = new File("src/graphOfTown.txt").getAbsolutePath();
	private static final int SAIR = 5;
	  
	public static synchronized View getInstancia() throws FileNotFoundException, IOException {  
	      if (instancia == null)  
	         instancia = new View();  
	      return instancia;  
	}  
	
	public View() throws FileNotFoundException, IOException {
		new Graph (new FileInputStream(PATH));
		showGraph();
	}

	private void showGraph(){
		Printer.printTitleRepresentationRoute();
		for(Town t: Graph.townsGraph){
			showRouteByTown(t);	
		}
	}
	
	private void showRouteByTown(Town town){
		Printer.printFromRoute(town.toString());
	    for(Edje r : town.adjacencies)
	    	Printer.printToRoute(r.toString());
	}
	
	public void executeProject(){
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
