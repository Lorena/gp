package br.com.tw.lorena.controller;

import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.view.BuilderInput;
import br.com.tw.lorena.view.Menu;
import br.com.tw.lorena.view.Printer;
import br.com.tw.lorena.view.View;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		View view = new View(new Menu(new CalculaterPathBetweenTwoNodesController(),
									  new DijkistraController(),
						 			  new DeephtSearchLimitedController(),
								 	  new Printer()), new BuilderInput(), new Graph());
    	view.executeProject();
    }
}
