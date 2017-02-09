package br.com.tw.lorena.view;

import br.com.tw.lorena.controller.Validator;
import br.com.tw.lorena.model.Graph;
import br.com.tw.lorena.model.Town;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BuilderInput {

    private static final String REGEX_MENU_OPTION = "[1-5]";
    private Input input;

    public void buildInputReadingFromUser() throws IOException {
        Printer.printAnswerTown();
        input = new Input();
        input.setOption(readInputOption());
        input.setRoutes(readInputRoutes());
        input.setCondition(readInputCondition());
    }

    private int readInputOption() throws IOException {
        String option = readFromUser();
        while(!option.matches(REGEX_MENU_OPTION)) {
            Printer.printErrEnterWithNumber();
            option = readFromUser();
        }
        return Integer.parseInt(option);
    }

    private List<Town> readInputRoutes() throws IOException {
        String routes = "";
        while (!Validator.isRoutes(routes)){
            Printer.printTownModelExemplo();
            routes = readFromUser();
        }
        return createTowns(routes);
    }

    private String readInputCondition() throws IOException {
        if(input.getOption() == 3 || input.getOption() == 4) {
            String condition = null;
            while (!Validator.isCodition(condition)){
                Printer.printDistanceStopModelExemplo(getTypeCondition(input.getOption()));
                condition = readFromUser();
            }
            return condition;
        }
        return null;
    }

    private String readFromUser() throws IOException {
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        return in.readLine().replace(" ", "");
    }

    private List<Town> createTowns(String routes){
        List<Town> towns = new ArrayList<Town>();
        for (String town : routes.split("-")) {
            towns.add(createTown(town));
        }
        return towns;
    }

    private Town createTown(String townS){
        for(Town town : Graph.townsGraph){
            if(townS.equals(town.getName()))
                return town;
        }
        return null;
    }

    private String getTypeCondition(int menuOption) {
        if(menuOption == 3)
            return "stop";
        return "dist";
    }

    public Input getInput() {
        return input;
    }
}
