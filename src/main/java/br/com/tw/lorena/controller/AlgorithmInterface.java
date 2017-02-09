package br.com.tw.lorena.controller;

import br.com.tw.lorena.model.Town;

import java.io.IOException;
import java.util.List;

public interface AlgorithmInterface {

    public int getOption();
    public void execute() throws IOException;
    public void setTowns(List<Town> towns);
    public void setCondition(String condition);
}
