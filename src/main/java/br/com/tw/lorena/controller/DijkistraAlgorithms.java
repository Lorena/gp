package br.com.tw.lorena.controller;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkistraAlgorithms {

    private List<Town> towns;

	public void execute() throws IOException {
		calculateLengthShortestRouteBetweenTwoTowns(towns.get(0), towns.get(1));
	}

    private void calculateLengthShortestRouteBetweenTwoTowns(Town startTown, Town goalTown) throws IOException {
        computePathsByDijkstra(startTown);
        List<Town> path = getShortestPathTo(goalTown);

        if(path.get(0).equals(goalTown) && goalTown.getMinDistance() != 0)
            Printer.printNoSuchRouteTwoTowns(startTown, goalTown);
        else
            Printer.printShortestRouteBetweenTwoTowns(startTown, goalTown, path);
    }
	

	private void computePathsByDijkstra(Town startTown) {
        startTown.setMinDistance(0.);
        PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
        townQueue.add(startTown);

        while (!townQueue.isEmpty()) {
        	Town town = townQueue.poll();
        	for (Edje edje : town.getAdjacencies())
            {
            	Town town1 = edje.goalTown;
                double distance = edje.distance;
                double distanceThroughU = town.getMinDistance() + distance;
        if (distanceThroughU < town1.getMinDistance()) {
            townQueue.remove(town1);
            town1.setMinDistance(distanceThroughU);
            town1.setPrevious(town);
            townQueue.add(town1);
        }
            }
        }
    }

    private List<Town> getShortestPathTo(Town goalTown) {
        List<Town> path = new ArrayList<Town>();
        for (Town vertex = goalTown; vertex != null; vertex = vertex.getPrevious())
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }
}
