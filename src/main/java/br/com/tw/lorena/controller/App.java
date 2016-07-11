package br.com.tw.lorena.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import br.com.tw.lorena.view.View;

public class App 
{
	public static void main(String[] args) throws FileNotFoundException, IOException
    {
		View view = new View();
    	view.executeProject();
    }
}
