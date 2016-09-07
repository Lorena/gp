package br.com.tw.lorena.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.tw.lorena.model.Town;
import br.com.tw.lorena.view.Printer;

public abstract class StrategyGraphSearchAlgorithms {
	
	List<Town> towns = new ArrayList<Town>();
	
	public StrategyGraphSearchAlgorithms (List<Town> towns){
		
		if(!(towns.size()>=2))
			Printer.printFormatAtLeastTwoTown();
		
		this.towns = towns;
	}

	public abstract void execute(List<Town> towns);

}
