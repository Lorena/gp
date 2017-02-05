package br.com.tw.lorena.resource;

import br.com.tw.lorena.view.Printer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;

public class ResourseProperty {

	private static Properties properties;

	public ResourseProperty() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("src/main/java/br/com/tw/lorena/resource/graphProblem.properties");
		props.load(file);
		properties = props;
	}

	public static Properties getProperties() {
		return properties;
	}

	 public static String getProperty(String name) throws IOException {
			String field = "";
			try {
				field = new ResourseProperty().getProperties().getProperty(name);
			} catch (MissingResourceException e) {
				Printer.printMissingData(name);
			}
			return field;
		}

}
