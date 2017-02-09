package br.com.tw.lorena.model;

import br.com.tw.lorena.view.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
	public static List<Town> townsGraph = new ArrayList<Town>();
	private Map<String, Town> nodesFromInput = new TreeMap<String, Town>();
	private static final String PATH = new File("src/graphOfTown.txt").getAbsolutePath();

	public Graph() {
		super();
	}

	public void showGraph() throws IOException {
		readFromFile(new FileInputStream(PATH));

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

	private void readGraph(String str) {
		for (String s : str.split("[\\s,]")) {
			if (s!=null) {
				if (s.length() < 3) {
					throw new IllegalArgumentException("The node is not correct: " + s);
				}
				String goalTownName = s.substring(1,2);
				String startTownName = str.substring(0,1);

				int distance;

				try {
					distance = Integer.parseInt(s.substring(2));
				} catch(NumberFormatException e) {
					throw new IllegalArgumentException("The node is not correct: " + s +
							": " + s.substring(2) + " is not a valid number.");
				}

				Town startTown = getTown(startTownName);
				Town goalTown = getTown(goalTownName);

				startTown.getAdjacencies().add(new Edje(goalTown, distance));

			}
		}
	}

	  private Town getTown(String name) {
	    	Town town = this.nodesFromInput.get(name);
	    	if (town == null) {
	    		town = new Town (name);
	    		nodesFromInput.put(name, town);
	    	}
	    	return town;
	    }

	private void readFromFile(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str;
		while((str = reader.readLine()) != null) {
			readGraph(str);
		}
		townsGraph = new ArrayList<Town>(nodesFromInput.values());
	}
}
