package br.com.tw.lorena.controller;

import br.com.tw.lorena.view.Menu;
import br.com.tw.lorena.view.Printer;
import br.com.tw.lorena.view.View;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		View view = new View(new Menu(new CalculaterPathBetweenTwoNodesAlgorithms(),
									  new DijkistraAlgorithms(),
						 			  new DeephtSearchLimitedAlgorithms(),
								 	  new Printer()));
    	view.executeProject();
    }
}
