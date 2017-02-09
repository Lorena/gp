package br.com.tw.lorena.view;

import br.com.tw.lorena.model.Town;

import java.util.List;

public class Input {

    private int option;
    private List<Town> routes;
    private String condition;

    public Input(int option, List<Town> routes, String condition) {
        this.option = option;
        this.routes = routes;
        this.condition = condition;
    }

    public Input() {
    }

    public void setOption(int option) {
        this.option = option;
    }

    public void setRoutes(List<Town> routes) {
        this.routes = routes;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getOption() {
        return option;
    }

    public List<Town> getRoutes() {
        return routes;
    }

    public String getCondition() {
        return condition;
    }
}
