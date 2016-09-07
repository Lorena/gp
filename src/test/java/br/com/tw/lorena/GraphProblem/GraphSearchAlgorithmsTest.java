package br.com.tw.lorena.GraphProblem;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import br.com.tw.lorena.controller.*;
import br.com.tw.lorena.model.Edje;
import br.com.tw.lorena.model.Town;

public class GraphSearchAlgorithmsTest extends TestCase {

	public Town A, B, C, D, E;

	public GraphSearchAlgorithmsTest() {
		super();
		A = new Town("A");
		B = new Town("B");
		C = new Town("C");
		D = new Town("D");
		E = new Town("E");

		A.adjacencies.add(new Edje(B, 5));
		A.adjacencies.add(new Edje(D, 5));
		A.adjacencies.add(new Edje(E, 7));
		
		B.adjacencies.add(new Edje(C, 4));
		
		C.adjacencies.add(new Edje(D, 8));
		C.adjacencies.add(new Edje(E, 2));
		
		D.adjacencies.add(new Edje(C, 8));
		D.adjacencies.add(new Edje(E, 6));
		
		E.adjacencies.add(new Edje(B, 3));

	}
	
	public void testExecutaCalculateLengthShortestRouteBetweenTwoTowns(){
		
//		GraphSearchAlgorithmsLixo g = new GraphSearchAlgorithmsLixo();
		Town townStart = A;
		Town townGoal = C;
		List<Town> pathWaited = new ArrayList<Town>();
        pathWaited.add(A);
        pathWaited.add(B);
        pathWaited.add(C);
	
       // g.computePathsByDijkstra(townStart);
    	//List<Town> pathReturned = g.getShortestPathTo(townGoal);
		
    	//assertEquals(pathWaited, pathReturned);
	}
	
	public void testExecutaGetFinalDistanceOfTowns(){
	
//		GraphSearchAlgorithmsLixo g = new GraphSearchAlgorithmsLixo();
	
		List<Town> path = new ArrayList<Town>();
        path.add(A);
        path.add(B);
        
        double valueWaited = 5;
        //double valueReturned = g.getFinalDistanceOfTowns(path);
		
    	//assertEquals(valueWaited, valueReturned);
	}


	
	
}
