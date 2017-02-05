package br.com.tw.lorena.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

public class DijkistraAlgorithms extends StrategyGraphSearchAlgorithms {

	public DijkistraAlgorithms(List<Town> towns) throws IOException {
		super(towns);
	}

	@Override
	public void execute(List<Town> towns) throws IOException {
		calculateLengthShortestRouteBetweenTwoTowns(towns.get(0), towns.get(1));
	}
	
	protected void calculateLengthShortestRouteBetweenTwoTowns(Town startTown, Town goalTown) throws IOException {
		 
        Town townStart = startTown; 
        Town townGoal = goalTown;
        computePathsByDijkstra(townStart);
        List<Town> path = getShortestPathTo(townGoal);
        
        if(path.get(0).equals(townGoal) && townGoal.minDistance != 0)
        	Printer.printNoSuchRouteTwoTowns(townStart, townGoal);
        else
        	Printer.printShortestRouteBetweenTwoTowns(townStart, townGoal, path);
	    
	}
	

	private void computePathsByDijkstra(Town startTown)
    {
        startTown.minDistance = 0.;
        PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
        townQueue.add(startTown);

        while (!townQueue.isEmpty()) {
        	Town u = townQueue.poll();
        	for (Edje e : u.adjacencies)
            {
            	Town v = e.goalTown;
                double distance = e.distance;
                double distanceThroughU = u.minDistance + distance;
        if (distanceThroughU < v.minDistance) {
            townQueue.remove(v);
            v.minDistance = distanceThroughU ;
            v.previous = u;
            townQueue.add(v);
        }
            }
        }
    }


    private List<Town> getShortestPathTo(Town goalTown)
    {
        List<Town> path = new ArrayList<Town>();
        for (Town vertex = goalTown; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

	
	
}
