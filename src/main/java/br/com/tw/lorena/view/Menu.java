package br.com.tw.lorena.view;

import br.com.tw.lorena.controller.CalculaterPathBetweenTwoNodesAlgorithms;
import br.com.tw.lorena.controller.DeephtSearchLimitedAlgorithms;
import br.com.tw.lorena.controller.DijkistraAlgorithms;
import br.com.tw.lorena.model.Town;

import java.io.IOException;
import java.util.List;

/*
 * @author Lorena
 * Classe de Menu.
 * */
public class Menu {

	private final CalculaterPathBetweenTwoNodesAlgorithms calculaterPathBetweenTwoNodesAlgorithms;
	private final DijkistraAlgorithms dijkistraAlgorithms;
	private final DeephtSearchLimitedAlgorithms deephtSearchLimitedAlgorithms;
	private Printer printer;

	public Menu(CalculaterPathBetweenTwoNodesAlgorithms calculaterPathBetweenTwoNodesAlgorithms,
				DijkistraAlgorithms dijkistraAlgorithms,
				DeephtSearchLimitedAlgorithms deephtSearchLimitedAlgorithms,
				Printer printer) {
		this.calculaterPathBetweenTwoNodesAlgorithms = calculaterPathBetweenTwoNodesAlgorithms;
		this.dijkistraAlgorithms = dijkistraAlgorithms;
		this.deephtSearchLimitedAlgorithms = deephtSearchLimitedAlgorithms;
		this.printer = printer;
	}

	public void showMenuOption() throws IOException {
		printer.printMenu();
	}

	public int getMenuOption(String option) throws IOException {
		int op = 0;
		try {
			op = Integer.parseInt(option);
		} catch (NumberFormatException e) {
			Printer.printErrEnterWithNumber();
			op = 5;
		}
		return op;
	}

	public void executeMenuOption(int option, List<Town> towns) throws IOException {

		if (option == 1){
			calculaterPathBetweenTwoNodesAlgorithms.setTowns(towns);
			calculaterPathBetweenTwoNodesAlgorithms.execute();

		} else if (option == 2) {
			dijkistraAlgorithms.setTowns(towns);
			dijkistraAlgorithms.execute();
		}
		else if (option == 3){
			deephtSearchLimitedAlgorithms.setTowns(towns);
			deephtSearchLimitedAlgorithms.setConditionChoice("stop");
			deephtSearchLimitedAlgorithms.execute();

		} else if (option == 4) {
			deephtSearchLimitedAlgorithms.setTowns(towns);
			deephtSearchLimitedAlgorithms.setConditionChoice("dist");
			deephtSearchLimitedAlgorithms.execute();

		}
	}
}
