package br.com.tw.lorena.view;

import br.com.tw.lorena.controller.CalculaterPathBetweenTwoNodesController;
import br.com.tw.lorena.controller.DeephtSearchLimitedController;
import br.com.tw.lorena.controller.DijkistraController;

import java.io.IOException;

/*
 * @author Lorena
 * Classe de Menu.
 * */
public class Menu {

	private final CalculaterPathBetweenTwoNodesController calculaterPathBetweenTwoNodesController;
	private final DijkistraController dijkistraController;
	private final DeephtSearchLimitedController deephtSearchLimitedController;
	private Printer printer;


	public Menu(CalculaterPathBetweenTwoNodesController calculaterPathBetweenTwoNodesController,
				DijkistraController dijkistraController,
				DeephtSearchLimitedController deephtSearchLimitedController,
				Printer printer) {
		this.calculaterPathBetweenTwoNodesController = calculaterPathBetweenTwoNodesController;
		this.dijkistraController = dijkistraController;
		this.deephtSearchLimitedController = deephtSearchLimitedController;
		this.printer = printer;
	}

	public void outputMenuOption() throws IOException {
		printer.printMenu();
	}

	public void executeMenuOption(Input input) throws IOException {

		//TODO REFACTORING. colocar a option em cada classe de algoritmo de rota.

		if (input.getOption() == calculaterPathBetweenTwoNodesController.getOption()){
			calculaterPathBetweenTwoNodesController.setTowns(input.getRoutes());
			calculaterPathBetweenTwoNodesController.setCondition(null);
			calculaterPathBetweenTwoNodesController.execute();

		} else if (input.getOption() == dijkistraController.getOption()) {
			dijkistraController.setTowns(input.getRoutes());
			dijkistraController.setCondition(null);
			dijkistraController.execute();
		}
		else if (input.getOption() == deephtSearchLimitedController.getOption()){
			deephtSearchLimitedController.setTowns(input.getRoutes());
			deephtSearchLimitedController.setCondition(input.getCondition());
			deephtSearchLimitedController.execute();

		} else if (input.getOption() == 4) {
			deephtSearchLimitedController.setTowns(input.getRoutes());
			deephtSearchLimitedController.setCondition(input.getCondition());
			deephtSearchLimitedController.execute();

		}

	}
}
