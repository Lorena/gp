package br.com.tw.lorena.view;

import br.com.tw.lorena.model.Graph;

import java.io.IOException;

/*
 * @author Lorena
 * Classe de interação com usuário.
 * */
public class View {

	private static final int EXIT_OF_PROGRAM = 5;
	private Menu menu;
	private BuilderInput builderInput;
	private Graph graph;

	public View(Menu menu, BuilderInput builderInput, Graph graph) throws IOException {
		this.menu = menu;
		this.builderInput = builderInput;
		this.graph = graph;
	}

	public void executeProject() throws IOException {
		graph.showGraph();
		do {
			menu.outputMenuOption();
			builderInput.buildInputReadingFromUser();
			menu.executeMenuOption(builderInput.getInput());
		} while ((builderInput.getInput().getOption() != EXIT_OF_PROGRAM));
	}


}
