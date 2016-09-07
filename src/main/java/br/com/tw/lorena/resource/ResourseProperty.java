package br.com.tw.lorena.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import br.com.tw.lorena.view.Printer;

public class ResourseProperty {

	private ResourceBundle properties;
	private static ResourseProperty instancia;  
	
	public static synchronized ResourseProperty getInstancia() throws FileNotFoundException, IOException {  
	      if (instancia == null)  
	         instancia = new ResourseProperty();  
	      return instancia;  
	}  
	
	public ResourseProperty() {
		super();
		try {
			properties = ResourceBundle.getBundle("br.com.tw.lorena.resource.graphProblem");
		} catch(MissingResourceException e) {
			Printer.printNotFindFile();
			System.exit(1);
		}
	}
	
	 public static String getProperty(String name) {
			String s = "";
			try {
				s = new ResourseProperty().properties.getString(name);
			} catch (MissingResourceException e) {
				Printer.printMissingData(name);
			}
			return s;
		}

	
	
}
